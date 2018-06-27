package com.eBay.helpers;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
 
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import com.eBay.wrappers.BaseTest;
import com.eBay.wrappers.TestMethodCapture;

public class ExcelUtil {
    //Main Directory of the project
    public static final String currentDir = System.getProperty("user.dir");
 
    //Location of Test data excel file
    public static String testDataExcelPath = null;
 
    //Excel WorkBook
    private static XSSFWorkbook excelWBook;
 
    //Excel Sheet
    private static XSSFSheet excelWSheet;
 
    //Excel cell
    private static XSSFCell cell;
 
    //Excel row
    private static XSSFRow row;
   
    //Row Number
    public static int rowNumber;
 
    //Column Number
    public static int columnNumber;
 
    //Setter and Getters of row and columns
    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }
 
    public static int getRowNumber() {
        return rowNumber;
    }
 
    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }
 
    public static int getColumnNumber() {
        return columnNumber;
    }
 
    static String testDataExcelFileName = "DataSheet.xlsx";
    
    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
    public static void setExcelFileSheet(String sheetName) {
        //MAC or Windows Selection for excel path
        if (Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "//src//resources//data//";
        } else if (Platform.getCurrent().toString().contains("WIN")) {
            testDataExcelPath = currentDir + "\\src\\resources\\data\\";
        }
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
 
    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public static String getCellData(int RowNum, int ColNum) {
        try {
            cell = excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }
 
    //This method takes row number as a parameter and returns the data of given row number.
    public static XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }
 
    //This method gets excel file, row and column number and set a value to the that cell.
    public static void setCellData(String value, int RowNum, int ColNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
        
    public CellAddress searchStringInXslx(String string) throws IOException{
        Iterator<Row> iterator = excelWSheet.iterator();
        CellAddress cellNumber=null;
        while(iterator.hasNext()){
             Row nextRow = iterator.next();
             Iterator<Cell> cellIterator = nextRow.cellIterator();
             while (cellIterator.hasNext()) {
                 Cell cell = cellIterator.next();
                    String text = cell.getStringCellValue();
                      if (string.equals(text)) {
                    	  cellNumber=cell.getAddress();
                         break;              
                    }
                 }
        }
        return cellNumber;
    }
    
    public static int getTestRow(String testCase){
        Iterator<Row> iterator = excelWSheet.iterator();
        int rowNumber=-1;
        while(iterator.hasNext()){
             Row nextRow = iterator.next();
             Cell testCell = nextRow.getCell(0);
             String text = testCell.getStringCellValue();
              if (testCase.equals(text)) {
                 	  rowNumber = testCell.getRowIndex();
                      break;              
               }
        }
        return rowNumber;
    }
    
    public static int getDataColumn(String columnTitle){
    	Row titleRow = getRowData(0);
        int columnNumber=-1;
        Iterator<Cell> cellIterator = titleRow.cellIterator();
        while (cellIterator.hasNext()) {
        	Cell cell = cellIterator.next();
        	String text = cell.getStringCellValue();
            if (columnTitle.equals(text)) {
            	columnNumber = cell.getColumnIndex();
            	break;              
            }
        }
        return columnNumber;
    }
    
    public static String getTestData(String columnTitle){
    	String testCase = TestMethodCapture.getTestMethod().getMethodName();
    	int testRowIndex = getTestRow(testCase);
    	int dataColumnIndex = getDataColumn(columnTitle);
        return getCellData(testRowIndex,dataColumnIndex);
    }
    
    public static void setTestData(String columnTitle, String data){
    	String testCase = TestMethodCapture.getTestMethod().getMethodName();
    	int testRowIndex = getTestRow(testCase);
    	int dataColumnIndex = getDataColumn(columnTitle);
        setCellData(data, testRowIndex, dataColumnIndex);
    }
    
}