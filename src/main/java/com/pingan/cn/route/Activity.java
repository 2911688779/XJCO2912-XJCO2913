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
@Table(name = "joy_activity")
public class Activity implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(unique = true)
    private String name; // activity name

    @Column(unique = true)
    private String userId; // 发起人

    @Column
    private String address;

    @Column
    private String routes; // 活动路线

    private String activity_time;

    private String content;

    private String[] imageIds; // 活动照片

    private String[] userIds; // 参与人员
}
