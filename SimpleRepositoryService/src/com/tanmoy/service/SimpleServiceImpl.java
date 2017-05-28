package com.tanmoy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tanmoy.dao.SimpleDAO;
import com.tanmoy.employee.service.SimpleService;


@Service("simpleService")
public class SimpleServiceImpl implements SimpleService {

	@Autowired(required=true)
	SimpleDAO dao;

	@Override
	public void createJob() {
		
		System.out.print("AAAAAAAAAAAA BBBBBBBBBBBB");
		dao.createJob();
		
	}

}
