package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCheckinDataset;

public interface SceneCheckinDatasetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCheckinDataset record);

    int insertSelective(SceneCheckinDataset record);

    SceneCheckinDataset selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCheckinDataset record);

    int updateByPrimaryKey(SceneCheckinDataset record);
}