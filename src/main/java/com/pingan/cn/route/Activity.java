package com.pingan.cn.route;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

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

    @Column
    private String userId; // 发起人

    private String userName;

    @Column
    private String address;

    @Column
    private String activityType;

    @Column
    @Lob
    private String routes; // 活动路线

    private Date activityTime;

    @Lob
    private String content;

    private String[] base64Images; // 活动照片

    private String[] userNames; // 参与人员
}
