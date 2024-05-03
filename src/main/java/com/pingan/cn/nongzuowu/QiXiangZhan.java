package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwQiXiangZhan")
@NoArgsConstructor
@AllArgsConstructor
public class QiXiangZhan {
    /**
     id	VARCHAR	20	Y	Y	N	编号
     position	VARCHAR	30	N	N	N	位置（经度,纬度）
     city	VARCHAR	30	N	N	N	所在城市
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String st_num;
    private String position;
    private String city;
}
