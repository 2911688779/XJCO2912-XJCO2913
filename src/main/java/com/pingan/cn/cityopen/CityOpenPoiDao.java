package com.pingan.cn.cityopen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityOpenPoiDao extends JpaRepository<CityOpenPoi,Integer> {

//    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') = ?1 " , nativeQuery = true)
//    public List<Poi> findByDate(String date);
//
//    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') >= ?1 and  DATE_FORMAT(date, '%Y-%m-%d') <= ?2" , nativeQuery = true)  // # 支持mysql, 不支持pg库
//    public List<Poi> findByBetweenDate(String start, String end);

//    @Query(value = "SELECT DISTINCT ON (lng, lat) *  FROM taxi  WHERE time BETWEEN ?1 AND ?2" , nativeQuery = true)  // # 支持mysql, 不支持pg库
//    public List<Poi> findDistinctLngLatByBetweenDate(String start, String end);

//    @Query(value = "SELECT *  FROM taxi order by time desc limit 10000" , nativeQuery = true)  // # 支持mysql, 不支持pg库
//    public List<Poi> findAllLimit10000();
}
