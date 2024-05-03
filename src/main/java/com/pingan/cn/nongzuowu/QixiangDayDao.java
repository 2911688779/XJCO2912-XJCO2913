package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QixiangDayDao extends JpaRepository<QixiangDay,Integer> {
    public List<QixiangDay> findByDateLike(String day);

    @Query(value = "select * from nzw_qixiang_day c where DATE_FORMAT(date, '%Y-%m-%d') >= ?1 and  DATE_FORMAT(date, '%Y-%m-%d') <= ?2" , nativeQuery = true)
    public List<QixiangDay> findByBetweenDate(String start,String end);
}
