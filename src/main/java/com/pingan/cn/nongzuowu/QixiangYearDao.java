package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QixiangYearDao extends JpaRepository<QixiangYear,Integer> {
    public List<QixiangYear> findByYear(int year);

}
