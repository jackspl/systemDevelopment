package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneOpenFolder;

public interface SceneOpenFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneOpenFolder record);

    int insertSelective(SceneOpenFolder record);

    SceneOpenFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneOpenFolder record);

    int updateByPrimaryKey(SceneOpenFolder record);
}