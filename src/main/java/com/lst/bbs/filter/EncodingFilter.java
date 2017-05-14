package com.lst.bbs.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by LiuSitong on 2017/5/8.
 */
public class EncodingFilter implements Filter {
    private String encoder ;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoder=filterConfig.getInitParameter("encoding");//得到配置中的过滤转码值

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(encoder);
        //传递request和response到下个对象 防止被截获
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
