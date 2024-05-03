package com.pingan.cn.nongzuowu;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "农作物-品种")
@RestController
@RequestMapping(value = "/api/pinZhong")
public class PinZhongController {

    @Autowired
    private PinZhongService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody PinZhong action){

        PinZhong result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody PinZhong action){
        return ResponseUtil.builder().success(true).data(actionService.update(action)).build();
    }

    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<PinZhong> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable Integer id){
        PinZhong action = actionService.findById(id);
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

    @ApiOperation(value = "findInNumbers")
    @PostMapping(value = "/findInNumbers")
    public @ResponseBody ResponseUtil findInNumbers(@RequestBody String[] numbers){
        List<PinZhong> action = actionService.findInNumbers(numbers);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findDistinctType")
    @GetMapping(value = "/findDistinctType")
    public @ResponseBody ResponseUtil findDistinctType(){
        return ResponseUtil.builder().success(true).data(actionService.findDistinctType()).build();
    }

    @ApiOperation(value = "findCondition")
    @GetMapping(value = "/findCondition/{year}/{region}/{type}")
    public @ResponseBody ResponseUtil findCondition(@PathVariable String year,@PathVariable String region,@PathVariable String type){
        return ResponseUtil.builder().success(true).data(actionService.findCondition(year,region,type)).build();
    }

}
