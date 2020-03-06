package com.project.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.service.RecoveryDao;

@Service
public class PasswordRecoveryFunction {
	
	@Autowired
	RecoveryDao dao;
	
	public RecoveryBean update(String id,String question,String answer)
	{
		RecoveryBean rb;
		
		if(question.equals("q1"))
		{
			rb = dao.Validate(id,answer);
		}
		else if(question.equals("q2"))
		{
			 rb = dao.Validate1(id,answer);
		}
		else
		{
			 rb = dao.Validate2(id,answer);
		}
		
		
		return rb;
	}

}
