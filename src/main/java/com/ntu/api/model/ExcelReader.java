package com.ntu.api.model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ExcelReader {

    public static void excelWrite(String fileName, String sheetName, Object[][] data){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("sheetName");
        int rowNum = 0;

        for (Object[] datatype: data){
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
            for (Object field : datatype){
                Cell cell = row.createCell(colNum++);
                if(field instanceof  String){
                    cell.setCellValue((String) field);
                }
                else if(field instanceof  Integer){
                    cell.setCellValue((Integer) field);
                }
            }
        }

        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            workbook.write(outputStream);
            workbook.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> excelRead(String fileName){
        ArrayList<ArrayList<String>> data = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(new File(fileName));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            int count = 0;

            while (iterator.hasNext()){
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                data.add(new ArrayList<>());
                while (cellIterator.hasNext()){
                    Cell currentCell = cellIterator.next();
                    if(currentCell.getCellTypeEnum() == CellType.STRING && currentCell.getStringCellValue() != "/"){
                        data.get(count).add(currentCell.getStringCellValue());
                    }
                    else if(currentCell.getCellTypeEnum() == CellType.NUMERIC){
                        data.get(count).add(String.valueOf(currentCell.getNumericCellValue()));
                    }
                }
                count++;
            }
            System.out.println(data);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
