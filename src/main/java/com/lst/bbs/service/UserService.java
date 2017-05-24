package com.lst.bbs.service;

import com.lst.bbs.dao.IUserDao;
import com.lst.bbs.po.Bbsuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by LiuSitong on 2017/5/10.
 */
@Service
@Transactional
public class UserService {
    @Autowired  //属性自动装配
    private IUserDao dao = null;
    private HashMap types = new HashMap();

    public Bbsuser login(String username, String userId){
        return dao.login(username,userId);
    }

    public UserService() { //图片允许上传的类型
        types.put("image/jpeg", ".jpg");
        types.put("image/gif", ".gif");
        types.put("image/x-ms-bmp", ".bmp");
        types.put("image/png", ".png");
    }

    /**
     * 头像上传，并且将注册的用户名，密码，图片路径保存到bbsuser
     * @param request
     * @return bbsuser
     */
    public Bbsuser uploadPic(HttpServletRequest request){

        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getServletContext());

        commonsMultipartResolver.setDefaultEncoding("utf-8");
        commonsMultipartResolver.setMaxUploadSize(3*1024*1024);
        commonsMultipartResolver.setMaxInMemorySize(1024*1024*1024);
        commonsMultipartResolver.setMaxUploadSizePerFile(1024*1024);

        MultipartHttpServletRequest multipartHttpServletRequest = commonsMultipartResolver.resolveMultipart(request);

        MultipartFile file = multipartHttpServletRequest.getFile("file0");
        String type = file.getContentType();
        String filename = file.getOriginalFilename();
        String path = "upload"+ File.separator+filename;
        File upfile = new File(path);
        Bbsuser bbsuser = new Bbsuser();

        try {
            file.transferTo(upfile);

            String username = multipartHttpServletRequest.getParameter("reusername");
            String password = multipartHttpServletRequest.getParameter("repassword");

            bbsuser.setUsername(username);
            bbsuser.setPassword(password);
            bbsuser.setPicPath(path);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bbsuser;

    }

    public Bbsuser save(Bbsuser bbsuser){
        return  dao.save(bbsuser);
    }

    public boolean exists(String username){
        if (dao.exists(username)!=null){
            return true;
        }
        return false;
    }


    public Bbsuser queryPicByid(int id){
        return  dao.queryPicByid(id);
    }



}
