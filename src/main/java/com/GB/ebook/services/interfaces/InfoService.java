package com.GB.ebook.services.interfaces;

import com.GB.ebook.entity.Information;

public interface InfoService {
	
	int updateInfo(Information info);
	Information findbyInfoID();
}
