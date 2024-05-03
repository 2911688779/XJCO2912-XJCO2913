package com.pingan.cn.fangxun;

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
@Table(name = "taifeng")
@NoArgsConstructor
@AllArgsConstructor
public class TaiFeng {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 时间	中心位置	风速风力	中心气压
    private String timeStr;
    private String location;
    private String fengsu;
    private String qiya;

}
