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
@Table(name = "fangchan_sy")
@NoArgsConstructor
@AllArgsConstructor
public class FangjianSy {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 房间id， 使用时间 ， 备注， 人数，
    private String fj_id;
    private String sy_time;
    private String info;
    private Integer people;
}
