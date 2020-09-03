package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneMytcOpenDataset;

public interface SceneMytcOpenDatasetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneMytcOpenDataset record);

    int insertSelective(SceneMytcOpenDataset record);

    SceneMytcOpenDataset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneMytcOpenDataset record);

    int updateByPrimaryKey(SceneMytcOpenDataset record);
}