package com.winterarm.aspect.listener;

import com.alibaba.fastjson.JSON;
import com.winterarm.aspect.event.DemoEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by huangdx on 2018/5/16.
 */
@Component
public class DemoApplicationListener1 {

  @EventListener
  @Async
  public void processBlackListEvent(DemoEvent event) {
    // BlackListEvent is processed in a separate thread
    System.out.println("+++++++++++++++++++++++++");
    System.out.println(JSON.toJSONString(event));
  }
}
