package io.github.anantharajuc.bookmarc.service.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import io.github.anantharajuc.bookmarc.service.URLservice;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class URLserviceImpl implements URLservice
{
	int urlcounter = 0;

	@Override
	public HashMap<String,String> urlParser(String urlToBeProcessed) 
	{	
		log.info(urlcounter++ +" urlToBeProcessed " +urlToBeProcessed);
		
		try 
		{
			//Example URL http://example.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING"
			URL url = new URL(urlToBeProcessed);
			
			HashMap<String,String> mapURLobjects = new HashMap<>();   
			
			//Returns the protocol identifier component of the URL.
			//protocol = http
			mapURLobjects.put("protocol", url.getProtocol());
			
			//Returns the authority component of the URL.
			//authority = example.com:80
			mapURLobjects.put("authority", url.getAuthority());
			
			//Returns the host name component of the URL.
			//host = example.com
			mapURLobjects.put("host", url.getHost());
			
			//Returns the port number component of the URL. The getPort method returns an integer that is the port number. If the port is not set, getPort returns -1.
			//port = 80
			mapURLobjects.put("port", String.valueOf(url.getPort()));
			
			//Returns the path component of this URL.
			//path = /docs/books/tutorial/index.html
			mapURLobjects.put("path", url.getPath());
			
			//Returns the query component of this URL.
			//query = name=networking
			mapURLobjects.put("query", url.getQuery());
			
			//Returns the filename component of the URL. The getFile method returns the same as getPath, plus the concatenation of the value of getQuery, if any.
			//filename = /docs/books/tutorial/index.html?name=networking
			mapURLobjects.put("filename", url.getFile());
			
			//Returns the reference component of the URL.
			//ref = DOWNLOADING
			mapURLobjects.put("ref", url.getRef());

			return mapURLobjects;			
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
