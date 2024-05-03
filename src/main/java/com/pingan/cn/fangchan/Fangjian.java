package com.pingan.cn.fangchan;

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
@Table(name = "fangchan_fangjian")
@NoArgsConstructor
@AllArgsConstructor
public class Fangjian {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 楼名、层名、房间号、
    private String lou;
    private String ceng;
    private String num;
}
