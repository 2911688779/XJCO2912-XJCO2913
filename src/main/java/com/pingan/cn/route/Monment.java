package com.pingan.cn.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "joy_moment")
public class Monment implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String content; //

    private String userId; // 发起人

    private String monment_time;

    private String[] imageIds; // 活动照片

    private String[] loverIds; //
}
