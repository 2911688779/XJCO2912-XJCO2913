package com.pingan.cn.fangxun;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaiFengDao extends JpaRepository<TaiFeng,String> {

}
