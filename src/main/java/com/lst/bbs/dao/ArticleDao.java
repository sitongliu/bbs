package com.lst.bbs.dao;

import com.lst.bbs.po.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LiuSitong on 2017/5/14.
 */
public interface ArticleDao extends CrudRepository<Article,Integer> {


    @Query("select a from Article a where rootid=:rootid")
    Page<Article> findAll(Pageable pageable , @Param(value = "rootid") Integer rootid);
}
