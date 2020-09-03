package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCloseFolderNewstuff;

public interface SceneCloseFolderNewstuffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCloseFolderNewstuff record);

    int insertSelective(SceneCloseFolderNewstuff record);

    SceneCloseFolderNewstuff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCloseFolderNewstuff record);

    int updateByPrimaryKey(SceneCloseFolderNewstuff record);
}