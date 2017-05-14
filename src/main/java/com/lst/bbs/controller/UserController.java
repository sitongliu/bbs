package com.lst.bbs.controller;

import com.lst.bbs.config.FreeMarkerConfig;
import com.lst.bbs.po.Bbsuser;
import com.lst.bbs.service.UserService;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by LiuSitong on 2017/5/8.
 */

@WebServlet(
        name = "u1",
        urlPatterns = "/user",
        initParams = @WebInitParam(name = "show", value = "show.ftl"))
public class UserController extends HttpServlet {

    @Autowired
    UserService service;
    HashMap<String, String> map = new HashMap<String, String>();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (ServletFileUpload.isMultipartContent(req)) {  //流形式上传
            HashMap<String, Object> vmap = new HashMap<>();
            Bbsuser bbsuser = service.uploadPic(req);

            try (FileInputStream fis = new FileInputStream(bbsuser.getPicPath())) {
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                bbsuser.setPic(buffer);
                vmap = new HashMap<String, Object>();
                service.save(bbsuser);
                vmap.put("msg", bbsuser.getUsername() + "注册成功！");
                FreeMarkerConfig.forward(resp, map.get("show").toString(), vmap);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String action = req.getParameter("action");
            switch (action) {
                case "login"://登录
                    login(req, resp);

                    break;
                case "pic"://取得头像
                    String id = req.getParameter("id");
                    Bbsuser user = service.queryPicByid(Integer.parseInt(id));


                    OutputStream out = resp.getOutputStream();
                    out.write(user.getPic());

                    out.flush();
                    out.close();

                    break;

                case "check"://登录
                    check(req, resp);

                    break;
            }
        }


    }

    public void check(HttpServletRequest req, HttpServletResponse resp) {

        String reusername = req.getParameter("username");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        PrintWriter out = null;

        try {
            out =resp.getWriter();
            if (service.exists(reusername)== true ){
               out.print("true"); //用户名存在
            }else {
                out.print("false"); //用户名不存在
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void login(HttpServletRequest req, HttpServletResponse resp) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        HashMap<String, Object> vmap = null;
        Bbsuser bbsuser = service.login(username, password);
        if (bbsuser != null) { // 如果查出存在用户的话  保存cookie
            String cookie = req.getParameter("sun");
            if (cookie != null) {  //判断已经勾选了记住cookie的按钮
                Cookie uc = new Cookie("papaoku", username);
                uc.setMaxAge(3600 * 24 * 7);
                resp.addCookie(uc);

                Cookie pc = new Cookie("papaokp", password);
                pc.setMaxAge(3600 * 24 * 7);
                resp.addCookie(pc);

            }

            vmap = new HashMap<String, Object>();
            vmap.put("msg", username + "登录成功");
            vmap.put("user", bbsuser);
            FreeMarkerConfig.forward(resp, map.get("show").toString(), vmap);

        }

    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("show", config.getInitParameter("show"));

    }
}
