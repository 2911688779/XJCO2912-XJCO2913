package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.HuangheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuangheConfigDao extends JpaRepository<HuangheConfig,Integer> {

//    @Query(value = "select * from huanghe_config where 1 = 1",nativeQuery = true)
//    List<HuangheConfig> findAll();

}
