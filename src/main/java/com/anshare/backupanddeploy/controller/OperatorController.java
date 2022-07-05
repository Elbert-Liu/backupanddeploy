package com.anshare.backupanddeploy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能模块-中文描述
 *
 * @author liule
 * @since 21/6/2022 17:35
 */

@RestController
@RequestMapping(value = "/operator")
public class OperatorController {

    @RequestMapping(value = "/move", method = RequestMethod.GET)
    public void move()
    {

    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public void delete()
    {

    }

    @RequestMapping(value = "/deleteDir", method = RequestMethod.GET)
    public void deleteDir()
    {

    }
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void get()
    {

    }
    @RequestMapping(value = "/mkDir", method = RequestMethod.GET)
    public void mkDir()
    {

    }
    @RequestMapping(value = "/put", method = RequestMethod.GET)
    public void put()
    {

    }

    @RequestMapping(value = "/stat", method = RequestMethod.GET)
    public void stat()
    {

    }
}
