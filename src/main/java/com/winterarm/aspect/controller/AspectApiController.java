package com.winterarm.aspect.controller;

import com.winterarm.aspect.annotation.SystemControllerLog;
import com.winterarm.aspect.service.ILogService;
import com.winterarm.aspect.vo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by huangdx on 2018/3/12.
 */
@RestController
public class AspectApiController {

    @Resource
    private ILogService logService;

    @PostMapping("/demo")
    @SystemControllerLog(description = "这就是方法描述")
    public String createAUser(@RequestBody User user){
        return logService.createUser(user);
    }

}
