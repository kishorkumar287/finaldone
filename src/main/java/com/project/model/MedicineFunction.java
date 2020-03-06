package com.project.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.project.model.MedicineBean;
import com.project.service.MedicineDao;

@Service
public class MedicineFunction {
	
	@Autowired
	MedicineDao md;
	
	
	public List<MedicineBean> retrivedetails(String type,String name)
	{
		List<MedicineBean> ls=null;
			
		
		System.out.println(name+" "+type);
		
		
		  if(type.equals("disease")) 
		  { 
			  
			  ls=md.findByDisease(name);
		  }
		 
		
		  if(type.equals("name"))
			{
				ls=md.findByName(name);
			}
		  
		  if(type.equals("brand"))
			{
				ls=md.findByBrand(name);
			}
		
		
		System.out.println(name+" "+type);
		return ls;
	}
	

}
