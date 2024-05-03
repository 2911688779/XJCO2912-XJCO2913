package com.pingan.cn.aoyunhui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "yundongyuan_info")
@NoArgsConstructor
@AllArgsConstructor
public class Yundongyuan {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;    //姓名
    private String city;    //籍贯
    private String position;  //坐标
    private String skill;   //擅长
    private String awards;  //获奖情况
    private String photo;   //照片
}
