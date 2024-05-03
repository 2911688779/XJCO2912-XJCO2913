package com.pingan.cn.lish.entity;


import lombok.Data;

import javax.persistence.*;

// 苏州小区房价
@Data
@Entity
@Table(name = "SuFangjia")
public class SuFangjia {
    //   小区名称	地址	均价	环比上月	物业类型	总建面积	建筑年代	容积率	开发商	物业公司	相关学校	物业费	总户数	停车位	绿化率	经度	纬度

//    @Id
//    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "idGenerator")
//    private String id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String xqmc;
    private String dz;
    private double jj;
    private String hbsy;
    private String wylx;
    private String zjmj;
    private int jznd;
    private String rjl;
    private String kfs;
    private String wygs;
    private String xgxx;
    private String wyf;
    private String zhs;
    private String tcw;
    private String lhl;
    private double lon;
    private double lat;
    private String xzq;  // 所属行政区


}
