package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TuijianPinZhongDao extends JpaRepository<TuijianPinZhong,Integer> {

    @Query(value = "select * from nzw_tuijian_pin_zhong where num = ?1 and year in (select max(year) from nzw_tuijian_pin_zhong)" , nativeQuery = true)
    public List<TuijianPinZhong> findYearMax(String num);

}
