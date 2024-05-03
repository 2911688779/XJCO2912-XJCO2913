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
@Table(name = "fangxun_qu")
@NoArgsConstructor
@AllArgsConstructor
public class Qu {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 区名子， 降水面积 ， 降雨量， 限制水位，实时水位, 抢险人员
    private String quName;
    private Double jsArea;
    private Double jyl;
    private Double swLimit;
    private Double swReal;
    private Integer people;
}
