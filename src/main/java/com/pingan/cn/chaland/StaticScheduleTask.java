package com.pingan.cn.chaland;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.time.LocalDateTime;

@Component
@Configuration //主要用于标记配置类,兼备component的效果
@EnableScheduling  //开启定时任务
@Lazy(value = false)
public class StaticScheduleTask {
    private static final Logger logger = LoggerFactory.getLogger(StaticScheduleTask.class);

    @Value("${dataserver.folder}")
    private String FILELoction;

//    12 小时清除一次
    @Scheduled(fixedRate = 1000 * 60 * 60 * 12)
    private void configureTasks() {
        logger.info("执行定时清除缓存文件: " + LocalDateTime.now());
        deleteAllFile(FILELoction+"/模板/temp");
    }
    /**
     * 删除指定文件夹下所有子目录
     *
     * @param filepath 文件夹路径
     * @return 删除成功返回true,失败返回false
     */
    public static boolean deleteAllFile(String filepath) {
        boolean flag = false;
        File file = new File(filepath);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (filepath.endsWith(File.separator)) {
                temp = new File(filepath + tempList[i]);
            } else {
                temp = new File(filepath + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                deleteAllFile(temp.getPath());  // 先删除文件夹里面的文件
                temp.delete();  // 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }
}
