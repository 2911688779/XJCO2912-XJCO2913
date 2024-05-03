package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwNdvi")
@NoArgsConstructor
@AllArgsConstructor
public class Ndvi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date; // 日期
    private double min; // 最小值
    private double max; // 最大值
    private double mean; //均值
    private double std; //方差
}
