package com.coder.commom.fileSystem.controller;

import com.coder.commonBase.Utils.RespMessageUtils;
import com.coder.commonBase.entity.User;
import com.coder.commonBase.props.ShiroProps;
import com.coder.commonBase.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 文件管理系统的登入平台
 * @Author coder
 * @Date 2022/6/4 9:55
 * @Description
 */
@Controller
@Slf4j
@RequestMapping("/fileSystem")
public class FileSystemAccountController {

    private final UserService userService;

    private final ShiroProps shiroProps;

    public FileSystemAccountController(ShiroProps shiroProps, UserService userService) {
        this.shiroProps = shiroProps;
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping(value="/login")
    @ApiOperation(value="登入", notes = "登入成功,返回success, 返回error.", httpMethod = "POST")
    public RespMessageUtils login(@RequestBody User user, ModelMap map, HttpServletRequest request){
        log.info(user.getUsername()+user.getPassword());
        if(userService.login(user)){
            user = userService.selectOne(user.getUsername());
            System.out.println(user);
            //记录登入信息
            request.getSession().setAttribute("user",new User(user.getUsername(),new Date(System.currentTimeMillis()),user.getImageId()));
            return RespMessageUtils.SUCCESSOBJ("账号密码正确");
        }
        /**
         * 返回登入信息
         */
        return new RespMessageUtils(false, "用户名和密码错误");
    }

    @ResponseBody
    @PostMapping("/register")
    public RespMessageUtils register(@RequestBody User user){
        log.info(user.toString());
        User user1 = userService.selectOne(user.getUsername());
        if(user1 == null){
            userService.insert(user);
            return RespMessageUtils.SUCCESSOBJ("登入成功!");
        }else{
            return new RespMessageUtils(false, "用户已经存在");
        }
    }

}