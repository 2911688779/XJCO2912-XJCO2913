package com.pingan.cn.lish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


/**
 * 说明，该表手动添加字段，一一对应huanghe表字段名和日期名，一次性全部读取。
 */
@Data
@Entity
@Table(name = "huanghe_config")
@NoArgsConstructor
@AllArgsConstructor
public class HuangheConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 字段名
    private String fieldName;
    // 日期别名
    private String dateAlias;


}
