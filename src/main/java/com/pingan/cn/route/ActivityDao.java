package com.pingan.cn.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityDao extends JpaRepository<Activity,String> {
    List<Activity> findAllByOrderByActivityTimeDesc();
}
