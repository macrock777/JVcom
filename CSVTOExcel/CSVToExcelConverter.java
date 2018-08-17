package com.common;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;


public class CSVToExcelConverter {
	
public boolean csvToExcelConverter(String csvFilePath,String outputFileXLSPath,String[] ColArry)
{
	 try
     {
			 ArrayList arList=null;
		     ArrayList al=null;
		     //String outputFileXLSPath = "C://Users//Raj//Desktop//test.csv";
		     String thisLine;
		     int count=0;
		     FileInputStream fis = new FileInputStream(outputFileXLSPath);
		     DataInputStream myInput = new DataInputStream(fis);
		     int i=0;
		     arList = new ArrayList();
		     while ((thisLine = myInput.readLine()) != null)
		     	{
			         al = new ArrayList();
			         String strar[] = thisLine.split(",");
			         for(int j=0;j<strar.length;j++)
			         {
			             al.add(strar[j]);
			         }
			         arList.add(al);
			         System.out.println();
			         i++;
		     }
    
         HSSFWorkbook hwb = new HSSFWorkbook();
         HSSFSheet sheet = hwb.createSheet("new sheet");
         //String ColArry[] = {"Sr No","UserType","UserType","Email","Mobile","D6","D7","D8","D9","D10","D11","D12","D13","D14","D15","D16"};   
         for(int k=0;k<1;k++)
         {
             HSSFRow row = sheet.createRow((short) 0);
             for(int p=0;p<ColArry.length;p++)
	             {
	                 HSSFCell cell = row.createCell((short) p);
	                 String data = ColArry[p].toString();
	                 cell.setCellType(Cell.CELL_TYPE_STRING);
	                 data=data.replaceAll("\"", "");
	                 data=data.replaceAll("=", "");
	                 cell.setCellValue(data);
	                 System.out.println(data);
	             }
             }
         for(int k=1;k<arList.size()+1;k++)
         {
             ArrayList ardata = (ArrayList)arList.get(k-1);
             HSSFRow row = sheet.createRow((short) 0+k);
             for(int p=0;p<ardata.size();p++)
             {
                 HSSFCell cell = row.createCell((short) p);
                 String data = ardata.get(p).toString();
                 if(data.startsWith("=")){
                     cell.setCellType(Cell.CELL_TYPE_STRING);
                     data=data.replaceAll("\"", "");
                     data=data.replaceAll("=", "");
                     cell.setCellValue(data);
                 }else if(data.startsWith("\"")){
                     data=data.replaceAll("\"", "");
                     cell.setCellType(Cell.CELL_TYPE_STRING);
                     cell.setCellValue(data);
                 }else{
                     data=data.replaceAll("\"", "");
                     cell.setCellType(Cell.CELL_TYPE_NUMERIC);
                     cell.setCellValue(data);
                 }
                 //*/
                 // cell.setCellValue(ardata.get(p).toString());
             }
             System.out.println();
         }
        // String outputFileXLSPath ="C://Users//Raj//Desktop//test.xls";
         FileOutputStream fileOut = new FileOutputStream(outputFileXLSPath);
         hwb.write(fileOut);
         fileOut.close();
         System.out.println("Your excel file has been generated");
     } catch ( Exception ex ) {
         ex.printStackTrace();
     } //main method ends
	return true;
 }
   
}