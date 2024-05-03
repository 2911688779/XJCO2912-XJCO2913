package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "农作物-生产力")
@RestController
@RequestMapping(value = "/api/shengChanLi")
public class ShengChanLiController {

    @Autowired
    private ShengChanLiService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody ShengChanLi action){

        ShengChanLi result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody ShengChanLi action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<ShengChanLi> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable Integer id){
        ShengChanLi action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable Integer id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody Integer[] ids){
        boolean action = actionService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByDate")
    @GetMapping(value = "/findByDate/{date}")
    public @ResponseBody ResponseUtil findByDate(@PathVariable String date){
        List<ShengChanLi> action = actionService.findByDate(date);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByBetweenDate")
    @GetMapping(value = "/findByBetweenDate/{startdate}/{enddate}")
    public @ResponseBody ResponseUtil findByBetweenDate(@PathVariable String startdate,@PathVariable String enddate){
        List<ShengChanLi> action = actionService.findByBetweenDate(startdate, enddate);
        return ResponseUtil.builder().success(true).data(action).build();
    }
}
