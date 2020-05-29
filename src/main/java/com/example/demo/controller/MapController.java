package com.example.demo.controller;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.CityDo;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.Objects;

@Controller
@RequestMapping("/map")
public class MapController {

    private static String UPLOADED_FOLDER = "D://map//";

    @RequestMapping("/getindex")
    public String index() {
        return "index";
    }

    @ResponseBody
    @RequestMapping("/getCityData")
    public String getCityData() {
        String fileName = "D:\\map\\汉中烟草适应性数据.xls";
        return Objects.requireNonNull(readFile(fileName)).toJSONString();
    }

    @PostMapping("/upload")
    Object upload(@RequestParam("file") MultipartFile file) {
        byte[] bytes;
        try {
            String fileName = UPLOADED_FOLDER + file.getOriginalFilename();
            File deleteFile = new File(fileName);
            if (deleteFile.exists()) {
                deleteFile.delete();
            }
            bytes = file.getBytes();
            Path path = Paths.get(fileName);
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }


    private static JSONArray readFile(String filePath){
        try {
            JSONArray jsonArray = new JSONArray();
            FileInputStream inputStream = new FileInputStream(filePath);
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            System.out.println("lastRowNum-----------" + lastRowNum);
            for (int i = 1; i <= lastRowNum; i++) {
                HSSFRow row = sheet.getRow(i);
                if (null !=row.getCell(0)) {
                    row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                    double acount = 0;
                    String name = "";
                    for (int j = 0; j < 7; j++) {
                        if (j == 0) {
                            name = row.getCell(0).getStringCellValue();
                        }else {
                            acount += Double.valueOf(row.getCell(j).getStringCellValue());
                        }
                    }
                    DecimalFormat df = new DecimalFormat(".##");
                    jsonArray.add(new CityDo(name, df.format(acount / 7)));
                }
            }
            return jsonArray;
        } catch (Exception e) {
            System.out.println(e.getMessage()+"---------------------------------");
        }
        return null;

    }

}
