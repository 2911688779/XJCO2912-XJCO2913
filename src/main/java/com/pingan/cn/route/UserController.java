package com.pingan.cn.route;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户")
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(value = "/register")
    public ResponseUtil register(@RequestBody User user){
        User user1 = new User();
        return userService.register(user);
    }

    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/delete/{id}")
    public ResponseUtil deleteById(@PathVariable String id){
        return userService.deleteById(id);
    }

    @GetMapping(value = "/login/{username}/{password}")
    public ResponseUtil login(@PathVariable String username,@PathVariable String password){
        return userService.findByUsernameAndPassword(username,password);
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody String[] ids){
        boolean action = userService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "update")
    @PostMapping(value = "/update")
    public ResponseUtil updateAction(@RequestBody User action){
        return ResponseUtil.builder().success(true).data(userService.update(action)).build();
    }
}
