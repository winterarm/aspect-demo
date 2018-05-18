package com.winterarm.aspect.event;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

/**
 * Created by huangdx on 2018/5/16.
 */
public class DemoSessionEvent extends HttpSessionEvent {

  String desc= "";

  Class clazz;

  String paramName;



  public DemoSessionEvent(HttpSession source) {
    super(source);
  }

  public DemoSessionEvent(HttpSession source, String desc, Class clazz, String paramName) {
    super(source);
    this.desc = desc;
    this.clazz = clazz;
    this.paramName = paramName;
  }
}
