package com.pingan.cn.nongzuowu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ZhongZhiQuDao extends JpaRepository<ZhongZhiQu,Integer> {

    @Query(value = "select distinct(region) from nzw_zhong_zhi_qu " , nativeQuery = true)
    public List<String> findDistinctRegion();
}
