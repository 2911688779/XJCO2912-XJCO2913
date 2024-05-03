package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.Tem;
import com.pingan.cn.lish.service.TemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "qiwen")
@RestController
@RequestMapping(value = "/api/tem")
public class TemController {

    @Autowired
    private TemService temService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Tem action){

        Tem result = temService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Tem action){
        return ResponseUtil.builder().success(true).data(temService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Tem> actionsList = temService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable Long id){
        Tem action = temService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody Long[] ids){
        boolean action = temService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable Long id){
        boolean action = temService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByDate/{year}/{month}/{day}")
    @GetMapping(value = "/findByDate/{year}/{month}/{day}")
    public ResponseUtil findByDate(@PathVariable String year, @PathVariable String month, @PathVariable String day){
        List<Tem> actionsList = temService.findByDate(year,month,day);
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }
}
