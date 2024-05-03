package com.pingan.cn.chaland;

import com.alibaba.fastjson.JSONObject;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.geolatte.geom.Polygon;
import org.geolatte.geom.codec.Wkt;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service("chalandService")
public class ChaLandService {
    private static GeometryFactory geometryFactory = new GeometryFactory();

    @Value("${dataserver.folder}")
    private String FILELoction;

    @Autowired
    private ChaLandDao actionDao;

    public ChaLand saveAction(ChaLandDto action){
        try {
            ChaLand entity = new ChaLand();
            BeanUtils.copyProperties(action,entity);
            Polygon geo = (Polygon) Wkt.fromWkt(action.getWkt());
            System.out.println(action.toString());
            System.out.println(entity.toString());
            entity.setGeometry(geo);
            actionDao.save(entity);
            return entity;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<ChaLand> findAll(){
        return actionDao.findAll();
    }
    public boolean deleteById(String id){
        try {
            actionDao.deleteById(id);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public ChaLand findById(String id){
        return actionDao.findById(id).get();
    }

    public boolean deleteBatch(String[] ids){
        ArrayList<ChaLand> arrayList = new ArrayList<>();
        for (String id:ids) {
            ChaLand entity = findById(id);
            if (entity != null){
                arrayList.add(entity);
            }
        }
        try {
            actionDao.deleteInBatch(arrayList);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Boolean isExistNumber(String number){
        ChaLand entity = actionDao.findByNumber(number);
        return entity != null;

    }


    public Map findByParams(String number, String unitType, String name, String town, String countrySide, String village, Double area, String blType,
                            int startYear, int endYear, Integer pageNum, Integer pageSize){
        if(number.equals("ALL")){
            number = "";
        }
        if(unitType.equals("ALL")){
            unitType = "";
        }
        if(name.equals("ALL")){
            name = "";
        }
        if(town.equals("ALL")){
            town = "";
        }
        if(countrySide.equals("ALL")){
            countrySide = "";
        }
        if(village.equals("ALL")){
            village = "";
        }
        if(blType.equals("ALL")){
            blType = "";
        }

        Pageable pageable = PageRequest.of(pageNum,pageSize);
        Page<ChaLand> pages = actionDao.findByParams(number,unitType,name,town,countrySide,village, area,blType,startYear,endYear,pageable);
        Map result = new HashMap();
        result.put("data",pages.getContent());
        result.put("total",pages.getTotalElements());
        return result;
    }

//    public Map statiscByParams(String xzqh,  Double minArea, Double maxArea, int minAge, int maxAge, Integer pageNum, Integer pageSize){
//        String xzqhCopy = "";
//        if(xzqh.equals("ALL")){
//            xzqhCopy = "";
//        }else {
//            xzqhCopy = xzqh;
//        }
//        if(maxArea == 0){
//            maxArea = 100000.0;
//        }
//        int startYear = minAge +  Calendar.getInstance().get(Calendar.YEAR);
//        if(maxAge == 0){
//            maxAge = 1000;
//        }
//        int endYear = maxAge + Calendar.getInstance().get(Calendar.YEAR);
//        Pageable pageable = PageRequest.of(pageNum,pageSize);
//        Map result = new HashMap();
//        System.out.println(xzqhCopy +"、"+ minArea +"、"+ maxArea +"、"+ startYear +"、"+ endYear);
//        if(xzqh.equals("ALL")){ // 统计镇
//            Page<List<Map<String,String>>> pages = actionDao.staticTownByParams(xzqhCopy, minArea, maxArea, startYear,endYear, pageable);
//            result.put("data",pages.getContent());
//            result.put("total",pages.getTotalElements());
//        }else { // 统计乡
//            Page<List<Map<String,String>>> pages = actionDao.staticContrySideByParams(xzqhCopy, minArea, maxArea, startYear,endYear, pageable);
//            result.put("data",pages.getContent());
//            result.put("total",pages.getTotalElements());
//        }
//
//        return result;
//    }

    public  List<Map<String,Object>> statiscByParams2(String xzqh,  Double minArea, Double maxArea, int minAge, int maxAge){
        String xzqhCopy = "";
        if(xzqh.equals("ALL")){
            xzqhCopy = "";
        }else {
            xzqhCopy = xzqh;
        }
        if(maxArea == 0){
            maxArea = 100000.0;
        }
        int startYear = minAge +  Calendar.getInstance().get(Calendar.YEAR);
        if(maxAge == 0){
            maxAge = 1000;
        }
        int endYear = maxAge + Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(xzqhCopy +"、"+ minArea +"、"+ maxArea +"、"+ startYear +"、"+ endYear);
        if(xzqh.equals("ALL")){ // 统计镇
            return  actionDao.staticTownByParams2(xzqhCopy, minArea, maxArea, startYear,endYear);
        }else { // 统计乡
            return actionDao.staticContrySideByParams2(xzqhCopy, minArea, maxArea, startYear,endYear);
        }

    }

//    查询统计不同的数据，生成文件
    public File getFileByType(String type){
        File newFile = createNewFile(type);;

        //新文件写入数据，并下载*****************************************************
        InputStream is = null;
        HSSFWorkbook workbook = null;
        HSSFSheet sheet = null;
        try {
            is = new FileInputStream(newFile);
            workbook = new HSSFWorkbook(is);
            //获取第一个sheet
        } catch (Exception e1) {
            e1.printStackTrace();
        }

            try {
                //写数据
                FileOutputStream fos = new FileOutputStream(newFile);
                if (type.equals("全县槟榔汇总")) {
                    sheet = workbook.getSheetAt(0);
                    List<Map<String, Object>> result = actionDao.stasticXian();
                    List<String> townNames = new ArrayList<>();
                    for (int j = 0; j < result.size(); j++) {
                        String town = result.get(j).get("town").toString();
                        if (!townNames.contains(town)) {
                            townNames.add(town);
                        }
                    }

                    Double hzTotalArea = 0.0;
                    Double hzGsArea = 0.0;
                    Double hzNhArea = 0.0;
                    Integer hzTotalNum = 0;
                    Integer hzGsNum = 0;
                    Integer hzNhNum = 0;
                    for (String town : townNames) {
                        List<Map<String, Object>> items = result.stream().filter(res -> res.get("town").toString().equals(town)).collect(Collectors.toList());
                        HSSFRow row = sheet.createRow(townNames.indexOf(town) + 3);
                        HSSFCell cell0 = row.createCell(0);
                        cell0.setCellValue(items.get(0).get("town").toString());

                        Double totalArea = 0.0;
                        Integer totalNum = 0;
                        for (int i = 0; i < items.size(); i++) {
                            if (items.get(i).get("unit_type").equals("公司")) {
                                HSSFCell cell3 = row.createCell(3);
                                HSSFCell cell4 = row.createCell(4);
                                Double area = (Double) items.get(i).get("totalarea");
                                totalArea += area;
                                hzTotalArea += area;
                                hzGsArea += area;
                                Integer num = Integer.valueOf(items.get(i).get("totalnum").toString());
                                totalNum += num;
                                hzTotalNum += num;
                                hzGsNum += num;
                                cell3.setCellValue(area);
                                cell4.setCellValue(num);
                            }
                            if (items.get(i).get("unit_type").equals("农户")) {
                                HSSFCell cell5 = row.createCell(5);
                                HSSFCell cell6 = row.createCell(6);
                                Double area = (Double) items.get(i).get("totalarea");
                                totalArea += area;
                                hzTotalArea += area;
                                hzNhArea += area;
                                Integer num = Integer.valueOf(items.get(i).get("totalnum").toString());
                                totalNum += num;
                                hzNhNum += num;
                                hzNhNum += num;
                                cell5.setCellValue(area);
                                cell6.setCellValue(num);
                            }
                        }
                        HSSFCell cell1 = row.createCell(1);
                        HSSFCell cell2 = row.createCell(2);
                        cell1.setCellValue(totalArea);
                        cell2.setCellValue(totalNum);
                    }

                    HSSFCellStyle style = workbook.createCellStyle();
                    HSSFFont font = workbook.createFont();
                    font.setFontName("黑体");
                    font.setFontHeightInPoints((short) 12);// 字体大小
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                    style.setFont(font);

                    HSSFRow row = sheet.createRow(townNames.size() + 3);
                    HSSFCell cell0 = row.createCell(0);
                    HSSFCell cell1 = row.createCell(1);
                    HSSFCell cell2 = row.createCell(2);
                    HSSFCell cell3 = row.createCell(3);
                    HSSFCell cell4 = row.createCell(4);
                    HSSFCell cell5 = row.createCell(5);
                    HSSFCell cell6 = row.createCell(6);
                    cell0.setCellValue("汇总");
                    cell0.setCellStyle(style);
                    cell1.setCellValue(hzTotalArea);
                    cell1.setCellStyle(style);
                    cell2.setCellValue(hzTotalNum);
                    cell2.setCellStyle(style);
                    cell3.setCellValue(hzGsArea);
                    cell3.setCellStyle(style);
                    cell4.setCellValue(hzGsNum);
                    cell4.setCellStyle(style);
                    cell5.setCellValue(hzNhArea);
                    cell5.setCellStyle(style);
                    cell6.setCellValue(hzNhNum);
                    cell6.setCellStyle(style);
                }else if (type.equals("乡镇槟榔汇总")) {
                    List<Map<String, Object>> result = actionDao.stasticXiang();
                    List<String> townNames = new ArrayList<>();
                    for (int j = 0; j < result.size(); j++) {
                        String town = result.get(j).get("town").toString();
                        if (!townNames.contains(town)) {
                            townNames.add(town);
                        }
                    }
                    for (String town : townNames) {
                        if(townNames.indexOf(town) == 0){
                            sheet = workbook.getSheetAt(0);
                            workbook.setSheetName(0,town);
                        }else {
                            sheet = workbook.createSheet();
                            workbook.setSheetName(townNames.indexOf(town),town);
                        }

                        //表头数据
                        HSSFCellStyle style_head = workbook.createCellStyle();
                        HSSFFont font_head = workbook.createFont();
                        font_head.setFontName("黑体");
                        font_head.setFontHeightInPoints((short) 18);// 字体大小
                        font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head.setFont(font_head);
                        style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        CellRangeAddress regionx1 = new CellRangeAddress(0, 0, 0, 6);
                        CellRangeAddress regionx2 = new CellRangeAddress(1, 1, 0, 6);
                        sheet.addMergedRegion(regionx1);
                        sheet.addMergedRegion(regionx2);

                        // 表头数据
                        List<String> title = new ArrayList<String>();
                        title.add("村委会");
                        title.add("总面积（亩）");
                        title.add("总个数（个）");
                        title.add("公司面积（亩）");
                        title.add("公司个数（个）");
                        title.add("农户面积（亩）");
                        title.add("农户个数（个）");
                        // 第一行 标题
                        HSSFRow row0 = sheet.createRow(0);
                        HSSFCell cell_00 = row0.createCell(0);
                        cell_00.setCellValue("xxx槟榔调查统计表");
                        cell_00.setCellStyle(style_head);
                        // 弟2行 标题
                        HSSFRow row1 = sheet.createRow(1);
                        HSSFCell cell_10 = row1.createCell(0);
                        cell_10.setCellValue("数据范围："+town);
                        HSSFCellStyle style_head2 = workbook.createCellStyle();
                        HSSFFont font_head2 = workbook.createFont();
                        font_head2.setFontName("黑体");
                        font_head2.setFontHeightInPoints((short) 12);// 字体大小
                        font_head2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head2.setFont(font_head2);
                        style_head2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        style_head2.setFont(font_head2);
                        cell_10.setCellStyle(style_head2);

                        // 第3行 表头设置
                        HSSFRow row2 = sheet.createRow(2);
                        for (int i = 0; i < title.size(); i++) {
                            String s = title.get(i);
                            HSSFCell cell = row2.createCell(i);
                            cell.setCellValue(s);
                        }

                        List<Map<String, Object>> items = result.stream().filter(res -> res.get("town").toString().equals(town)).collect(Collectors.toList());
                        List<String> countryNames = new ArrayList<>();
                        for (int i = 0; i < items.size(); i++) {
                            String country = items.get(i).get("country_side").toString();
                            if (!countryNames.contains(country)) {
                                countryNames.add(country);
                            }
                        }

                        Double hzTotalArea = 0.0;
                        Double hzGsArea = 0.0;
                        Double hzNhArea = 0.0;
                        Integer hzTotalNum = 0;
                        Integer hzGsNum = 0;
                        Integer hzNhNum = 0;
                        for (String country : countryNames) {
                            List<Map<String, Object>> countrys = items.stream().filter(res -> res.get("country_side").toString().equals(country)).collect(Collectors.toList());
                            HSSFRow row = sheet.createRow(countryNames.indexOf(country) + 3);
                            HSSFCell cell0 = row.createCell(0);
                            cell0.setCellValue(country);

                            Double totalArea = 0.0;
                            Integer totalNum = 0;
                            for (int i = 0; i < countrys.size(); i++) {
                                if (countrys.get(i).get("unit_type").equals("公司")) {
                                    HSSFCell cell3 = row.createCell(3);
                                    HSSFCell cell4 = row.createCell(4);
                                    Double area = (Double) countrys.get(i).get("totalarea");
                                    totalArea += area;
                                    hzTotalArea += area;
                                    hzGsArea += area;
                                    Integer num = Integer.valueOf(countrys.get(i).get("totalnum").toString());
                                    totalNum += num;
                                    hzTotalNum += num;
                                    hzGsNum += num;
                                    cell3.setCellValue(area);
                                    cell4.setCellValue(num);
                                }
                                if (countrys.get(i).get("unit_type").equals("农户")) {
                                    HSSFCell cell5 = row.createCell(5);
                                    HSSFCell cell6 = row.createCell(6);
                                    Double area = (Double) countrys.get(i).get("totalarea");
                                    totalArea += area;
                                    hzTotalArea += area;
                                    hzNhArea += area;
                                    Integer num = Integer.valueOf(countrys.get(i).get("totalnum").toString());
                                    totalNum += num;
                                    hzNhNum += num;
                                    hzNhNum += num;
                                    cell5.setCellValue(area);
                                    cell6.setCellValue(num);
                                }
                            }
                            HSSFCell cell1 = row.createCell(1);
                            HSSFCell cell2 = row.createCell(2);
                            cell1.setCellValue(totalArea);
                            cell2.setCellValue(totalNum);
                        }

                        HSSFCellStyle style = workbook.createCellStyle();
                        HSSFFont font = workbook.createFont();
                        font.setFontName("黑体");
                        font.setFontHeightInPoints((short) 12);// 字体大小
                        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style.setFont(font);

                        HSSFRow row = sheet.createRow(countryNames.size() + 3);
                        HSSFCell cell0 = row.createCell(0);
                        HSSFCell cell1 = row.createCell(1);
                        HSSFCell cell2 = row.createCell(2);
                        HSSFCell cell3 = row.createCell(3);
                        HSSFCell cell4 = row.createCell(4);
                        HSSFCell cell5 = row.createCell(5);
                        HSSFCell cell6 = row.createCell(6);
                        cell0.setCellValue("汇总");
                        cell0.setCellStyle(style);
                        cell1.setCellValue(hzTotalArea);
                        cell1.setCellStyle(style);
                        cell2.setCellValue(hzTotalNum);
                        cell2.setCellStyle(style);
                        cell3.setCellValue(hzGsArea);
                        cell3.setCellStyle(style);
                        cell4.setCellValue(hzGsNum);
                        cell4.setCellStyle(style);
                        cell5.setCellValue(hzNhArea);
                        cell5.setCellStyle(style);
                        cell6.setCellValue(hzNhNum);
                        cell6.setCellStyle(style);
                    }

                }else if (type.equals("公司槟榔汇总")){
                    sheet = workbook.getSheetAt(0);
                    //表头数据
                    HSSFCellStyle style_head = workbook.createCellStyle();
                    HSSFFont font_head = workbook.createFont();
                    font_head.setFontName("黑体");
                    font_head.setFontHeightInPoints((short) 18);// 字体大小
                    font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                    style_head.setFont(font_head);
                    style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    CellRangeAddress regionx1 = new CellRangeAddress(0, 0, 0, 2);
                    CellRangeAddress regionx2 = new CellRangeAddress(1, 1, 0, 2);
                    sheet.addMergedRegion(regionx1);
                    sheet.addMergedRegion(regionx2);

                    // 表头数据
                    List<String> title = new ArrayList<String>();
                    title.add("公司名称");
                    title.add("面积（亩）");
                    title.add("块数（个）");
                    // 第一行 标题
                    HSSFRow row0 = sheet.createRow(0);
                    HSSFCell cell_00 = row0.createCell(0);
                    cell_00.setCellValue("xxx槟榔调查统计表");
                    cell_00.setCellStyle(style_head);
                    // 弟2行 标题
                    HSSFRow row1 = sheet.createRow(1);
                    HSSFCell cell_10 = row1.createCell(0);
                    cell_10.setCellValue("数据范围：xxx");
                    HSSFCellStyle style_head2 = workbook.createCellStyle();
                    HSSFFont font_head2 = workbook.createFont();
                    font_head2.setFontName("黑体");
                    font_head2.setFontHeightInPoints((short) 12);// 字体大小
                    font_head2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                    style_head2.setFont(font_head2);
                    style_head2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                    style_head2.setFont(font_head2);
                    cell_10.setCellStyle(style_head2);

                    // 第3行 表头设置
                    HSSFRow row2 = sheet.createRow(2);
                    for (int i = 0; i < title.size(); i++) {
                        String s = title.get(i);
                        HSSFCell cell = row2.createCell(i);
                        cell.setCellValue(s);
                    }

                    List<Map<String, Object>> result = actionDao.stasticByGs();
                    Double hzGsArea = 0.0;
                    Integer hzGsNum = 0;

                    for (int j = 0; j < result.size(); j++) {
                        String name = result.get(j).get("name").toString();
                        Double area = (Double) result.get(j).get("totalarea");
                        Integer num = Integer.valueOf(result.get(j).get("totalnum").toString());
                        hzGsArea += area;
                        hzGsNum += num;
                        HSSFRow row = sheet.createRow(j + 3);
                        HSSFCell cell0 = row.createCell(0);
                        HSSFCell cell1 = row.createCell(1);
                        HSSFCell cell2 = row.createCell(2);
                        cell0.setCellValue(name);
                        cell1.setCellValue(area);
                        cell2.setCellValue(num);
                    }

                    HSSFCellStyle style = workbook.createCellStyle();
                    HSSFFont font = workbook.createFont();
                    font.setFontName("黑体");
                    font.setFontHeightInPoints((short) 12);// 字体大小
                    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                    style.setFont(font);

                    HSSFRow row = sheet.createRow(result.size() + 3);
                    HSSFCell cell0 = row.createCell(0);
                    HSSFCell cell1 = row.createCell(1);
                    HSSFCell cell2 = row.createCell(2);
                    cell0.setCellValue("汇总");
                    cell0.setCellStyle(style);
                    cell1.setCellValue(hzGsArea);
                    cell1.setCellStyle(style);
                    cell2.setCellValue(hzGsNum);
                    cell2.setCellStyle(style);
                }
                else if (type.equals("全县槟榔地块（按乡镇统计）")){
                    List<ChaLand> result = actionDao.findAll();
                    List<String> countrySides = actionDao.findDistinctCountrySide();
                    for (String countrySide : countrySides) {
                        if(countrySides.indexOf(countrySide) == 0){
                            sheet = workbook.getSheetAt(0);
                            workbook.setSheetName(0,countrySide);
                        }else {
                            sheet = workbook.createSheet();
                            workbook.setSheetName(countrySides.indexOf(countrySide),countrySide);
                        }

                        //表头数据
                        HSSFCellStyle style_head = workbook.createCellStyle();
                        HSSFFont font_head = workbook.createFont();
                        font_head.setFontName("黑体");
                        font_head.setFontHeightInPoints((short) 18);// 字体大小
                        font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head.setFont(font_head);
                        style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        CellRangeAddress regionx1 = new CellRangeAddress(0, 0, 0, 10);
                        CellRangeAddress regionx2 = new CellRangeAddress(1, 1, 0, 10);
                        sheet.addMergedRegion(regionx1);
                        sheet.addMergedRegion(regionx2);

                        // 表头数据
                        List<String> title = new ArrayList<String>();
                        title.add("序号");
                        title.add("编号");
                        title.add("类型");
                        title.add("名称");
                        title.add("镇");
                        title.add("乡");
                        title.add("村");
                        title.add("地块编号");
                        title.add("面积");
                        title.add("槟榔类型");
                        title.add("种植年份");
                        // 第一行 标题
                        HSSFRow row0 = sheet.createRow(0);
                        HSSFCell cell_00 = row0.createCell(0);
                        cell_00.setCellValue("xxx槟榔调查明细表");
                        cell_00.setCellStyle(style_head);
                        // 弟2行 标题
                        HSSFRow row1 = sheet.createRow(1);
                        HSSFCell cell_10 = row1.createCell(0);
                        cell_10.setCellValue("数据范围："+countrySide);
                        HSSFCellStyle style_head2 = workbook.createCellStyle();
                        HSSFFont font_head2 = workbook.createFont();
                        font_head2.setFontName("黑体");
                        font_head2.setFontHeightInPoints((short) 12);// 字体大小
                        font_head2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head2.setFont(font_head2);
                        style_head2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        style_head2.setFont(font_head2);
                        cell_10.setCellStyle(style_head2);

                        // 第3行 表头设置
                        HSSFRow row2 = sheet.createRow(2);
                        for (int i = 0; i < title.size(); i++) {
                            String s = title.get(i);
                            HSSFCell cell = row2.createCell(i);
                            cell.setCellValue(s);
                        }

                        List<ChaLand> items = result.stream().filter(res -> res.getCountrySide().equals(countrySide)).collect(Collectors.toList());
                        for (ChaLand cha : items) {
                            HSSFRow row = sheet.createRow(items.indexOf(cha) + 3);
                            HSSFCell cell0 = row.createCell(0);
                            HSSFCell cell1 = row.createCell(1);
                            HSSFCell cell2 = row.createCell(2);
                            HSSFCell cell3 = row.createCell(3);
                            HSSFCell cell4 = row.createCell(4);
                            HSSFCell cell5 = row.createCell(5);
                            HSSFCell cell6 = row.createCell(6);
                            HSSFCell cell7 = row.createCell(7);
                            HSSFCell cell8 = row.createCell(8);
                            HSSFCell cell9 = row.createCell(9);
                            HSSFCell cell10 = row.createCell(10);
                            cell0.setCellValue(items.indexOf(cha));
                            cell1.setCellValue(cha.getNumber());
                            cell2.setCellValue(cha.getUnitType());
                            cell3.setCellValue(cha.getName());
                            cell4.setCellValue(cha.getTown());
                            cell5.setCellValue(cha.getCountrySide());
                            cell6.setCellValue(cha.getVillage());
                            cell7.setCellValue(cha.getLandNo());
                            cell8.setCellValue(cha.getArea());
                            cell9.setCellValue(cha.getBlType());
                            cell10.setCellValue(cha.getYear());
                        }
                    }
                }
                else if (type.equals("全县槟榔地块（按公司及农户分类）")){
                    List<ChaLand> result = actionDao.findAll();
                    List<String> names = actionDao.findDistinctName();
                    for (String name : names) {
                        if(names.indexOf(name) == 0){
                            sheet = workbook.getSheetAt(0);
                            workbook.setSheetName(0,name);
                        }else {
                            sheet = workbook.createSheet();
                            workbook.setSheetName(names.indexOf(name),name);
                        }

                        //表头数据
                        HSSFCellStyle style_head = workbook.createCellStyle();
                        HSSFFont font_head = workbook.createFont();
                        font_head.setFontName("黑体");
                        font_head.setFontHeightInPoints((short) 18);// 字体大小
                        font_head.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head.setFont(font_head);
                        style_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        CellRangeAddress regionx1 = new CellRangeAddress(0, 0, 0, 10);
                        CellRangeAddress regionx2 = new CellRangeAddress(1, 1, 0, 10);
                        sheet.addMergedRegion(regionx1);
                        sheet.addMergedRegion(regionx2);

                        // 表头数据
                        List<String> title = new ArrayList<String>();
                        title.add("序号");
                        title.add("编号");
                        title.add("类型");
                        title.add("名称");
                        title.add("镇");
                        title.add("乡");
                        title.add("村");
                        title.add("地块编号");
                        title.add("面积");
                        title.add("槟榔类型");
                        title.add("种植年份");
                        // 第一行 标题
                        HSSFRow row0 = sheet.createRow(0);
                        HSSFCell cell_00 = row0.createCell(0);
                        cell_00.setCellValue("xxx槟榔调查明细表");
                        cell_00.setCellStyle(style_head);
                        // 弟2行 标题
                        HSSFRow row1 = sheet.createRow(1);
                        HSSFCell cell_10 = row1.createCell(0);
                        cell_10.setCellValue("数据范围："+ name);
                        HSSFCellStyle style_head2 = workbook.createCellStyle();
                        HSSFFont font_head2 = workbook.createFont();
                        font_head2.setFontName("黑体");
                        font_head2.setFontHeightInPoints((short) 12);// 字体大小
                        font_head2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
                        style_head2.setFont(font_head2);
                        style_head2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
                        style_head2.setFont(font_head2);
                        cell_10.setCellStyle(style_head2);

                        // 第3行 表头设置
                        HSSFRow row2 = sheet.createRow(2);
                        for (int i = 0; i < title.size(); i++) {
                            String s = title.get(i);
                            HSSFCell cell = row2.createCell(i);
                            cell.setCellValue(s);
                        }

                        List<ChaLand> items = result.stream().filter(res -> res.getName().equals(name)).collect(Collectors.toList());
                        for (ChaLand cha : items) {
                            HSSFRow row = sheet.createRow(items.indexOf(cha) + 3);
                            HSSFCell cell0 = row.createCell(0);
                            HSSFCell cell1 = row.createCell(1);
                            HSSFCell cell2 = row.createCell(2);
                            HSSFCell cell3 = row.createCell(3);
                            HSSFCell cell4 = row.createCell(4);
                            HSSFCell cell5 = row.createCell(5);
                            HSSFCell cell6 = row.createCell(6);
                            HSSFCell cell7 = row.createCell(7);
                            HSSFCell cell8 = row.createCell(8);
                            HSSFCell cell9 = row.createCell(9);
                            HSSFCell cell10 = row.createCell(10);
                            cell0.setCellValue(items.indexOf(cha));
                            cell1.setCellValue(cha.getNumber());
                            cell2.setCellValue(cha.getUnitType());
                            cell3.setCellValue(cha.getName());
                            cell4.setCellValue(cha.getTown());
                            cell5.setCellValue(cha.getCountrySide());
                            cell6.setCellValue(cha.getVillage());
                            cell7.setCellValue(cha.getLandNo());
                            cell8.setCellValue(cha.getArea());
                            cell9.setCellValue(cha.getBlType());
                            cell10.setCellValue(cha.getYear());
                        }
                    }
                }

                workbook.write(fos);
                fos.flush();
                fos.close();
            }
            catch(Exception e) {
                e.printStackTrace();
            }finally {
                try {
                    if (null != is) {
                        is.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        return newFile;
    }

    /**
     * 复制文件
     *
     * @param s
     * 源文件
     * @param t
     * 复制到的新文件
     */

    public void fileChannelCopy(File s, File t) {
        try {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new BufferedInputStream(new FileInputStream(s),1024);
                out = new BufferedOutputStream(new FileOutputStream(t),1024);
                byte[] buffer = new byte[1024];
                int len;
                while ((len=in.read(buffer))!=-1) {
                    out.write(buffer,0,len);
                }
            } finally {
                if (null != in) {
                    in.close();
                }
                if (null != out) {
                    out.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取excel模板，并复制到新文件中供写入和下载
     * @return
     */
    public File createNewFile(String fileName){
        //读取模板，并赋值到新文件************************************************************
        //文件模板路径
        String path = FILELoction + "/模板";
        File file=new File(path+"/"+fileName + ".xls");

        //保存文件的路径
        String realPath = FILELoction + "/模板/temp";
        //新的文件名
        String newFileName = fileName + System.currentTimeMillis() + ".xls";
        //判断路径是否存在
        File dir = new File(realPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //写入到新的excel
        File newFile = new File(realPath, newFileName);
        try {
            newFile.createNewFile();
            //复制模板到新文件
            fileChannelCopy(file, newFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newFile;
    }

    /**
     * 下载成功后删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
