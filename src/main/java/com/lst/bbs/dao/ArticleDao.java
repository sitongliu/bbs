package com.lst.bbs.dao;

import com.lst.bbs.po.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LiuSitong on 2017/5/14.
 */
public interface ArticleDao extends CrudRepository<Article,Integer> {


    /**
     * 分页查询
     * @param pageable
     * @param rootid
     * @return
     */
    @Query("select a from Article a where rootid=:rootid")
    Page<Article> findAll(Pageable pageable , @Param(value = "rootid") Integer rootid);

    @Modifying
    @Query("delete from Article where rootid=:rootid or id=:id")
     void delete(@Param(value = "rootid") Integer rootid,@Param(value = "id") Integer id);




}
