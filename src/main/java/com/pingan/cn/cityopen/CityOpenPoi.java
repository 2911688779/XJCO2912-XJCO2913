package com.pingan.cn.cityopen;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "cityopen_poi")
@NoArgsConstructor
@AllArgsConstructor
public class CityOpenPoi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String type;
    private String address;
    private Double jd;
    private Double wd;
    private String pname;
    private String citycode;
    private String cityname;
    private String adcode;
    private String adname;
}
