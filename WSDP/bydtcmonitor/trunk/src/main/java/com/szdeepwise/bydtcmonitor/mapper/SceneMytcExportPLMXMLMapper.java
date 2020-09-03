package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneMytcExportPLMXML;

public interface SceneMytcExportPLMXMLMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneMytcExportPLMXML record);

    int insertSelective(SceneMytcExportPLMXML record);

    SceneMytcExportPLMXML selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneMytcExportPLMXML record);

    int updateByPrimaryKey(SceneMytcExportPLMXML record);
}