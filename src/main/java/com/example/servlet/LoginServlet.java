package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        logger.info("Enter to doGet() method of LoginServlet");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
        {
            logger.info("Redirect to /user/hello.jsp from doGet() method of LoginServlet");
            response.sendRedirect("/user/hello.jsp");
        }
        else
        {
            logger.info("Redirect to /login.jsp from doGet() method of LoginServlet");
            response.sendRedirect("/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        List<String> usersList = Users.getInstance().getUsers();

        if (usersList.contains(login) && !password.isEmpty())
        {
            logger.info("Redirect to /user/hello.jsp from doPost() method of LoginServlet");
            session.setAttribute("user", login);
            response.sendRedirect("/user/hello.jsp");
        }
        else
        {
            logger.info("Redirect to /login.jsp from doPost() method of LoginServlet");
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }
}
