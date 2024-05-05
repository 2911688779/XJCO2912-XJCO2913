package com.pingan.cn.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

//@Setter
//@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "joy_moment")
public class Moment implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String content; //

    @Column
    private String userId; // 发起人

    @Column
    private String userName; // 发起人

    @Column
    private Date momentTime = new Date();

    @Column
    private String[] base64Images; // 活动照片

    @OneToMany(mappedBy = "moment", cascade = {CascadeType.ALL})
    @JsonIgnoreProperties(value="moment")
//    @JoinColumn(name = "fk_comments_id")
    private List<Comments> comments;

//    private String[] loverIds; //


}
