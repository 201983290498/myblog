package com.coder.commom.fileSystem.service;

import com.coder.commonBase.entity.User;

/**
 * 专门负责文件系统方面的登入问题，核心还是依靠基础的userService对用户表进行管理,
 * 此外需要加一些额外的操作，例如在创建文件的时候需要常见额外的文件目录
 * @Author coder
 * @Date 2022/6/4 16:34
 * @Description
 */

public interface FileAccoutService {

    /**
     * 登入，验证账号和密码
     * @param user 需要登入的用户
     * @return
     */
    boolean login(User user);

    /**
     * 查找一个用户
     * @param username 用户的用户名
     * @return 返回对应的用户对象
     */
    User selectOne(String username);

    /**
     * 插入一个用户对象
     * @param user 用户的基本信息
     */
    void insert(User user);
}
