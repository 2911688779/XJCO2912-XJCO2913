package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.Poi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoiDao extends JpaRepository<Poi,String> {
}
