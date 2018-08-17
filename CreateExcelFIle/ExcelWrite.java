package com.common;
import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;


import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class ExcelWrite {

    private static final String EXCEL_FILE_LOCATION = "MyFirstExcel.xls";
    
   

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

    	ExcelWrite excelWrite = new ExcelWrite();
        WritableWorkbook myFirstWbook = null;
        try {
        	Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/efive","root","mastermind");
            PreparedStatement ps = con.prepareStatement("select * from usermaster");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            myFirstWbook = Workbook.createWorkbook(new File(EXCEL_FILE_LOCATION));
            // create an Excel sheet
            WritableSheet excelSheet = myFirstWbook.createSheet("Sheet 1", 0);
            String ColArry[] = {"D1","D2","D3","D4","D5","D6","D7","D8","D9","D10","D11","D12","D13","D14","D15"};   
            WritableCellFormat cFormat = new WritableCellFormat();
            WritableFont font = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
            cFormat.setBackground(Colour.SKY_BLUE);
            cFormat.setFont(font);
            int i=1;
            while (rs.next()) {
            	for(int j=1; j<=columnsNumber; j++)
            	{
               		for(int k=0; k<ColArry.length; k++)
			           {
			            	if(k==0)
			            		{
				            		Label label = new Label(0,0,ColArry[k],cFormat);
				            		excelSheet.addCell(label);	
			            		}
			            	else
			            		{
			            			Label label = new Label(k,0,ColArry[k],cFormat);
				            		excelSheet.addCell(label);  		
			          			}
			            }
             		  Label label = new Label(j-1, i, rs.getString(j));
                      excelSheet.addCell(label);
                	}
            	i++;
           }
            myFirstWbook.write();
            System.out.println("File Is Create.....");
            
            
            


            con.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } finally {

            if (myFirstWbook != null) {
                try {
                    myFirstWbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                }
            }


        }

    }

  

}