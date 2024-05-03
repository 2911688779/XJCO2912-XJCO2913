package com.pingan.cn.lmsg;


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
@Table(name = "shigong_file")
@NoArgsConstructor
@AllArgsConstructor
public class Sgfile {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;
//    private String number; // 文件编号
    private String name;     // 名称
    private String datetime;     // 日期
    private String username; // 经办人名称
    private String info;    //描述
    private String filepath; // 文件路径
}
