package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.ScenePasteDocVersion;

public interface ScenePasteDocVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ScenePasteDocVersion record);

    int insertSelective(ScenePasteDocVersion record);

    ScenePasteDocVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ScenePasteDocVersion record);

    int updateByPrimaryKey(ScenePasteDocVersion record);
}