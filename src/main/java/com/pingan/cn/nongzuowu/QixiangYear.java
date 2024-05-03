package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwQixiangYear")
@NoArgsConstructor
@AllArgsConstructor
public class QixiangYear {
    /**
     id	VARCHAR	20	Y	Y	Y	编号
     year	INT	10	N	N	N	年
     st_num	VARCHAR	30	N	N	N	气象站号
     aa_0	float	20	N	N	N	≥0℃活动积温
     ea_0	float	20	N	N	N	≥0℃有效积温
     aa_5	float	20	N	N	N	≥5℃活动积温
     ea_5	float	20	N	N	N	≥5℃有效积温
     aa_10	float	20	N	N	N	≥10℃活动积温
     ea_10	float	20	N	N	N	≥10℃有效积温
     ar	float	20	N	N	N	年降水量
     */
//    @Id
//    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
//    @GeneratedValue(generator = "idGenerator")
//    private String id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int year;
    private String st_num;
    private double aa_0;
    private double ea_0;
    private double aa_5;
    private double ea_5;
    private double aa_10;
    private double ea_10;
    private double ar;
}
