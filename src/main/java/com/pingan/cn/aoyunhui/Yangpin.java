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
@Table(name = "yangpin_info")
@NoArgsConstructor
@AllArgsConstructor
public class Yangpin {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String type; // 新加类型，五类
    private String yangpin;
    private String c;
    private String h;
    private String o;
    private String n;
    private String s;
    private String vm;
    private String fc;
    private String ash;
    private String hhv;

}
