package com.pingan.cn.lish.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 魔芋实体
 */
@Data
@Entity
@Table(name = "moyu")
public class Moyu {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    //海拔 温度 湿度 ph 地区 光照强度
    @ApiModelProperty(name = "经度")
    @Column
    private Double lng;//经度
    @ApiModelProperty(name = "维度")
    private Double lat;//维度
    @ApiModelProperty(name = "海拔")
    private Double height;//海拔
    @ApiModelProperty(name = "温度")
    private Double tem;//温度
    @ApiModelProperty(name = "湿度")
    private Double humidity;//湿度
    @ApiModelProperty(name = "ph值")
    private Double ph;
    @ApiModelProperty(name = "光照强度")
    private Double beam;
    @ApiModelProperty(name = "月份")
    private Integer month;

    @ApiModelProperty(name = "地区，YUNNAN, SICUAN, GUANGXI")
    private DiquNames diqu;
}
