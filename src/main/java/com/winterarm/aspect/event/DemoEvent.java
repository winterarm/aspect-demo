package com.winterarm.aspect.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by huangdx on 2018/5/16.
 */
public class DemoEvent extends ApplicationEvent {

  String desc= "";

  Class clazz;

  String paramName;

  public DemoEvent(Object source) {
    super(source);
  }

  public DemoEvent(Object source, String desc, Class clazz, String paramName) {
    super(source);
    this.desc = desc;
    this.clazz = clazz;
    this.paramName = paramName;
  }
}
