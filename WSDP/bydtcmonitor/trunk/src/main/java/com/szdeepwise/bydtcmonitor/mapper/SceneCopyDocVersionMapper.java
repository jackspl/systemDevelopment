package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCopyDocVersion;

public interface SceneCopyDocVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCopyDocVersion record);

    int insertSelective(SceneCopyDocVersion record);

    SceneCopyDocVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCopyDocVersion record);

    int updateByPrimaryKey(SceneCopyDocVersion record);
}