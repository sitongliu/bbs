package com.lst.bbs.controller;

import com.lst.bbs.config.FreeMarkerConfig;
import com.lst.bbs.po.Bbsuser;
import com.lst.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


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

/**
 * Created by Administrator on 2017/5/11.
 */
@WebServlet(name="/a",urlPatterns = {"/article"},
        initParams = {@WebInitParam(name = "show",value="show.ftl")})
public class ArticleController extends HttpServlet{
    private HashMap<String,String> map=new HashMap<String,String>();
    @Autowired
    private ArticleService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action=req.getParameter("action");
        switch(action){
            case "queryall":
                //page:当前页，size：每页行数
                String curpage=req.getParameter("cur");
                HashMap vmap = new HashMap<String, Object>();

                int pagesize=0;

                Bbsuser user = (Bbsuser) req.getSession().getAttribute("user");
                if(user!=null){//登录成功
                    pagesize=user.getPagenum();
                    vmap.put("msg", "恭喜" + user.getUsername() + "，登录成功！");
                    vmap.put("user", user);

                }else{//游客
                    pagesize=5;
                }
                Sort sort=new Sort(Sort.Direction.DESC,"id");
                Pageable pb=new PageRequest(Integer.parseInt(curpage),pagesize,sort);
                Page p= service.findAll(pb , 0);

                vmap.put("page", p);

                FreeMarkerConfig.forward(resp,map.get("show"),vmap);
                break;
            case "del"://删除主贴，且从贴也必须删除
                String id=req.getParameter("id");//帖子的主键
                service.delete(Integer.parseInt(id),Integer.parseInt(id));

                RequestDispatcher dispatcher=req.getRequestDispatcher("/welcome");

                try {
                    dispatcher.forward(req,resp);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("show",config.getInitParameter("show"));
    }
}
