package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.Tem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemDao extends JpaRepository<Tem,Long> {

    public List<Tem> findByYearAndMonthAndDay(String year,String month,String day);
    public List<Tem> findByYearLikeAndMonthLikeAndDayLike(String year,String month,String day);

}
