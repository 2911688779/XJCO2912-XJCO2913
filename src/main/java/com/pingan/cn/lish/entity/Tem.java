package com.pingan.cn.lish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tem")
@NoArgsConstructor
@AllArgsConstructor
public class Tem {
    @Id
//    @Column(nullable = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String stationNum;
    private Long lat;
    private Long lng;
    private Double alt;
    private String year;
    private String month;
    private String day;
    private Double pj;
    private Double zg;
    private Double zd;
}
