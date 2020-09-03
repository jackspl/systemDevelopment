package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneReviseDocVersion;

public interface SceneReviseDocVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneReviseDocVersion record);

    int insertSelective(SceneReviseDocVersion record);

    SceneReviseDocVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneReviseDocVersion record);

    int updateByPrimaryKey(SceneReviseDocVersion record);
}