package com.hbh.blog.dao;

import com.hbh.blog.entity.Note;

public interface NoteMapper {
    int deleteByPrimaryKey(Long noteId);

    int insert(Note record);

    int insertSelective(Note record);

    Note selectByPrimaryKey(Long noteId);

    int updateByPrimaryKeySelective(Note record);

    int updateByPrimaryKeyWithBLOBs(Note record);

    int updateByPrimaryKey(Note record);
}