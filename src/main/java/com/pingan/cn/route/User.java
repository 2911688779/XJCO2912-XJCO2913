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
@Table(name = "joy_user") //pg库不能用user表
public class User implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(unique = true)
    private String username;
    @Column
    private String password;

    @Column
    private String role;//用户的角色

    private String phoneNum;//手机号码

    private String email;//email

    private String[] friendIds;

    private String[] follows;

    @Lob
    private String avatarImg;

    private Integer cost = 0;  // vip cost

    private Date vipDueDate = new Date(); // Due date , If the expiration date is less than the current date, it is not a member

    private String[] cardType;
}
