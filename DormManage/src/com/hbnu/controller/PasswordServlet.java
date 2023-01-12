package com.hbnu.controller;

import com.hbnu.pojo.User;
import com.hbnu.service.UserService;
import com.hbnu.service.impl.UserServiceImpl;
import com.hbnu.uitl.DBUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/password.action")
public class PasswordServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        User login_user = (User) req.getSession().getAttribute("session_user");
        UserServiceImpl userService = new UserServiceImpl();

        if(action!=null && action.equals("preChange")){
            req.setAttribute("mainRight", "passwordChange.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }else if(action != null && action.equals("preChange")){
            //校验用户输入的原密码是否正确
            String oldPassWord = req.getParameter("oldPassWord");


            //当前登录的用户原始密码
            String password = login_user.getPassword();

            if(!password.equals(oldPassWord)){
                PrintWriter writer = resp.getWriter();
                writer.print("您输入的原始密码错误，请重新输入");
            }
        }else if(action !=null && action.equals("change")){
            //修改密码
            String newPassword = req.getParameter("newPassword");
            login_user.setPassword(newPassword);

            userService.updateManager(login_user);

            req.getRequestDispatcher("/index.jsp").forward(req,resp);

        }
    }
}

