package io.github.anantharajuc.bookmarc.service;

import java.util.HashMap;

public interface AppService 
{
	HashMap<String,String> urlParser(String urlToBeProcessed);
	
	void htmlParser(String htmlFilesName);
	
	void getBookmarks();
	
	void BookmarkLiveChromeParser();
	
	void classifyHost();
}
