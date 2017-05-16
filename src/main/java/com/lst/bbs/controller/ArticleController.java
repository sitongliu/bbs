package com.lst.bbs.controller;

import com.lst.bbs.config.FreeMarkerConfig;
import com.lst.bbs.po.Bbsuser;
import com.lst.bbs.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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
 * Created by LiuSitong on 2017/5/14.
 */

@WebServlet(
        name = "article",
        urlPatterns = "/article",
        initParams = {@WebInitParam( name = "show",value = "show.ftl")}
)
public class ArticleController extends HttpServlet{

    @Autowired
    ArticleService articleService = new ArticleService();
    HashMap map = new HashMap();

    @Override
    public void init(ServletConfig config) throws ServletException {
        map.put("show",config.getInitParameter("show"));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        switch (action){
            case "queryall" :
                Bbsuser bbsuser = (Bbsuser) req.getSession().getAttribute("user");
                int pageSize = 0;  // 1.每页显示的信息条数
                if (bbsuser != null){
                  pageSize = bbsuser.getPagenum();
                }else {
                    pageSize = 10;
                }

                int curPage = Integer.parseInt(req.getParameter("cur")); // 当前所在的页码
                Pageable pageable = new PageRequest(curPage,pageSize);
                Page page =articleService.findAll(pageable,0);
                HashMap vmap = new HashMap();
                vmap.put("page",page);
                if (bbsuser != null){
                    vmap.put("msg","恭喜"+bbsuser.getUsername()+"登录成功");
                }
                vmap.put("user",bbsuser);
                FreeMarkerConfig.forward(resp,map.get("show").toString(),vmap);
                break;
        }

    }
}
