package com.coder.commonBase.controller;

import com.coder.commonBase.Utils.Queue.MessageQueUntils;
import com.coder.commonBase.Utils.RespMessageUtils;
import com.coder.commonBase.entity.User;
import com.coder.commonBase.exception.MessageException;
import com.coder.commonBase.props.ShiroProps;
import com.coder.commonBase.service.UserService;
import com.coder.commom.annotation.AccessLimit;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;


/**
 * 专门负责用户的登入和注册的账号管理的类
 *
 * @Author coder
 * @Date 2021 /11/29 19:54
 * @Description
 */
@Data
@Controller
@RequestMapping("/users")
@Api(value = "账户登入、注册Contoller")
/*
镇压因mabatis自动注入导致的问题
@SuppressWarnings("all")
*/
public class AccountController {

    private final UserService userService;

    private final MessageQueUntils messageQueUntils;

    private final ShiroProps shiroProps;

    /**
     * Instantiates a new Account controller and finish the automatic injection.
     *
     * @param userService      the user service
     * @param messageQueUntils the message que untils
     * @param shiroProps       the shiro props
     */
    public AccountController(UserService userService, MessageQueUntils messageQueUntils,ShiroProps shiroProps) {
        this.userService = userService;
        this.messageQueUntils = messageQueUntils;
        this.shiroProps = shiroProps;
    }


  /**
   * Login find exist string.
   *
   * @param account the account
   * @return the string
   */
    @ResourceAcquisitionRecorder(resourceType = ResourceType.CHECK,name = "用户名核验")
    @GetMapping("/login/username")
    @ResponseBody
    @ApiOperation(value = "查找账户是否存在", notes = "查找账户是否存在", httpMethod = "POST")
    public String loginFindExist(String account){
        User user = userService.selectOne(account);
        if(user==null){
            return RespMessageUtils.ERROR();
        }else{
            Map<String,Object> data = new HashMap<>();
            if(user.getImageId()!=null){
                data.put("imageId",user.getImageId());
            }else{
                data =null;
            }
            return RespMessageUtils.SUCCESS(data);
        }
    }


  /**
   * Login string.
   *
   * @param username the username
   * @param password the password
   * @param map      the map
   * @param request  the request
   * @return the string
   */
    @ResourceAcquisitionRecorder(resourceType = ResourceType.CHECK,name = "登入核验")
    @PostMapping("/login")
    @ApiOperation(value="登入", notes = "登入成功,跳转到dashboard页面，失败跳转到error页面", httpMethod = "POST")
    public String login(String username, String password, ModelMap map, HttpServletRequest request){
        if(userService.login(new User(username, password))) {
            User user = userService.selectOne(username);
            System.out.println(user);
          //记录登入信息
            request.getSession().setAttribute("user",new User(username,new Date(System.currentTimeMillis()),user.getImageId()));
            if(username.equals(shiroProps.getAdmin())||user.hasRole("admin")){
              return "forward:/admin/dashBoard";
            }
            return "forward:/account/dashBoard";
        }else{
            RespMessageUtils.generateErrorInfo(map,new String[]{"用户名和密码错误"});
            return "forward:/error";
        }
    }


   /**
    * Register string.
    *
    * @param user         the user
    * @param photo        the photo
    * @param repeatPwd    the repeat pwd
    * @param validateData the validate data
    * @param map          the map
    * @return the string
    */
    @AccessLimit(seconds = 1)
    @ResourceAcquisitionRecorder(resourceType = ResourceType.MODIFY, name = "注册用户修改")
    @PostMapping("/register")
    @ApiOperation(value="账号注册",notes = "注册成功返回登入页面，失败跳转到失败页面，交代失败的原因", httpMethod = "POST")
    public String register(User user, String repeatPwd,  MultipartFile photo, String validateData, ModelMap map){
        //验证失败，或者密码重复不正确
        if(!repeatPwd.equals(user.getPassword())) {
            RespMessageUtils.generateErrorInfo(map,new String[]{"两次输入的密码不正确"});
            return "forward:/error";
        }
        try {
            messageQueUntils.checkMsg(user.getEmail(),validateData);
        } catch (MessageException e) {
            RespMessageUtils.generateErrorInfo(map,new String[]{e.getMessage()});
            return "forward:/error";
        }

        //登入页面
        try {
            userService.insert(user,photo);
        } catch (IOException e) {
            RespMessageUtils.generateErrorInfo(map,new String[]{e.getMessage()});
            return "forward:/error";
        }
        return "redirect:/login";
    }

    /**
     * Regist page string.
     *
     * @return the string
     */
    @ResourceAcquisitionRecorder(name = "注册页面")
    @GetMapping("/registPage")
    @ApiOperation(value="注册页面", notes = "跳转到注册页面regist", httpMethod = "GET")
    public String registPage(){
        return "regist";
    }

    /**
     * Generate msg string.
     *
     * @param email the email
     * @return the string
     */
    @ResourceAcquisitionRecorder(resourceType = ResourceType.MODIFY, name="生成验证信息修改")
    @GetMapping("/generateMsg")
    @ResponseBody
    @ApiOperation(value="生成验证码", notes = "生成验证码", httpMethod = "GET")
    public String generateMsg(String email){
        messageQueUntils.generateMsg(email);
        return RespMessageUtils.SUCCESS("验证码已发送，请注意查收");
    }

    /**
     * Check msg string.
     *
     * @param email   the email
     * @param message the message
     * @return the string
     */
    @ResourceAcquisitionRecorder(resourceType = ResourceType.CHECK, name="验证码核验")
    @PostMapping("/checkMsg")
    @ResponseBody
    @ApiOperation(value="生成验证码", notes = "生成验证码,错误返回错误信息", httpMethod = "POST")
    public String checkMsg(String email,String message){
        try {
            messageQueUntils.checkMsg(email,message);
            return RespMessageUtils.SUCCESS();
        } catch (MessageException e) {
            return RespMessageUtils.ERROR(e.getMessage());
        }
    }
}
