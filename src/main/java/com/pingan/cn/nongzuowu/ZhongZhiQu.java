package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwZhongZhiQu")
@NoArgsConstructor
@AllArgsConstructor
public class ZhongZhiQu {
    /**
     id	VARCHAR	20	Y	Y	N
     city	VARCHAR	20	N	N	N	城市名称
     position	VARCHAR	30	N	N	N	位置（经度,纬度）
     region	VARCHAR	20	N	N	N	农作区
     production_mode	VARCHAR	20	N	N	N	生产模式（种植作物类型）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String num; // 编号
    private String city;
    private String position;
    private String region;
    private String production_mode;
}
