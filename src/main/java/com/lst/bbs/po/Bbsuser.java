package com.lst.bbs.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

/**
 * Created by LiuSitong on 2017/5/8.
 */
@Entity
public class Bbsuser {
    private int userid;
    private String username;
    private String password;
    private byte[] pic;
    private Integer pagenum;
    private String picPath;

    @Id
    @Column(name = "userid")
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "pic")
    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    @Basic
    @Column(name = "PAGENUM")
    public Integer getPagenum() {
        return pagenum;
    }

    public void setPagenum(Integer pagenum) {
        this.pagenum = pagenum;
    }

    @Basic
    @Column(name = "pic_path")
    public String getPicPath() {
        return picPath;
    }

    public void setPicPath(String picPath) {
        this.picPath = picPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bbsuser bbsuser = (Bbsuser) o;

        if (userid != bbsuser.userid) return false;
        if (username != null ? !username.equals(bbsuser.username) : bbsuser.username != null) return false;
        if (password != null ? !password.equals(bbsuser.password) : bbsuser.password != null) return false;
        if (!Arrays.equals(pic, bbsuser.pic)) return false;
        if (pagenum != null ? !pagenum.equals(bbsuser.pagenum) : bbsuser.pagenum != null) return false;
        if (picPath != null ? !picPath.equals(bbsuser.picPath) : bbsuser.picPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(pic);
        result = 31 * result + (pagenum != null ? pagenum.hashCode() : 0);
        result = 31 * result + (picPath != null ? picPath.hashCode() : 0);
        return result;
    }
}
