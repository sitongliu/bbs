package com.lst.bbs.controller;

import com.lst.bbs.config.FreeMarkerConfig;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuSitong on 2017/5/8.
 */
@WebServlet(
        name="w1",
        urlPatterns = {"/welcome"},
        initParams = {
                @WebInitParam(name="index",value = "article?action=queryall&cur=0")
        })
public class WelcomeController extends HttpServlet {
    private Map<String,String> forwards;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("OK");
        //resp:响应对象
        //index：访问的某个页的键
        //forwards:含有键值对页的map
        //vmap:传值的map
       // FreeMarkerConfig.forward(resp,forwards.get("index"),null);
        RequestDispatcher dispatcher=req.getRequestDispatcher(forwards.get("index"));

        dispatcher.forward(req,resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        forwards=new HashMap<String,String>();
        forwards.put("index",config.getInitParameter("index"));
    }
}
