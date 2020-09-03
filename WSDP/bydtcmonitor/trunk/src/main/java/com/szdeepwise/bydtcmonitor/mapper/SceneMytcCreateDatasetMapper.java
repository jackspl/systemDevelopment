package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneMytcCreateDataset;

public interface SceneMytcCreateDatasetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneMytcCreateDataset record);

    int insertSelective(SceneMytcCreateDataset record);

    SceneMytcCreateDataset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneMytcCreateDataset record);

    int updateByPrimaryKey(SceneMytcCreateDataset record);
}