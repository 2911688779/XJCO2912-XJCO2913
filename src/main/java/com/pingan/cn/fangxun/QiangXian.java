package com.pingan.cn.fangxun;

import io.swagger.models.auth.In;
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
@Table(name = "fangxun_qiangxian")
@NoArgsConstructor
@AllArgsConstructor
public class QiangXian {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 地名	lon	lat	紧急安置	房屋倒塌	电路中断	公路中断	受灾人数	经济损失/万元 , 抢险人数
    private String quName;
    private Double lon;
    private Double lat;
    private Integer jjaz;
    private Integer fwdt;
    private Integer dlzd;
    private Integer glzd;
    private Integer szrs;
    private Double jjss;
    private Integer people;
}
