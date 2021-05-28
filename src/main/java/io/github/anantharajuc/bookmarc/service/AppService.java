package io.github.anantharajuc.bookmarc.service;

public interface AppService 
{
	void htmlParser(String htmlFilesName);
	
	void getBookmarks();
	
	void BookmarkLiveChromeParser();
	
	void classifyHost();
}
