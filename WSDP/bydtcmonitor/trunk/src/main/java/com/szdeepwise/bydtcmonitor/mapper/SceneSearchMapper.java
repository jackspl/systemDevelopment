package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneSearch;

public interface SceneSearchMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneSearch record);

    int insertSelective(SceneSearch record);

    SceneSearch selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneSearch record);

    int updateByPrimaryKey(SceneSearch record);
}