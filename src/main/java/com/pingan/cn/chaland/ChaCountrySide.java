package com.pingan.cn.chaland;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

// 村委会
@Data
@Entity
@Table(name = "cha_country_side")
@NoArgsConstructor
@AllArgsConstructor
public class ChaCountrySide {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    private String name;

    private String town_id; // 镇id
}
