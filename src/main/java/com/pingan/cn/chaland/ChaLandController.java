package com.pingan.cn.chaland;

import com.pingan.cn.common.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Api(tags = "槟榔")
@RestController
@RequestMapping(value = "/api/chaland")
public class ChaLandController {
//    private static final Logger logger = LoggerFactory.getLogger(com.pingan.cn.chaland.ChaLandController.class);
    @Value("${dataserver.folder}")
    private String FILELoction;

    @Autowired
    private ChaLandService actionService;

    @ApiOperation(value = "save")
    @PostMapping(value = "/save")
    public ResponseUtil save(@RequestBody ChaLandDto action){
        ChaLand result = actionService.saveAction(action);
        if(result.getGeometry().equals(null)){
            return ResponseUtil.builder().success(false).data(result).build();
        }
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
            fileName = filePath + path[0]+ '/' + path[1] +'/' + path[2] + '/' + path[3] +  '/' + path[4] + '/' + uuid ;
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
        List<ChaLand> actionsList = actionService.findAll();
        return ResponseUtil.builder().success(true).data(actionsList).build();
    }

    @ApiOperation(value = "findById/{id}")
    @GetMapping(value = "/findById/{id}")
    public @ResponseBody ResponseUtil findById(@PathVariable String id){
        ChaLand action = actionService.findById(id);
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

    @ApiOperation(value = "isExistNumber/{number}")
    @GetMapping(value = "/isExistNumber/{number}")
    public @ResponseBody ResponseUtil isExistNumber(@PathVariable String number){
        boolean action = actionService.isExistNumber(number);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "findByParams/{number}/{unitType}/{name}/{town}/{countrySide}/{village}/{area}/{blType}/{startYear}/{endYear}/{pageNum}/{pageSize}")
    @GetMapping(value = "/findByParams/{number}/{unitType}/{name}/{town}/{countrySide}/{village}/{area}/{blType}/{startYear}/{endYear}/{pageNum}/{pageSize}")
    public @ResponseBody ResponseUtil findByParams(@PathVariable String number,@PathVariable String unitType,@PathVariable String name,@PathVariable String town,
                                                   @PathVariable String countrySide,@PathVariable String village,@PathVariable Double area, @PathVariable String blType,
                                                   @PathVariable int startYear,@PathVariable int endYear,@PathVariable Integer pageNum,@PathVariable Integer pageSize){
        Map action = actionService.findByParams(number,unitType,name,town,countrySide,village, area,blType,startYear,endYear,pageNum,pageSize);
        return ResponseUtil.builder().success(true).data(action).build();
    }

//    @ApiOperation(value ="/statiscByParams/{xzqh}/{minArea}/{maxArea}/{minAge}/{maxAge}/{pageNum}/{pageSize}")
//    @GetMapping(value = "/statiscByParams/{xzqh}/{minArea}/{maxArea}/{minAge}/{maxAge}/{pageNum}/{pageSize}")
//    public @ResponseBody ResponseUtil statiscByParams(@PathVariable String xzqh,  @PathVariable Double minArea, @PathVariable Double maxArea,@PathVariable int minAge, @PathVariable int maxAge,
//                                                      @PathVariable Integer pageNum, @PathVariable Integer pageSize){
//        Map action = actionService.statiscByParams(xzqh, minArea, maxArea, minAge, maxAge, pageNum,pageSize);
//        return ResponseUtil.builder().success(true).data(action).build();
//    }

//    统计
    @ApiOperation(value ="/statiscByParams/{xzqh}/{minArea}/{maxArea}/{minAge}/{maxAge}")
    @GetMapping(value = "/statiscByParams/{xzqh}/{minArea}/{maxArea}/{minAge}/{maxAge}")
    public @ResponseBody ResponseUtil statiscByParams2(@PathVariable String xzqh,  @PathVariable Double minArea, @PathVariable Double maxArea,@PathVariable int minAge, @PathVariable int maxAge){
        List<Map<String,Object>> action = actionService.statiscByParams2(xzqh, minArea, maxArea, minAge, maxAge);
        return ResponseUtil.builder().success(true).data(action).build();
    }

    @ApiOperation(value = "download/{type}")
    @GetMapping(value = "/download/{type}")
    public @ResponseBody void download(@PathVariable String type, HttpServletResponse response){
        try {
// path是指想要下载的文件的路径
            File file = actionService.getFileByType(type);
// 获取文件名
            String filename = file.getName();
// 获取文件后缀名
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();

// 将文件写入输入流
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStream fis = new BufferedInputStream(fileInputStream);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();

// 清空response
            response.reset();
// 设置response的Header
            response.setCharacterEncoding("UTF-8");
//Content-Disposition的作用：告知浏览器以何种方式显示响应返回的文件，用浏览器打开还是以附件的形式下载到本地保存
//attachment表示以附件方式下载 inline表示在线打开 "Content-Disposition: inline; filename=文件名.mp3"
// filename表示文件的默认名称，因为网络传输只支持URL编码的相关支付，因此需要将文件名URL编码后进行传输,前端收到后需要反编码才能获取到真正的名称
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
// 告知浏览器文件的大小
            response.addHeader("Content-Length", "" + file.length());
            OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
