package com.pingan.cn.lmsg;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Api(tags = "槟榔")
@RestController
@RequestMapping(value = "/api/sgfile")
public class SgfileController {

    @Value("${dataserver.folder}")
    private String FILELoction;

    @Autowired
    private SgfileService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody Sgfile action){
        Sgfile result = actionService.saveAction(action);
        return ResponseUtil.builder().success(true).data(result).build();
    }

    @ApiOperation(value = "upload")
    @PostMapping(value = "/upload")
    public ResponseUtil upload(@RequestParam("files") MultipartFile[] files, @RequestParam("path") String[] path){

        Boolean result = true;
        String[] uuids = new String[files.length];
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            // 获取原始名字
            String fileName = file.getOriginalFilename();
            // 获取后缀名
            // String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件保存路径
            String filePath = FILELoction;
//            String uuid = UUID.randomUUID().toString()
            String uuid = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."),fileName.length());
            uuids[i] = uuid;
            // 文件重命名，防止重复
//            fileName = filePath + path[0]+ '/' + path[1] +'/' + path[2] + '/' + path[3] +  '/' + path[4] + '/' + uuid ;
            fileName = filePath + path[0] + '/' + uuid ;
            // 文件对象
            System.out.println("============上传文件路径=============");
            System.out.println(fileName);
            File dest = new File(fileName);
            // 判断路径是否存在，如果不存在则创建
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                // 保存到服务器中
                file.transferTo(dest);
            } catch (Exception e) {
                e.printStackTrace();
                result = false;
            }
        }
        return ResponseUtil.builder().success(result).data(uuids).build();
    }


    @ApiOperation(value = "findAll")
    @GetMapping(value = "/findAll")
    public ResponseUtil findAll(){
        List<Sgfile> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        Sgfile action = actionService.findById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteById/{id}")
    @GetMapping(value = "/deleteById/{id}")
    public @ResponseBody ResponseUtil deleteById(@PathVariable String id){
        boolean action = actionService.deleteById(id);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "deleteBatch")
    @PostMapping(value = "/deleteBatch")
    public @ResponseBody ResponseUtil deleteBatch(@RequestBody String[] ids){
        boolean action = actionService.deleteBatch(ids);
        return ResponseUtil.builder().success(true).data(action).build();
    }
}
