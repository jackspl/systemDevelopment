package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneOpenNewstuff;

public interface SceneOpenNewstuffMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneOpenNewstuff record);

    int insertSelective(SceneOpenNewstuff record);

    SceneOpenNewstuff selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneOpenNewstuff record);

    int updateByPrimaryKey(SceneOpenNewstuff record);
}