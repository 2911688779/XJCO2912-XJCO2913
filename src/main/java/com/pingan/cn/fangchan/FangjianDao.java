package com.pingan.cn.fangchan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FangjianDao extends JpaRepository<Fangjian,String> {

}
