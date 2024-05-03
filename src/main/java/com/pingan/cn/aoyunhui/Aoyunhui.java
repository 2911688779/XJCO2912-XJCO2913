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
@Table(name = "aoyunhui_info")
@NoArgsConstructor
@AllArgsConstructor
public class Aoyunhui {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    private String num;     //第几届
    private String year;    //举办时间
    private String country; //国家
    private String city;    //城市
    private String position;//坐标
    private String content;    //描述

}
