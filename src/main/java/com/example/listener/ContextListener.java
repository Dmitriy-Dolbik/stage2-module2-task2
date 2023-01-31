package com.example.listener;

import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener
{
    public void contextInitialized(ServletContextEvent e) {
        ServletContext context = e.getServletContext();
        context.setAttribute("servletTimeInt", LocalDateTime.now());
    }
}
