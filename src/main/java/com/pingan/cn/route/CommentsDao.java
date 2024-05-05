package com.pingan.cn.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsDao extends JpaRepository<Comments, String> {

    List<Comments> findAllByIdIn(String[] ids);
}
