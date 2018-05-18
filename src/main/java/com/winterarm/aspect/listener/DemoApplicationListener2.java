package com.winterarm.aspect.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by huangdx on 2018/5/16.
 */
@WebListener
public class DemoApplicationListener2 implements HttpSessionListener {

  @Override
  public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    System.out.println("DemoApplicationListener2 sessionCreated");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    System.out.println("DemoApplicationListener2 sessionDestroyed");
  }
}
