package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwTuijianPinZhong")
@NoArgsConstructor
@AllArgsConstructor
public class TuijianPinZhong {
    /**
     id	VARCHAR	20	Y	Y	Y	编号
     year	INT	10	N	N	N	推荐年份
     num	VARCHAR	20	N	N	N	城市编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String year;
    private String num; // 城市编号
    private String crop_type;
    private String crop_id;  // 过审编号
    private String input_time; // 数据录入时间
}
