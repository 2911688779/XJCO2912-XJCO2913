package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "joyfun_moment")
@RestController
@RequestMapping(value = "/api/joy/moment")
public class MomentController {

    @Autowired
    private MomentService momentService;


    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Moment bean){
        return momentService.save(bean);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return momentService.findAll();
    }

    @GetMapping(value = "/findByUserId/{userId}")
    public ResponseUtil findByUserId(@PathVariable String userId){
        return momentService.findByUserId(userId);
    }

    @PostMapping(value = "/findByUserIds")
    public ResponseUtil findByUserIds(@RequestBody List<String> userIds){
        return momentService.findByUserIds(userIds);
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id){
        return momentService.deleteById(id);
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody String[] ids){
        boolean action = momentService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody Moment action){
        return ResponseUtil.builder().success(true).data(momentService.update(action)).build();
    }
}
