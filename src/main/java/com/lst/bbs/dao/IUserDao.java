package com.lst.bbs.dao;

import com.lst.bbs.po.Bbsuser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by LiuSitong on 2017/5/10.
 */
public interface IUserDao extends CrudRepository<Bbsuser,Integer>{


    @Query("select u from Bbsuser  u where username=:u and password=:p")
    public Bbsuser login(@Param(value = "u") String username, @Param(value = "p") String userId);

    @Query("select c from Bbsuser c where userid=:id")//HQL
    Bbsuser queryPicByid(@Param(value="id") Integer id);


    @Override
    Bbsuser save(Bbsuser s);

    @Query("select c.username from Bbsuser c where username=:username")
    String  exists(@Param(value = "username") String username);
}
