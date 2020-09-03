package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneGeneralCut;

public interface SceneGeneralCutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneGeneralCut record);

    int insertSelective(SceneGeneralCut record);

    SceneGeneralCut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneGeneralCut record);

    int updateByPrimaryKey(SceneGeneralCut record);
}