package com.efive.master.common;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.efive.master.hibernate.HibernateSessionFactory;

public class DAO implements ServletResponseAware,ServletRequestAware {
	
	public static HttpServletRequest request;
	public static HttpServletResponse response;
	Session session  = HibernateSessionFactory.getSession();
	Transaction transaction =null;
	public List<DAO> ExecuteQuery(String str)
	{
		try
		{
			transaction=session.beginTransaction();
			SQLQuery query = session.createSQLQuery(str);
			List<DAO> datalist = query.list();
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
				System.out.println(e.getMessage());
				e.printStackTrace();
				
			}
		}

		return null;
	}
	
	
	public String updateHibernate(Object object)
	
	{
		try 
		{
			
			transaction = session.beginTransaction();
			session.update(object);
			transaction=session.getTransaction();
			transaction.commit();
			
		}
		catch (Exception e) 
		{
			if(null!=transaction)
			{
				
				transaction.rollback();
				e.printStackTrace();
				System.out.println(e.getMessage());
				
				
			}
			
		}
		
		
		
		return null;
		
	}
public Boolean saveHibernate(Object object)
	
	{
		try 
		{
			
			transaction = session.beginTransaction();
			session.save(object);
			transaction=session.getTransaction();
			transaction.commit();
			return true;
		}
		catch (Exception e) 
		{
			if(null!=transaction)
			{
				
				transaction.rollback();
				
			}
			e.printStackTrace();
			System.out.println(e.getMessage());
			
			return false;
		}
		
		
		
	}


@Override
public void setServletRequest(HttpServletRequest request) {
	DAO.request=request;
	
}


@Override
public void setServletResponse(HttpServletResponse  response) {
	DAO.response  =response;
	
}
	
public boolean ExecuteUpdate(String strquey)
{
	System.out.println("Query Execute Properly");
	try
	{
		/*Session session = HibernateSessionFactory.getSession();*/
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

}
