package com.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataToDataBase extends DAO {
	public void UploadExceltoDatabase() throws IOException  // Support All Version Excel FIle....
	{
			// System.out.println(filepathFileName); // filepathFileName is BIN VARIABEL
			// String filepathFileName
			// File filepath 
							/*<form name="myform" id="myform" method="post" enctype="multipart/form-data" >
						 	<input type="file" name="filepath" id="filepath">
						 	<a href="#" onclick="getDATA();">GET</a> file is file type
							</form>*/
			String path = uploadFile("excel", /*filepathFileName*/, /*filepath*/, "Excelfile");
			System.out.println(path);
        	InputStream ExcelFileToRead = new FileInputStream(request.getServletContext().getRealPath(path));
        	XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
    		XSSFSheet sheet=wb.getSheetAt(0);
    		int getNumberofRow=sheet.getPhysicalNumberOfRows();
    		System.out.println("Total ROW:"+getNumberofRow);
    		 List<String> list = new ArrayList<String>();
    		 
    		for(int i =0; i<getNumberofRow;i++)
	    		{
    				XSSFRow row = sheet.getRow(i);
    				int getNumberofColumn=sheet.getRow(i).getPhysicalNumberOfCells();
    				System.out.println("Total Col:"+getNumberofColumn);
    				for(int j=0; j<getNumberofColumn;j++)
    					{
	    		    		XSSFCell column = row.getCell(j);
			    		    		if(column.getCellType()!=XSSFCell.CELL_TYPE_BLANK)
			    		    		{
					    		    		if(column.getCellType() ==XSSFCell.CELL_TYPE_NUMERIC)
					    					{
					    		    			double d =column.getNumericCellValue();
					    		    			long l = (long)d;
					    		    			String s1 =  String.valueOf(l);
				    							list.add(s1);
					    		    			System.out.println(column.getNumericCellValue());
					    					}
					    		    		if(column.getCellType() ==XSSFCell.CELL_TYPE_STRING)
					    					{
					    		    			System.out.println(column.getStringCellValue());
					    		    			list.add(column.getStringCellValue());
					    					}
			    					}
    					}
    				System.out.println(list);
    				String insertQuery = "insert into exceldata value("+Long.parseLong(list.get(0).toString())+",'"+list.get(1)+"','"+list.get(2)+"','','','')";
    				System.out.println(insertQuery);
    				Boolean a = ExecuteUpdate(insertQuery);
    				System.out.println("EX:"+a);
    				System.out.println(list.removeAll(list));	
	    		}
}
	
public String 	 excelDownload() throws IOException
{
	this.UploadExceltoDatabase();
	return null;
}
}