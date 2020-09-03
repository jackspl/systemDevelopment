package com.szdeepwise.bydtcmonitor.mapper;

import com.szdeepwise.bydtcmonitor.entity.SceneCancelCheckout;

public interface SceneCancelCheckoutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SceneCancelCheckout record);

    int insertSelective(SceneCancelCheckout record);

    SceneCancelCheckout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SceneCancelCheckout record);

    int updateByPrimaryKey(SceneCancelCheckout record);
}