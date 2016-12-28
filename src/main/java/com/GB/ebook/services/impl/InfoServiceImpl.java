package com.GB.ebook.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.GB.ebook.dao.InformationMapper;
import com.GB.ebook.entity.Information;
import com.GB.ebook.services.interfaces.InfoService;

@Service
public class InfoServiceImpl implements InfoService{
	
	@Autowired
	private InformationMapper infoMapper;
	
	public int updateInfo(Information info) {
		System.out.println("Enter AddInfo");
		return infoMapper.updateInfo(info);
	}

	public Information findbyInfoID() {
		
		Information info =  infoMapper.findbyInfoID();
		
		return info;
	}

}
