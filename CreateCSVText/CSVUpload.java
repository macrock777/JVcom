package com.common;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.hibernate.HibernateSessionFactory;

public class CSVUpload  {
	
	public static  boolean ExecuteUpdate(String strquey)
	{
		Session session = HibernateSessionFactory.getSession();
		System.out.println("Query Execute Properly");
		try
		{
			Connection con =session.connection();
			session.beginTransaction();
			Statement statement =con.createStatement();
			statement.executeUpdate(strquey);
			session.getTransaction().commit();
			con.commit();
			con.close();
			return true;
			
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	public static List<DAO> ExecuteQuery(String str)
	{
		Session session = HibernateSessionFactory.getSession();

		Transaction transaction =null;
		try
		{
			
			transaction=session.beginTransaction();
			SQLQuery query = session.createSQLQuery(str);
			List datalist = query.list();
			transaction=session.getTransaction();
			transaction.commit();
			return datalist;
			
		}
		catch (Exception e)
		{
			if(null!=transaction)
			{
				transaction.rollback();
				System.out.println("ERROR OCCURE DURING THE DISPLAYDATA");
				e.printStackTrace();
			}
		}

		return null;
	}
	
public String DBtoCSV() throws IOException
	{
		 String filename = "C://Users//Raj//Desktop//test.csv";
		 FileWriter fw = new FileWriter(filename);
		 String query = "select * from testtable";
		 List<?> datalist = ExecuteQuery(query);
	for(int i = 0;i<datalist.size();i++)
		{
			Object[] obj = (Object[])datalist.get(i);
			fw.append(obj[0].toString());
			fw.append(',');
			fw.append(obj[1].toString());
			fw.append(',');
			fw.append(obj[2].toString());
			fw.append(',');
			fw.append(obj[3].toString());
			fw.append('\n');
		}
		fw.flush();
		fw.close();
		return null;
	}
public static void main(String arg[])
{
	try
		{
		String filename = "C://Users//Raj//Desktop//test.csv";
		FileWriter fw = new FileWriter(filename);
		String query = "select * from photo";
		List<?> datalist = ExecuteQuery(query);
		for(int i = 1;i<datalist.size();i++)
		{
			Object[] obj = (Object[])datalist.get(i-1);
			for(int j=0;j<obj.length; j++)
				{
					System.out.println(obj[j].toString());
					fw.append(obj[j].toString());
					fw.append(',');
				}
			fw.append('\n');
		}
			fw.flush();
			fw.close();
	}
		catch(Exception e)
		{
			e.printStackTrace();
		}
}

public String StoreDBTOCSV()
	{
		String s1="LOAD DATA INFILE 'C://Users//Raj//Desktop//test.csv'  INTO TABLE photo  FIELDS TERMINATED BY ',';";
		boolean b = ExecuteUpdate(s1);
		System.out.println(b);
		return null;
	}
}
	

