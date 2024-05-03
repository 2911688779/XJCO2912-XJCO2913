package com.pingan.cn.lish.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Movie implements Serializable {

    private String name;
    private String director;
    private MovieType type;
    private String content;

    private Long  commentNum;
}
