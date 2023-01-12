package com.hbnu.controller;

import com.hbnu.pojo.User;
import com.hbnu.service.impl.UserServiceImpl;
import com.hbnu.service.UserService;
import com.hbnu.uitl.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
     UserService userService = new UserServiceImpl();

     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //���ñ����ʽ����ֹ����
        req.setCharacterEncoding("UTF-8");
        //�����û�����ĵ�¼��Ϣ
        String stuCode = req.getParameter("stuCode");
        String password = req.getParameter("password");
        String remember = req.getParameter("remember");

        //�����û��������Ϣ����ѯ���ݿ�
        User user = userService.findUserByStuCodeAndPassword(stuCode,password);
        if(user==null){
            req.setAttribute("error", "���������Ϣ��������������");

            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }else{
            req.getSession().setAttribute("session_user",user);

            if(remember!=null && remember.equals("remember-me")){
                CookieUtils.addCookie("cookie_stu_pwd",7*24*3600,req,resp,stuCode,password);

            }
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }
    }
}
