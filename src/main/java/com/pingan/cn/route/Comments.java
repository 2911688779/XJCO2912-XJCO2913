package com.pingan.cn.route;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "joy_comments")
public class Comments implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column
    private String userName; // 评论人

    @Column
    private String content; //

    @ManyToOne(cascade = {CascadeType.MERGE} , fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_moment_id")
    @JsonIgnoreProperties(value="comments")
    private Moment moment;


}
