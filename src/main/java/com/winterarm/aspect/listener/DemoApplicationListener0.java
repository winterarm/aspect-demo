package com.winterarm.aspect.listener;

import com.alibaba.fastjson.JSON;
import com.winterarm.aspect.event.DemoEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by huangdx on 2018/5/16.
 */
public class DemoApplicationListener0 implements ApplicationListener<DemoEvent> {

  @Override
  public void onApplicationEvent(DemoEvent demoEvent) {
    System.out.println("-----------------------");
    System.out.println(JSON.toJSONString(demoEvent));
  }
}
