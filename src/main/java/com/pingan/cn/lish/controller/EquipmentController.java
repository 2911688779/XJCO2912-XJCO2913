package com.pingan.cn.lish.controller;

import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.entity.Equipment;
import com.pingan.cn.lish.service.EquipmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "校园教学资源")
@RestController
@RequestMapping(value = "/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Equipment equipment) {
        return equipmentService.save(equipment);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll() {
        return equipmentService.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable Integer id) {
        return equipmentService.deleteById(id);
    }

    @GetMapping(value = "/update/{id}/{state}/{isApply}")
    public ResponseUtil update(@PathVariable Integer id,@PathVariable Integer state,@PathVariable Integer isApply){
        return equipmentService.update(id,state,isApply);
    }

    @PostMapping(value = "/findList")
    public ResponseUtil findList(@RequestBody List<Integer> nums) {
        return equipmentService.list(nums);
    }
}
