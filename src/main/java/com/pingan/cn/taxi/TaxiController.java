package com.pingan.cn.taxi;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "上海-出租车")
@RestController
@RequestMapping(value = "/api/shanghaiTaxi")
public class TaxiController {

    @Autowired
    private TaxiService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Taxi action){
        Taxi result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Taxi action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Taxi> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable Integer id){
        Taxi action = actionService.findById(id);
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
        List<Taxi> action = actionService.findByDate(date);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByBetweenDate")
    @GetMapping(value = "/findByBetweenDate/{startdate}/{enddate}")
    public @ResponseBody ResponseUtil findByBetweenDate(@PathVariable String startdate,@PathVariable String enddate){
        List<Taxi> action = actionService.findByBetweenDate(startdate, enddate);
        return ResponseUtil.builder().success(true).data(action).build();
    }

//按时间范围查找经纬度一起唯一的数据行，过滤掉僵尸车
    @ApiOperation(value = "findDistinctLngLatByBetweenDate")
    @GetMapping(value = "/findDistinctLngLatByBetweenDate/{startdate}/{enddate}")
    public @ResponseBody ResponseUtil findDistinctLngLatByBetweenDate(@PathVariable String startdate,@PathVariable String enddate){
        List<Taxi> action = actionService.findDistinctLngLatByBetweenDate(startdate, enddate);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "countOnlineByHour")
    @GetMapping(value = "/countOnlineByHour")
    public @ResponseBody ResponseUtil countOnlineByHour(){
        List<Integer> action = actionService.countOnlineByHour();
        return ResponseUtil.builder().success(true).data(action).build();
    }
}
