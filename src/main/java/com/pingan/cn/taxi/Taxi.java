package com.pingan.cn.taxi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "taxi")
@NoArgsConstructor
@AllArgsConstructor
public class Taxi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String VehicleNum;
    private String Time;
    private Double Lng;
    private Double Lat;
    private String OpenStatus;
    private Double Speed;

}
