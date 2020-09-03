package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneLogin;

public interface SceneLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneLogin record);

    int insertSelective(SceneLogin record);

    SceneLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneLogin record);

    int updateByPrimaryKey(SceneLogin record);
}