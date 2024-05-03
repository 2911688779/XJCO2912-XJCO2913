package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.Semester;
import com.pingan.cn.lish.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDao extends JpaRepository<Student,String> {
   List<Student> findBySemester(Semester semester);
}
