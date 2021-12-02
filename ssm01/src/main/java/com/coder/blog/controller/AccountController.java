package com.coder.blog.controller;

import com.coder.blog.Utils.MessageQueUntils;
import com.coder.blog.Utils.RespMessageUtils;
import com.coder.blog.entity.User;
import com.coder.blog.exception.MessageException;
import com.coder.blog.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;


/**
 * 专门负责用户的登入和注册
 * @Author coder
 * @Date 2021/11/29 19:54
 * @Description
 */
@Data
@Controller
@RequestMapping("/users")
@Api(value = "账户登入、注册Contoller")
//镇压自动注入的问题
//@SuppressWarnings("all")
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private MessageQueUntils messageQueUntils;


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


    @PostMapping("/login")
    @ApiOperation(value="登入", notes = "登入成功,跳转到dashboard页面，失败跳转到error页面", httpMethod = "POST")
    public String login(String username, String password,ModelMap map){

        if(userService.login(new User(username, password))) {
            return "userInfo/dashBoard";
        }else{
            map.addAttribute("errors",RespMessageUtils.generateErrorInfo(new String[]{"用户名和密码错误"}));
            return "error";
        }
    }


    @PostMapping("/register")
    @ApiOperation(value="账号注册",notes = "注册成功返回登入页面，失败跳转到失败页面，交代失败的原因", httpMethod = "POST")
    public String register(User user, MultipartFile photo, String repeatPwd, String validateData, ModelMap map){
        //验证失败，或者密码重复不正确

        if(!repeatPwd.equals(user.getPassword())) {
            map.addAttribute("errors",RespMessageUtils.generateErrorInfo(new String[]{"两次输入的密码不正确"}));
            return "error";
        }
        try {
            messageQueUntils.checkMsg(user.getEmail(),validateData);
        } catch (MessageException e) {
            map.addAttribute("errors",RespMessageUtils.generateErrorInfo(new String[]{e.getMessage()}));
            return "error";
        }

        //登入页面
        try {
            userService.insert(user,photo);
        } catch (IOException e) {
            map.addAttribute("errors",RespMessageUtils.generateErrorInfo(new String[]{e.getMessage()}));
            return "error";
        }
        return "index";
    }

    @GetMapping("/registPage")
    @ApiOperation(value="注册页面", notes = "跳转到注册页面regist", httpMethod = "GET")
    public String registPage(){
        return "regist";
    }

    @GetMapping("/generateMsg")
    @ResponseBody
    @ApiOperation(value="生成验证码", notes = "生成验证码", httpMethod = "GET")
    public String generateMsg(String email){
        messageQueUntils.generateMsg(email);
        return RespMessageUtils.SUCCESS("验证码已发送，请注意查收");
    }

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