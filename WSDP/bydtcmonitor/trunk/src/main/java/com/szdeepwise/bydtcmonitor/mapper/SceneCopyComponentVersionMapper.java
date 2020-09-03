package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCopyComponentVersion;

public interface SceneCopyComponentVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCopyComponentVersion record);

    int insertSelective(SceneCopyComponentVersion record);

    SceneCopyComponentVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCopyComponentVersion record);

    int updateByPrimaryKey(SceneCopyComponentVersion record);
}