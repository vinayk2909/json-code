package org.arpit.java2blog.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.arpit.java2blog.bean.Country;

/*
 * It is just a helper class which should be replaced by database implementation.
 * It is not very well written class, it is just used for demonstration.
 */
public class CountryService {

	static HashMap<Integer,Country> countryIdMap=getCountryIdMap();


	public CountryService() {
		super();
		System.out.println("hi1");
		if(countryIdMap==null)
		{
			countryIdMap=new HashMap<Integer,Country>();
		    //Creating some object of countries while initializing
			//Country indiaCountry=new Country(1, "India",10000);
			//countryIdMap.put(1,indiaCountry);
			System.out.println("hi2");
			try
			  {
				System.out.println("hi3");  
			  Class.forName("org.sqlite.JDBC");
		      Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\sqlite3\\Empl.db");  
		      Statement stmt  = conn.createStatement();
		      ResultSet rs    = stmt.executeQuery("select * from Empl");
		      //ArrayList<String> list=new ArrayList<String>();
		      Country[] c=new Country[10];
		      int i=1;
		      while(rs.next())
		      {    	  
		    	  System.out.println("hi3.5");
		    	  c[i]=new Country(rs.getInt("id"), rs.getString("name"),rs.getLong("age"));
		    	  //list.add(rs.getString("id")+rs.getString("name")+rs.getString("population"));
		    	  
		    	  countryIdMap.put(i,c[i]);  
		    	  i++;
		      }     
		      //for(int i=0;i<3;i++)
	    	  //{
	    	  //System.out.println(list.get(i));
	    	  //}
		      System.out.println(countryIdMap);
		      conn.close();
			  }
		
		      catch (SQLException e) {  
		          System.out.println(e.getMessage()); 
		          System.out.println("hi5");
		      	} 
		      catch (ClassNotFoundException e) 
		      {		
		    	e.printStackTrace();
		    	
		        } 
		     
			System.out.println("hi7");
			
		}
	
	}
	public List<Country> getAllCountries()
	{
		List<Country> countries = new ArrayList<Country>(countryIdMap.values());
		return countries;
	}

	public Country getCountry(int id)
	{
		Country country= countryIdMap.get(id);
		return country;
	}
	public Country addCountry(Country country)
	{
		country.setId(countryIdMap.size()+1);
		countryIdMap.put(country.getId(), country);
		return country;
	}
	
	public Country updateCountry(Country country)
	{
		if(country.getId()<=0)
			return null;
		countryIdMap.put(country.getId(), country);
		return country;

	}
	public void deleteCountry(int id)
	{
		countryIdMap.remove(id);
	}

	public static HashMap<Integer, Country> getCountryIdMap() {
		return countryIdMap;
	}


}
