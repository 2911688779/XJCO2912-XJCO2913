package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.Poi;
import com.pingan.cn.lish.service.PoiService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Poi点")
@RestController
@RequestMapping(value = "/api/poi")
public class PoiController {

    @Autowired
    private PoiService poiService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Poi poi){
        return poiService.save(poi);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return poiService.findAll();
    }

}
