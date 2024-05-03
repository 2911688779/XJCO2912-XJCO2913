package com.pingan.cn.chaland;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ChaLandDao extends JpaRepository<ChaLand,String> {

//    @Query(value = "select * from cha_land where 1=1 and if(?1 !='ALL', number like CONCAT('%',?1,'%') ,1=1 ) " +
//            "and if(?2 != 'ALL',unit_type = ?2 ,1=1) and if(?3 != 'ALL', name like CONCAT('%',?3,'%') ,1=1) and if(?4 != 'ALL',town = ?4 ,1=1) and if(?5 != 'ALL',country_side = ?5 ,1=1) " +
//            "and if(?6 != 'ALL',village = ?6 ,1=1) and area >= ?7 and if(?8 != 'ALL', bl_type like CONCAT('%',?8,'%') ,1=1) and  year > ?9  and  year< ?10 ",
//            countQuery = "SELECT count(*) FROM cha_land", nativeQuery = true)


    @Query(value = "select * from cha_land where number like CONCAT('%',?1,'%') " +
            "and unit_type like CONCAT('%',?2,'%') and name like CONCAT('%',?3,'%') and town like CONCAT('%',?4,'%') and country_side like CONCAT('%',?5,'%') " +
            "and village like CONCAT('%',?6,'%')  and area >= ?7 and bl_type like CONCAT('%',?8,'%') and  year > ?9  and  year< ?10 ",
            nativeQuery = true)
    Page<ChaLand> findByParams(String number, String unitType, String name, String town, String countrySide, String village, Double area, String blType,
                               int startYear, int endYear, Pageable pageable);


    ChaLand findByNumber(String number);

    @Query(value = "select distinct country_side from cha_land",nativeQuery = true)
    List<String> findDistinctCountrySide();

    @Query(value = "select distinct name from cha_land where unit_type = '公司'",nativeQuery = true)
    List<String> findDistinctName();

//    @Query(value = "select town, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land where town like CONCAT('%',?1,'%') and area >= ?2 and area <= ?3  and  year >= ?4  and  year <= ?5  group by town, unit_type",nativeQuery = true)
//    Page<List<Map<String,String>>> staticTownByParams(String xzqh, Double minArea, Double maxArea, int startYear, int endYear,Pageable pageable);
//
//    @Query(value = "select contry_side as xzqh, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land where contry_side like CONCAT('%',?1,'%') and area >= ?2 and area <= ?3  and  year >= ?4  and  year<= ?5 group by contry_side, unit_type",
//            nativeQuery = true)
//    Page<List<Map<String,String>>> staticContrySideByParams(String xzqh, Double minArea, Double maxArea, int startYear, int endYear,Pageable pageable);


    @Query(value = "select town as xzqh, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land where town like CONCAT('%',?1,'%') and area >= ?2 and area <= ?3  and  year >= ?4  and  year <= ?5  group by town, unit_type",nativeQuery = true)
    List<Map<String,Object>> staticTownByParams2(String xzqh, Double minArea, Double maxArea, int startYear, int endYear);

    @Query(value = "select country_side as xzqh, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land where town like CONCAT('%',?1,'%') and area >= ?2 and area <= ?3  and  year >= ?4  and  year<= ?5 group by country_side, unit_type",
            nativeQuery = true)
    List<Map<String,Object>> staticContrySideByParams2(String xzqh, Double minArea, Double maxArea, int startYear, int endYear);

    @Query(value = "select town, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land group by town, unit_type ",nativeQuery = true)
    List<Map<String,Object>> stasticXian();

    @Query(value = "select town, country_side, unit_type, sum(area) as totalArea, count(*) as totalNum from cha_land group by town, country_side, unit_type ",nativeQuery = true)
    List<Map<String,Object>>  stasticXiang();

    @Query(value = "select name, sum(area) as totalArea, count(*) as totalNum from cha_land where unit_type = '公司' group by name " ,nativeQuery = true)
    List<Map<String,Object>>  stasticByGs();
}
