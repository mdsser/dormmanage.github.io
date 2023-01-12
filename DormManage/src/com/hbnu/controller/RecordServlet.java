package com.hbnu.controller;

import com.hbnu.pojo.Record;
import com.hbnu.pojo.User;
import com.hbnu.service.RecordService;
import com.sun.deploy.security.CredentialManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/record.action")
public class RecordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User login_user = (User) req.getSession().getAttribute("session_user");



        String action = req.getParameter("action");
        if(action !=null && action.equals("list")){
            //跳转页面
            req.setAttribute("mainRight", "recordList.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }else if(action !=null && action.equals("preAdd")){
            req.setAttribute("mainRight", "recordAddOrUpdate.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }else if(action !=null && action.equals("save")){

            //保存缺勤记录
            String stuCode = req.getParameter("stuCode");
            String date = req.getParameter("date");
            String remark = req.getParameter("remark");

        }else{
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = null;
            Record record = new Record();
            record.setStudentId(login_user.getId());
            record.setDate(date2);
        }
    }
}
