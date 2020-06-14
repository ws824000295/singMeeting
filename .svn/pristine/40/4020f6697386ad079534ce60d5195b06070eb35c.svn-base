package com.boot.common.util.excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcal {
	  
	/**
	 * 读取excle表格
	 * @param fileName
	 */
    public static void readXml(String fileName){  
        boolean isE2007 = false;    //判断是否是excel2007格式  
        if(fileName.endsWith("xlsx"))  
            isE2007 = true;  
        try {  
            InputStream input = new FileInputStream(fileName);  //建立输入流  
            Workbook wb  = null;  
            //根据文件格式(2003或者2007)来初始化  
            if(isE2007)  
                wb = new XSSFWorkbook(input);  
            else  
                wb = new HSSFWorkbook(input);  
            Sheet sheet = wb.getSheetAt(0);     //获得第一个表单  
            Iterator<Row> rows = sheet.rowIterator(); //获得第一个表单的迭代器  
            
            while (rows.hasNext()) {
            	
                Row row = rows.next();  //获得行数据  
                System.out.println("Row #" + row.getRowNum());  //获得行号从0开始  
                Iterator<Cell> cells = row.cellIterator();    //获得第一行的迭代器  
                while (cells.hasNext()) {  
                    Cell cell = cells.next();
                    int columnIndex = cell.getColumnIndex();
                    String value = "";
                    
                    System.out.print("Cell #" + cell.getColumnIndex() + " ");  
                    switch (cell.getCellType()) {   //根据cell中的类型来输出数据  
	                    case HSSFCell.CELL_TYPE_NUMERIC:
	                    	DecimalFormat df = new DecimalFormat("#");
	                    	value = df.format(cell.getNumericCellValue());
	                        System.out.print(" " +df.format(cell.getNumericCellValue()));  
	                        break;  
	                    case HSSFCell.CELL_TYPE_STRING:
	                    	value = cell.getStringCellValue();
	                        System.out.print(" " +cell.getStringCellValue());  
	                        break;  
	                    case HSSFCell.CELL_TYPE_BOOLEAN:  
	                        System.out.print(" " +cell.getBooleanCellValue());  
	                        break;  
	                    case HSSFCell.CELL_TYPE_FORMULA:  
	                        System.out.print(" " +cell.getCellFormula());  
	                        break;  
	                    default:  
	                        System.out.print("unsuported sell type");  
	                    break; 
                    }
                    
                }  
                System.out.println();
            }  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
    
    
    
    public static void main(String[] args) {  
	    readXml("E:/excel/address.xlsx");  
	    System.out.println("-------------");  
	    //readXml("d:/test2.xls");  
	}
}
