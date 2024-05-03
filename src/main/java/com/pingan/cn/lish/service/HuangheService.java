package com.pingan.cn.lish.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.dao.HuangheDao;
import com.pingan.cn.lish.entity.Huanghe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("huangheService")
public class HuangheService {
    @Autowired
    private HuangheDao huangDao;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhh");
    private SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy/MM/dd hh:mm");

    @Value("${dataserver.HUANAGHE_FOLDER}")
    private String FOLDER;

    public List<Huanghe> findAll(){
        return huangDao.findAll();
    }

    public Huanghe findById(Integer id){
        return huangDao.findById(id).get();
    }

    public ResponseUtil findByConditions(String lng, String lat){
        double lng1 = Double.parseDouble(lng);
        double lat1 = Double.parseDouble(lat);
        List<Huanghe> lists = huangDao.findByF4LikeAndF5Like(lng, lat);
        List<Huanghe> results = new ArrayList<>();
        for (Huanghe huanghe : lists){
            double lng2 = Double.parseDouble(huanghe.getF4());
            double lat2 = Double.parseDouble(huanghe.getF5());
            if(Math.abs(lat2-lat1)<0.0005 && Math.abs(lng2-lng1)<0.0005){
                System.out.println(huanghe.toString());
                results.add(huanghe);
            }
        }
        return ResponseUtil.success(results);
    }

    public ResponseUtil findLatest5file(){
        File file = new File(FOLDER);		//获取其file对象
        File[] fs = file.listFiles();	//遍历path下的文件和目录，放在File数组中
        List LFs = new ArrayList();
        List GPs = new ArrayList();
        String maxDate_LF = "0000000000";
        String maxDate_GP = "0000000000";
        for(File f:fs){					//遍历File[]数组
            if(!f.isDirectory()){//若非目录(即文件)，则打印
                String curr = f.getName().substring(2,f.getName().indexOf(".TXT"));
                if(f.getName().startsWith("LF")){
                    LFs.add(curr);
                }
                if(f.getName().startsWith("GP")){
                    GPs.add(curr);
                }
            }
        }
        JSONObject res = new JSONObject();
        res.put("LFs",Arrays.copyOfRange(bubblingSort((String[]) LFs.toArray(new String[LFs.size()])), 0, 5));
        res.put("GPs",Arrays.copyOfRange(bubblingSort((String[]) GPs.toArray(new String[GPs.size()])), 0, 5));
        return ResponseUtil.success(res);
    }

    public static String[] bubblingSort(String[] arr) {
        int size = arr.length;
        for(int i = 0; i<size-1; i++) {
            for (int j = i+1; j<arr.length; j++) {
                if(!arr[i].equals(null) && !arr[j].equals(null)){
                    if(arr[i].compareTo(arr[j])<0) {
                        String temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                    }
                }
            }
        }
        return arr;
    }

    public ResponseUtil parseLFFile(String fileName) {
        try{
            String encoding = "UTF-8";
            System.out.println(FOLDER+fileName);
            File file = new File(FOLDER+fileName+".txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                JSONArray LFs = new JSONArray();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    JSONObject jsonObject1 = new JSONObject();
                    JSONObject jsonObject2 = new JSONObject();
                    String[] text = lineTxt.split(" ");
//                    {time: '20210317_090254', lf1: 135.988174, lf2: 136.002762, type: 'lf1', val: 135.988174}
                    jsonObject1.put("time",text[0]);
                    jsonObject1.put("type","lf1");
                    jsonObject1.put("val",Double.parseDouble(text[1]));
                    jsonObject2.put("time",text[0]);
                    jsonObject2.put("type","lf2");
                    jsonObject2.put("val",Double.parseDouble(text[2]));

                    LFs.add(jsonObject1);
                    LFs.add(jsonObject2);
                }
                read.close();
                return ResponseUtil.success(LFs);
            } else {
                System.out.println("找不到指定的文件");
                return ResponseUtil.success(false);
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }

    public ResponseUtil parseGPFile(String fileName,int num) {
        try{
            int dx = (num - 1)*3 + 1;
            int dy = (num - 1)*3 + 2;
            String encoding = "UTF-8";
            File file = new File(FOLDER+fileName+".txt");
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                JSONArray GPs = new JSONArray();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    JSONObject jsonObject1 = new JSONObject();
                    JSONObject jsonObject2 = new JSONObject();
                    String[] text = lineTxt.split(" ");

                    jsonObject1.put("time",text[0]);
                    jsonObject1.put("type","dx"+num);
                    jsonObject1.put("val",Double.parseDouble(text[dx]));
                    jsonObject2.put("time",text[0]);
                    jsonObject2.put("type","dy"+num);
                    jsonObject2.put("val",Double.parseDouble(text[dy]));

                    GPs.add(jsonObject1);
                    GPs.add(jsonObject2);
                }
                read.close();
                return ResponseUtil.success(GPs);
            } else {
                System.out.println("找不到指定的文件");
                return ResponseUtil.success(false);
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
            return ResponseUtil.error();
        }
    }

}
