package com.anshare.backupanddeploy.service;

import com.anshare.backupanddeploy.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
