package com.pingan.cn.fangchan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fangchan_weixiu")
@NoArgsConstructor
@AllArgsConstructor
public class Weixiu {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    // 房间id, 房间其他信息 维修事项 是否已维修 申报时间 处理时间 维修结果
    private String fj_id;
    private String fj_info;
    private String wx_info;
    private Boolean isWx;
    private String date_request;
    private String date_done;
    private String wx_result;

}
