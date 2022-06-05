package com.coder.commom.fileSystem.controller;

import com.coder.commom.annotation.Enum.ApplicationType;
import com.coder.commom.annotation.Enum.ResourceType;
import com.coder.commom.annotation.ResourceAcquisitionRecorder;
import com.coder.commom.fileSystem.service.imp.FileAccountServiceImpl;
import com.coder.commonBase.Utils.RespMessageUtils;
import com.coder.commonBase.entity.User;
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

    private final FileAccountServiceImpl userService;


    public FileSystemAccountController(FileAccountServiceImpl userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping(value="/login")
    @ApiOperation(value="登入", notes = "登入成功,返回success, 返回error.", httpMethod = "POST")
    @ResourceAcquisitionRecorder(resourceType = ResourceType.CHECK,name = "文件系统的登入核验",applicationType = ApplicationType.FILESYSTEM)
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
    @ApiOperation(value="登入", notes = "登入成功,返回success, 返回error.", httpMethod = "POST")
    @ResourceAcquisitionRecorder(resourceType = ResourceType.MODIFY, name = "文件系统的用户注册", applicationType = ApplicationType.FILESYSTEM)
    public RespMessageUtils register(@RequestBody User user){
        log.info(user.toString());
        User user1 = userService.selectOne(user.getUsername());
        if(user1 == null){
            userService.insert(user);
            return RespMessageUtils.SUCCESSOBJ("注册成功,请登入。");
        }else{
            return new RespMessageUtils(false, "用户已经存在");
        }
    }

}
