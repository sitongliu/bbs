package com.lst.bbs.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by LiuSitong on 2017/5/14.
 */
@Entity
public class Article {
    private int id;
    private int rootid;
    private String content;
    private Timestamp datetime;
    private String title;
    private Bbsuser user;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rootid")
    public int getRootid() {
        return rootid;
    }

    public void setRootid(int rootid) {
        this.rootid = rootid;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "datetime")
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (rootid != article.rootid) return false;
        if (content != null ? !content.equals(article.content) : article.content != null) return false;
        if (datetime != null ? !datetime.equals(article.datetime) : article.datetime != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + rootid;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    public Bbsuser getUser() {
        return user;
    }

    public void setUser(Bbsuser user) {
        this.user = user;
    }
}
