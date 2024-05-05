package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "joyfun_activity")
@RestController
@RequestMapping(value = "/api/joy/activity")
public class ActivityController {

    @Autowired
    private ActivityService activityService;


    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Activity bean){
        return activityService.save(bean);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return activityService.findAll();
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseUtil findById(@PathVariable String id){
        return activityService.findActivityById(id);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id){
        return activityService.deleteById(id);
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody String[] ids){
        boolean action = activityService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Activity action){
        return ResponseUtil.builder().success(true).data(activityService.update(action)).build();
    }
}
