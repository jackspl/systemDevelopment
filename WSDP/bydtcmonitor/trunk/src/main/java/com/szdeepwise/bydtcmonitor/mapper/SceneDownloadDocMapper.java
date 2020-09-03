package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneDownloadDoc;

public interface SceneDownloadDocMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneDownloadDoc record);

    int insertSelective(SceneDownloadDoc record);

    SceneDownloadDoc selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneDownloadDoc record);

    int updateByPrimaryKey(SceneDownloadDoc record);
}