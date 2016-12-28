package com.GB.ebook.dao;

import com.GB.ebook.entity.Information;

public interface InformationMapper {
	
	Information  findbyInfoID();
	int updateInfo(Information info);
}
