package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.service.HuangheService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "")
@RestController
@RequestMapping(value = "/api/huanghe")
public class HuangheController {
    @Autowired
    private HuangheService huangheService;

    @GetMapping(value = "/findList/{lng}/{lat}")
    public ResponseUtil findList(@PathVariable String lng, @PathVariable String lat) {
        return huangheService.findByConditions(lng,lat);
    }

    @GetMapping(value = "/parseLF/{fileName}")
    public ResponseUtil parseLF(@PathVariable String fileName) {
        return huangheService.parseLFFile(fileName);
    }

    @GetMapping(value = "/parseGP/{fileName}/{num}")
    public ResponseUtil parseGP(@PathVariable String fileName,@PathVariable int num) {
        return huangheService.parseGPFile(fileName,num);
    }

    @GetMapping(value = "/findLatest5file")
    public ResponseUtil findLatest5file() {
        return huangheService.findLatest5file();
    }
}
