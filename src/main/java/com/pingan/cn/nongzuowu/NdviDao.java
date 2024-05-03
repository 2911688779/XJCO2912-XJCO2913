package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NdviDao extends JpaRepository<Ndvi,Integer> {
    public List<Ndvi> findByDateLike(String day);

    @Query(value = "select * from nzw_ndvi c where DATE_FORMAT(date, '%Y-%m-%d') >= ?1 and  DATE_FORMAT(date, '%Y-%m-%d') <= ?2" , nativeQuery = true)
    public List<Ndvi> findByBetweenDate(String start,String end);
}
