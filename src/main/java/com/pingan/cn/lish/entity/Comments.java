package com.pingan.cn.lish.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Comments implements Serializable {

    private String say;

    private Date date = new Date();

    @Override
    public String toString() {
        return "Comments{" +
                "say='" + say + '\'' +
                ", date=" + date +
                '}';
    }
}
