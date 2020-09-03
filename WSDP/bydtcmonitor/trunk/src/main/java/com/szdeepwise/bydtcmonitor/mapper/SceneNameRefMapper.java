package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneNameRef;

public interface SceneNameRefMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneNameRef record);

    int insertSelective(SceneNameRef record);

    SceneNameRef selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneNameRef record);

    int updateByPrimaryKey(SceneNameRef record);
}