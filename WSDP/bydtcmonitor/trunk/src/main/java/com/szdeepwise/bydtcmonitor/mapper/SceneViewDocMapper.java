package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneViewDoc;

public interface SceneViewDocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneViewDoc record);

    int insertSelective(SceneViewDoc record);

    SceneViewDoc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneViewDoc record);

    int updateByPrimaryKey(SceneViewDoc record);
}