package com.anshare.backupanddeploy.service;

import com.anshare.backupanddeploy.controller.dto.UserDTO;
import com.anshare.backupanddeploy.controller.dto.UserPasswordDTO;
import com.anshare.backupanddeploy.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);

    void updatePassword(UserPasswordDTO userPasswordDTO);

    Page<User> findPage(Page<User> objectPage, String username);
}
