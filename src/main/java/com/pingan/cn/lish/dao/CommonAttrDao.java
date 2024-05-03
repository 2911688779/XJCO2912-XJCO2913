package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.CommonAttr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonAttrDao extends JpaRepository<CommonAttr,String> {
    public CommonAttr findByV1(String v1);

    public CommonAttr findByV2(String v1);
}
