package com.demo.clientserver;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFRow;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class WDIDAO {
    private static final String EXCEL_PATH = "/Resource/RD/Java8/COUNTRY_SM.xlsx";
    private HashMap<String,Integer>INDEX_MAP = new HashMap<>();
    private static class WIDAOInner{
        private static WDIDAO Instance  = new WDIDAO();

    }
    private Workbook workbook;
    public static WDIDAO getInstance()
    {
        return WIDAOInner.Instance;
    }
    private WDIDAO()
    {
        try {
            workbook = WorkbookFactory.create(new File(EXCEL_PATH));
            System.out.println("Workbook has "+workbook.getNumberOfSheets());

            Iterator<Sheet>sheetIterator = workbook.sheetIterator();
            System.out.println(" Retreiving Sheets using Iterator");
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                System.out.println("=> "+sheet.getSheetName());
            }
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            System.out.println("\n Iterating over Rows and Columns using Iterator");
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                Row row  = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                int index = 0;
                while ( cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    String cellValue = dataFormatter.formatCellValue( cell);
                    INDEX_MAP.put(cellValue,index++);
                }
                System.out.println();

            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

    }
    public void close() throws IOException {
        if( workbook !=null) {
            workbook.close();
        }
    }
    public String query(String cmd1,String cmd2)
    {
        if( INDEX_MAP.containsKey( cmd1))
        {

            int columnIndex = INDEX_MAP.get( cmd1);
            Sheet sheet = workbook.getSheetAt(0);
            DataFormatter dataFormatter = new DataFormatter();
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext())
            {
                Row row  = rowIterator.next();
                Cell cell = row.getCell( columnIndex);
                String cellValue = dataFormatter.formatCellValue( cell);
                if( cellValue.equals( cmd2))
                {
                    Iterator<Cell> cellIterator =      row.cellIterator();
                    StringBuilder stringBuilder = new StringBuilder();
                    while (cellIterator.hasNext()) {
                        stringBuilder.append(dataFormatter.formatCellValue(cellIterator.next())).append("\t");
                    }
                    return stringBuilder.toString();
                }

            }


        }
        return "";
    }

    public String query(String cmd1,String cmd2,short cmd3){
        return "";
    }
    public String report(String cmd){
        return "";
    }

}
