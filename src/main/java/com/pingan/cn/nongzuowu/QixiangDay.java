package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwQixiangDay")
@NoArgsConstructor
@AllArgsConstructor
public class QixiangDay {
    /**
     id	VARCHAR	20	Y	Y	Y	编号
     date	VARCHAR	20	N	N	N	日期
     st_num	VARCHAR	30	N	N	N	气象站号
     aver_tem	VARCHAR	30	N	N	N	平均温度
     max_tem	VARCHAR	30	N	N	N	最高温度
     min_tem	VARCHAR	30	N	N	N	最低温度
     pre	VARCHAR	30	N	N	N	降水
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String date;
    private String st_num;
    private double aver_tem;
    private double max_tem;
    private double min_tem;
    private double pre;
}
