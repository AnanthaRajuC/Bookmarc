package io.github.anantharajuc.bookmarc.service;

import java.util.HashMap;

public interface FileService 
{
	HashMap<String,String> fileConditionCheck();
	
	void exportBookmarcToCSV();
	
	void importBookmarcCSV();
	
	void importBrowserBookmark();
}
