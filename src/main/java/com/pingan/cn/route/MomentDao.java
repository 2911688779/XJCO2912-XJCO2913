package com.pingan.cn.route;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MomentDao extends JpaRepository<Moment, String> {
   List<Moment> findAllByUserId (String userId);
   List<Moment> findAllByUserIdOrderByMomentTimeDesc(String userId);

   @Query(value = "select * from joy_moment jm where jm.user_id in(:userIds) order by jm.moment_time desc", nativeQuery = true)
   List<Moment> findByUserIds(@Param(value = "userIds") List<String> userIds);
}
