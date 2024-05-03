package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.HuangheConfig;
import com.pingan.cn.lish.service.HuangheConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "")
@RestController
@RequestMapping(value = "/api/huangheconfig")
public class HuangheConfigController {
    @Autowired
    private HuangheConfigService huangheConfigService;

    //通用查找所有接口
    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<HuangheConfig> actionsList = huangheConfigService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }
}
