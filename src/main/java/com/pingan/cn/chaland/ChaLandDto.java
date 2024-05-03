package com.pingan.cn.chaland;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChaLandDto{
    private String number; // 编号
    private String unitType; // 类型
    private String name;  // 名称（人名）
    private String town; // 镇
    private String countrySide; // 乡村
    private String village; // 自然村
    private double area; // 亩
    private String blType; // 槟榔类型
    private int year;  // 槟榔种植年份
    private Integer landNo; // 地块编号，一般从1开始。2，3
    private String photoNames; // 图片的名称

    private String wkt;
}
