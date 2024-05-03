package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;


@Data
@Entity
@Table(name = "nzwPinZhong")
@NoArgsConstructor
@AllArgsConstructor
public class PinZhong {
    /**
     number         品种编号
     review_number	VARCHAR	20	Y	Y	N	过审编号
     name	VARCHAR	20	N	N	N	品种名称
     type	VARCHAR	20	N	N	N	作物品种类型
     technical 技术要点
     characteristic	VARCHAR	50	N	N	N	特征
     sowing_time	VARCHAR	20	N	N	N	播种时间
     harvest_time	VARCHAR	20	N	N	N	产量现状
     breeding_units	VARCHAR	20	N	N	N	选育单位
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;
    private String review_number;
    private String name;
    private String type;
//    煮熟 Lob , 解决pg库 不良类型long 问题错.
//    @Lob下增加一行注解@Type(type = “org.hibernate.type.TextType”) 来声明具体的类型
//    参考 https://blog.csdn.net/u013569853/article/details/126020419
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Basic(fetch=LAZY)
    @Column(columnDefinition = "text")
    private String technical;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Basic(fetch=LAZY)
    @Column(columnDefinition = "text")
    private String characteristic;
    private String sowing_time;
    private String yield_performance;
    private String breeding_units;
}
