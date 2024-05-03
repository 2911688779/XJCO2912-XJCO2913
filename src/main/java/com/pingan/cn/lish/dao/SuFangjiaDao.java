package com.pingan.cn.lish.dao;

import com.pingan.cn.lish.entity.SuFangjia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuFangjiaDao extends JpaRepository<SuFangjia, Integer > {

    @Query(value = "select * from su_fangjia where if(?1 != 'ALL',xqmc like CONCAT('%',?1,'%') , 1=1) and if(?2 != 'ALL',xzq = ?2, 1=1) and if(?3 != 'ALL',dz like CONCAT('%',?3,'%'), 1=1) " +
            "and if(?4 != 'ALL',wylx = ?4, 1=1) and jznd >= ?5 and jj > ?6 and jj < ?7", nativeQuery = true)
    List<SuFangjia> findParams(String xqmc, String xzq, String jd, String wylx, int jznd, double jj_low, double jj_high);

    @Query(value = "select * from su_fangjia where 1 = 1 order by jj asc ", nativeQuery = true)
    List<SuFangjia> findAllOrderByJjDesc();

    List<SuFangjia> findByXqmcLike(String xqmc);
}
