package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCreateFolder;

public interface SceneCreateFolderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCreateFolder record);

    int insertSelective(SceneCreateFolder record);

    SceneCreateFolder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCreateFolder record);

    int updateByPrimaryKey(SceneCreateFolder record);
}