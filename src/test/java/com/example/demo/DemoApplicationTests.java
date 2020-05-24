package com.example.demo;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;

@SpringBootTest
class DemoApplicationTests {

    @Test
    public void simpleWrite() {
        try {
            String filePath = "C:\\Users\\75673\\Desktop\\123.xls";
            FileInputStream inputStream = new FileInputStream(filePath);
//        2.通过poi解析流 HSSFWorkbook 处理流得到的对象中 就封装了Excel文件所有的数据
            HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
//        3.从文件中获取表对象  getSheetAt通过下标获取
            HSSFSheet sheet = workbook.getSheetAt(0);
//        4.从表中获取到行数据  从第二行开始 到 最后一行  getLastRowNum() 获取最后一行的下标
            int lastRowNum = sheet.getLastRowNum();

            for (int i = 1; i <= lastRowNum; i++) {
//            通过下标获取行
                HSSFRow row = sheet.getRow(i);
//            从行中获取数据

                /**
                 * getNumericCellValue() 获取数字
                 * getStringCellValue 获取String
                 */
                if (row.getCell(i) != null) {
                    row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
                    for (int j = 0; j < 7; j++) {
                        String value = row.getCell(j).getStringCellValue();
                        System.out.println(value + "-------------");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }


    }
}
