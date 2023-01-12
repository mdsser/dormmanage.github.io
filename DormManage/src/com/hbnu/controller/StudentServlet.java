package com.hbnu.controller;

import com.hbnu.pojo.DormBuild;
import com.hbnu.pojo.User;
import com.hbnu.service.impl.DormBuildServiceImpl;
import com.hbnu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");
        UserServiceImpl userService = new UserServiceImpl();
        DormBuildServiceImpl dormBuildService = new DormBuildServiceImpl();
        //1、接受请求
        if("list".equals(action)){
            //查询所有用户
            //2、调用相应的请求
            String searchType = req.getParameter("searchType");
            String keyword = req.getParameter("keyword");
            List<User> dormManager = userService.getDormManager(searchType,keyword);
            req.setAttribute("users", dormManager);

            //3、返回页面
            req.setAttribute("mainRight", "studentList.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }else if("preAdd".equals(action)){

            //查询宿舍楼

            List<DormBuild> dormBuildList = dormBuildService.getAllDormBuild();
            req.setAttribute("builds",dormBuildList);

            req.setAttribute("mainRight", "studentAddOrUpdate.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);
        }else if("save".equals(action)){
            //1、获取前端相应请求
            String id = req.getParameter("id");
            String stu_code = req.getParameter("stu_code");
            String name = req.getParameter("name");
            String passWord = req.getParameter("passWord");
            String sex = req.getParameter("sex");
            String tel = req.getParameter("tel");
            String dorm_code = req.getParameter("dorm_code");
            String[] dormBuildIds = req.getParameterValues("dormBuildId");

            if(id==null || id.equals("")){      //保存的业务
                User user = new User();
                user.setName(name);
                user.setPassword(passWord);
                user.setSex(sex);
                user.setStuCode(stu_code);
                user.setTel(tel);
                user.setDormcode(dorm_code);
                user.setRoleId(1);
                user.setDisabled(0);

                User login_user = (User) req.getSession().getAttribute("session_user");
                user.setCreatUserId(login_user.getId());

                //2、调用服务
                userService.saveManagerAndBuilds(user,dormBuildIds);


            }else{          //修改的业务
                //1、修改宿舍楼管理员信息
                User user = userService.getDormManagerById(Integer.parseInt(id));
                user.setStuCode(stu_code);
                user.setName(name);
                user.setPassword(passWord);
                user.setSex(sex);
                user.setTel(tel);
                userService.updateManager(user);

                dormBuildService.deleteDormBuildsByUserId(user.getId());

                dormBuildService.addDormBuildAndByUserId(user.getId(),dormBuildIds);

            }
            //3、跳转页面
            resp.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");

        }else if("preUpdate".equals(action)){
            //1、接受请求数据
            String id = req.getParameter("id");
            //2、调用相应服务
            User manager = userService.getDormManagerById(Integer.parseInt(id));
            req.setAttribute("user",manager);

            //获得所有的宿舍楼信息
            List<DormBuild> dormBuildList = dormBuildService.getAllDormBuild();
            req.setAttribute("builds",dormBuildList);

            //获取该用户的宿舍楼信息
            List<DormBuild> dormBuilds = dormBuildService.getDormBuildByUserId(manager.getId());
            ArrayList<Integer> userbuildIds = new ArrayList<>();
            for (DormBuild dormBuild : dormBuilds) {
                userbuildIds.add(dormBuild.getId());
            }
            req.setAttribute("userBuildids",userbuildIds);
            //3、返回页面
            req.setAttribute("mainRight", "studentAddOrUpdate.jsp");
            req.getRequestDispatcher("/jsp/main.jsp").forward(req,resp);

        } else if ("deleteOrAcive".equals(action)) {
            //1、获取请求数据
            String id = req.getParameter("id");
            String disabled = req.getParameter("disabled");

            //2、调用服务
            User user = userService.getDormManagerById(Integer.parseInt(id));
            user.setDisabled(Integer.parseInt(disabled));
            userService.updateManager(user);

            //3、跳转页面
            resp.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");

        }
    }
}
