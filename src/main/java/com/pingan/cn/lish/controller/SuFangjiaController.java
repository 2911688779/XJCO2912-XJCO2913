package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.SuFangjia;
import com.pingan.cn.lish.service.SuFangjiaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "苏州小区房价")
@RestController
@RequestMapping(value = "/api/sufangjia")
public class SuFangjiaController {

    @Autowired
    private SuFangjiaService suFangjiaService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody SuFangjia suFangjia){
        return suFangjiaService.save(suFangjia);
    }

    //通用编辑接口，传入实体，其中id必传
    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil update(@RequestBody SuFangjia common){
        return ResponseUtil.builder().success(true).data(suFangjiaService.update(common)).build();
    }
//    查找所有
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return suFangjiaService.findAll();
    }

    //    查找所有 倒序排名
    @GetMapping(value = "/findAllDesc")
    public ResponseUtil findAllDesc(){
        return suFangjiaService.findAllDesc();
    }

//    根据名称查找
    @ApiOperation(value = "findByXqmcLike/{xqmc}")
    @GetMapping(value = "/findByXqmcLike/{xqmc}")
    public @ResponseBody ResponseUtil findByXqmcLike(@PathVariable String xqmc){
        List<SuFangjia> common = suFangjiaService.findByXqmcLike("%"+xqmc+"%");
        return ResponseUtil.builder().success(true).data(common).build();
    }

//    按条件查询
    @ApiOperation(value = "findParams/{xqmc}/{xzq}/{jd}/{wylx}/{jznd}/{jj_low}/{jj_high}")
    @GetMapping(value = "/findParams/{xqmc}/{xzq}/{jd}/{wylx}/{jznd}/{jj_low}/{jj_high}")
    public @ResponseBody ResponseUtil findParams(@PathVariable String xqmc,@PathVariable String xzq,@PathVariable String jd,
                                                    @PathVariable String wylx,@PathVariable int jznd, @PathVariable double jj_low, @PathVariable double jj_high){
        List<SuFangjia> common = suFangjiaService.findParams(xqmc,xzq,jd,wylx,jznd,jj_low,jj_high);
        return ResponseUtil.builder().success(true).data(common).build();
    }

//    按id删除接口
    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable int id){
        boolean common = suFangjiaService.deleteById(id);
        return ResponseUtil.builder().success(true).data(common).build();
    }
}
