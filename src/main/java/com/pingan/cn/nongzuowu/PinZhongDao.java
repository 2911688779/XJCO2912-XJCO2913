package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PinZhongDao extends JpaRepository<PinZhong,Integer> {

    public List<PinZhong> findByNumberIn(String[] numbers);

    @Query(value = "select distinct(type) from nzw_pin_zhong " , nativeQuery = true)
    public List<String> findDistinctType();

    @Query(value = "select * from nzw_pin_zhong where number in " +
            "( select distinct(crop_id) from nzw_tuijian_pin_zhong where year = ?1 and crop_type = ?3 and num in (select distinct(num) from nzw_zhong_zhi_qu where region = ?2) )" , nativeQuery = true)
    public List<PinZhong> findCondition(String year,String region,String type);
}
