package com.project.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.project.model.AdminBean;


@Repository
public interface AdminDao extends JpaRepository<AdminBean, String>{
	
	@Query("select s from AdminBean s where s.emailId=:email AND s.password=:password")
	public AdminBean validateAdmin(String email,String password);

	@Query("select s from AdminBean s where s.emailId=:email")
	public AdminBean findAdmin(String email);
	
	
	public List<AdminBean> findAll();
	
	 @Transactional
	 @Modifying(clearAutomatically = true)
	 @Query("UPDATE AdminBean a SET a.password = :password WHERE a.emailId = :emailId")
	 int updatepassword(@Param("emailId") String emailId, @Param("password") String password);
	 
	 
	 
	 

}
