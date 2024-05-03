package com.pingan.cn.chaland;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.geolatte.geom.Polygon;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cha_land")
@NoArgsConstructor
@AllArgsConstructor
public class ChaLand {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
//    编号、类型、名称（人名）、所属镇、村、组、面积（亩）、茶叶类型、茶叶种植年份
    private String number; // 编号
    private String unitType; // 类型
    private String name;  // 名称（人名）
    private String town; // 镇
    private String countrySide; // 乡村
    private String village; // 自然村
    private Double area; // 亩
    private String blType; // 槟榔类型
    private Integer year;  // 槟榔种植年份
    private Integer landNo; // 地块编号，一般从1开始。2，3
    private String photoNames; // 图片的名称

    // mysql 库下注释
    @JsonIgnore
    @Column(name = "geometry", columnDefinition = "geometry(Polygon,4326)")
    private Polygon geometry;
}
