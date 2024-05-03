package com.pingan.cn.lish.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;

    //学期
    @Enumerated(EnumType.STRING)
    private Semester semester;

    //成绩
    @Lob
    @Column
    private String records; //存json串

}
