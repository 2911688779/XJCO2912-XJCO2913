package com.pingan.cn.taxi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaxiDao extends JpaRepository<Taxi,Integer> {

    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') = ?1 " , nativeQuery = true)
    public List<Taxi> findByDate(String date);

    @Query(value = "select * from nzw_sheng_chan_li c where DATE_FORMAT(date, '%Y-%m-%d') >= ?1 and  DATE_FORMAT(date, '%Y-%m-%d') <= ?2" , nativeQuery = true)  // # 支持mysql, 不支持pg库
    public List<Taxi> findByBetweenDate(String start, String end);

    @Query(value = "SELECT DISTINCT ON (lng, lat) *  FROM taxi  WHERE time BETWEEN ?1 AND ?2" , nativeQuery = true)  // # 支持mysql, 不支持pg库
    public List<Taxi> findDistinctLngLatByBetweenDate(String start, String end);

    @Query(value = "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '00:00:00' AND '01:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '01:00:00' AND '02:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '02:00:00' AND '03:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '03:00:00' AND '04:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '04:00:00' AND '05:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '05:00:00' AND '06:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '06:00:00' AND '07:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '07:00:00' AND '08:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '08:00:00' AND '09:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '09:00:00' AND '10:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '10:00:00' AND '11:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '11:00:00' AND '12:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '12:00:00' AND '13:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '13:00:00' AND '14:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '14:00:00' AND '15:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '15:00:00' AND '16:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '16:00:00' AND '17:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '17:00:00' AND '18:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '18:00:00' AND '19:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '19:00:00' AND '20:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '20:00:00' AND '21:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '21:00:00' AND '22:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '22:00:00' AND '23:00:00'\n" +
            "UNION ALL\n" +
            "SELECT count(DISTINCT(lng, lat)) FROM taxi WHERE open_status = '1' AND time BETWEEN '23:00:00' AND '24:00:00'"  , nativeQuery = true)
    public List<Integer> countOnlineByHour();


    @Query(value = "SELECT *  FROM taxi order by time desc limit 10000" , nativeQuery = true)  // # 支持mysql, 不支持pg库
    public List<Taxi> findAllLimit10000();
}
