package com.coder.commom.fileSystem.service.imp;

import com.coder.commom.fileSystem.entity.FileBase;
import com.coder.commom.fileSystem.service.FileAccoutService;
import com.coder.commonBase.entity.User;
import com.coder.commonBase.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 文件管理类
 * @Author coder
 * @Date 2022/6/4 16:35
 * @Description
 */
@Service("fileAccountService")
@Slf4j
public class FileAccountServiceImpl implements FileAccoutService {

    private final UserService userService;

    private final ContentFileServiceImp contentService;

    private final FileServiceImp fileService;

    public FileAccountServiceImpl(FileServiceImp fileService, ContentFileServiceImp contentService, UserService userService) {
        this.fileService = fileService;
        this.contentService = contentService;
        this.userService = userService;
    }


    /**
     * 登入，验证账号和密码
     *
     * @param user 需要登入的用户
     * @return
     */
    @Override
    public boolean login(User user) {
        return userService.login(user);
    }

    /**
     * 查找一个用户
     *
     * @param username 用户的用户名
     * @return 返回对应的用户对象
     */
    @Override
    public User selectOne(String username) {
        return userService.selectOne(username);
    }

    /**
     * 在文件系统插入一个用户, 基本信息的插入包括了用户的基本信息和角色、权限。其次文件系统的基础文件夹的创建
     * todo 文件系统应该是其他系统的核心子系统之一, 所以该方法需要对外直接提供添加用户的功能，而不能走最基本的UserService
     * @param user 用户的基本信息
     */
    @Override
    public void insert(User user) {
        userService.insert(user);
        contentService.insertOneContentFile(new FileBase(user.getUsername(),"/home"));
        contentService.insertOneContentFile(new FileBase(user.getUsername(),"/data"));
    }
}
