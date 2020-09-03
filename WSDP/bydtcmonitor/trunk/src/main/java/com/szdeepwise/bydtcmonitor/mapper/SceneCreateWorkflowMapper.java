package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCreateWorkflow;

public interface SceneCreateWorkflowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCreateWorkflow record);

    int insertSelective(SceneCreateWorkflow record);

    SceneCreateWorkflow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCreateWorkflow record);

    int updateByPrimaryKey(SceneCreateWorkflow record);
}