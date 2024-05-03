package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzw_hebei")
@NoArgsConstructor
@AllArgsConstructor
public class Nzw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String city; // 日期
    private double year2012; // 2012
    private double year2013;
    private double year2014;
    private double year2015;
    private double year2016;
    private double year2017;
    private double year2018;
    private double year2019;
    private double year2020;
    private double year2021;
    private String type;
    private String unit;
}
