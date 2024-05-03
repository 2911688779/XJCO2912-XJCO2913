package com.pingan.cn.lmsg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SgfileDao extends JpaRepository<Sgfile,String> {

}
