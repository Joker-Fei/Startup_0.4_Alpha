package com.syf.util;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitConfigInfo extends HttpServlet
{
  public void init()
    throws ServletException
  {
    ServletConfig config = getServletConfig();
    String driver = config.getInitParameter("driver");
    String userName = config.getInitParameter("userName");
    String userPwd = config.getInitParameter("userPwd");
    String url = config.getInitParameter("url");

    DBUtil.init(driver, url, userName, userPwd);
  }
}