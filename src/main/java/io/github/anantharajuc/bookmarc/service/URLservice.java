package io.github.anantharajuc.bookmarc.service;

import java.util.HashMap;

public interface URLservice 
{
	HashMap<String,String> urlParser(String urlToBeProcessed);
}
