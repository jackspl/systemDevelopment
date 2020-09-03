package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneOpenHome;

public interface SceneOpenHomeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneOpenHome record);

    int insertSelective(SceneOpenHome record);

    SceneOpenHome selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneOpenHome record);

    int updateByPrimaryKey(SceneOpenHome record);
}