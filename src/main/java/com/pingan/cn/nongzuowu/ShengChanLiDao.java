package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShengChanLiDao extends JpaRepository<ShengChanLi,Integer> {

    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') = ?1 " , nativeQuery = true)
    public List<ShengChanLi> findByDate(String date);

    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') >= ?1 and  DATE_FORMAT(date, '%Y-%m-%d') <= ?2" , nativeQuery = true)  // # 支持mysql, 不支持pg库
    public List<ShengChanLi> findByBetweenDate(String start,String end);

}
