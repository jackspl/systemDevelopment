package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneGeneralCopy;

public interface SceneGeneralCopyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneGeneralCopy record);

    int insertSelective(SceneGeneralCopy record);

    SceneGeneralCopy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneGeneralCopy record);

    int updateByPrimaryKey(SceneGeneralCopy record);
}