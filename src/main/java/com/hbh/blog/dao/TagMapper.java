package com.hbh.blog.dao;

import com.hbh.blog.entity.Tag;

public interface TagMapper {
    int deleteByPrimaryKey(Long tagId);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Long tagId);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
}