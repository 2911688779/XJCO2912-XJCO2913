package com.pingan.cn.nongzuowu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@Table(name = "nzwShengChanLi")
@NoArgsConstructor
@AllArgsConstructor
public class ShengChanLi {
    /**
     id	VARCHAR	20	Y	Y	Y	编号
     date	VARCHAR	20	N	N	N	日期
     st_num	VARCHAR	30	N	N	N	气象站号
     evap	Double	30	N	N	N	逐日作物蒸散量
     pvi	Double	30	N	N	N	逐日作物生产力
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;
    private String st_num;
    private Double evap;
    private Double pvi;
}
