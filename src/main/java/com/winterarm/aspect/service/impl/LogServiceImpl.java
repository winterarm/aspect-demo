package com.winterarm.aspect.service.impl;

import com.winterarm.aspect.annotation.SystemServiceLog;
import com.winterarm.aspect.service.ILogService;
import com.winterarm.aspect.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by huangdx on 2018/3/12.
 */
@Service("logService")
public class LogServiceImpl implements ILogService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @SystemServiceLog
    public String createUser(User user) {
        logger.info("我要创建一个用户啦！{}", user);
        return "0000";
    }
}
