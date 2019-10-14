package com.hbh.blog.dao;

import com.hbh.blog.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {
    int deleteByPrimaryKey(String account);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(String account);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
}