package com.example.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.Users;

@WebServlet("/login")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
        {
            response.sendRedirect("/user/hello.jsp");
        }
        else
        {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String login = (String) session.getAttribute("login");
        String password = (String) request.getAttribute("password");

        if (login != null && password != null)
        {
            Users usersInstance = Users.getInstance();
            List<String> usersList = usersInstance.getUsers();
            if (usersList.contains(login))
            {
                session.setAttribute("user", login);
                response.sendRedirect("/user/hello.jsp");
            }
            else
            {
                request.getRequestDispatcher("/login.jsp")
                        .forward(request, response);
            }
        } else {
            request.getRequestDispatcher("/login.jsp")
                    .forward(request, response);
        }
    }
}
