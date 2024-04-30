package com.pingan.cn.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityDao extends JpaRepository<Activity,String> {

}
