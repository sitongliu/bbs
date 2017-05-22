package com.lst.bbs.service;

import com.lst.bbs.dao.ArticleDao;
import com.lst.bbs.po.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by LiuSitong on 2017/5/14.
 */
@Service
@Transactional
public class ArticleService {
    @Autowired
    ArticleDao articleDao = null;

    public Page<Article> findAll(Pageable pageable ,Integer rootid){
        return articleDao.findAll(pageable , rootid);
    }

    public void delete( Integer rootid,Integer id){
        articleDao.delete(rootid,id);
    }

}
