package io.github.anantharajuc.bookmarc.service.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.bookmarc.model.Bookmark;
import io.github.anantharajuc.bookmarc.repository.BookmarkRepository;
import io.github.anantharajuc.bookmarc.service.AppService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class AppServiceImpl implements AppService
{
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Value("${chrome.bookmarks.file.windows}")
	private String chromeBookmarksFileWindows;
	
	private int fileCount = 0;
	
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

	@Override
	public void htmlParser(String htmlFilesName) 
	{
		fileCount++;
		
		Document htmlFile = null; 
		
		try 
		{
			log.info("");
			log.info("---- Parsing         : "+htmlFilesName);

			htmlFile = Jsoup.parse(new File(System.getProperty("user.dir")+"\\bm\\"+htmlFilesName), "ISO-8859-1"); 
		} 
		catch (IOException e) 
		{ 
			e.printStackTrace(); 
		}

		String title = htmlFile.title(); 
		
		log.info("---- HTML Title Tag  : " + title); 
		
		Elements elements = htmlFile.select("a");
		
		otherServicesImpl.setUrlCount(elements.size()); 

		for(int i = 0; i<elements.size(); i++)
		{
			Elements link = elements.get(i).select("a");
			int urlCount = i+1;
			
			log.info(""); 
			log.info("---- URL  : "+urlCount+"/"+elements.size()+" in file "+fileCount+"/"+otherServicesImpl.getValidHTMLfilesCount());
			log.info(""); 
			log.info("link      : "+ link.toString()); 
			log.info("text      : "+ link.text()); 
			log.info("href      : "+ link.attr("href")); 
			
			String url = link.attr("href");
			
			if(url.contains("view-source:"))
			{
				log.info("url contains view-source:"); 
				url = url.replace("view-source:","");
				log.info("url      : "+ url);
			}
			
			String href = link.attr("href");
			
			if(href.contains("view-source:"))
			{
				log.info("href contains view-source:"); 
				href = href.replace("view-source:","");
				log.info("href      : "+ url);
			}
			
			//EPOCH date converted to human readable format
			Date date = new Date(Long.parseLong(link.attr("add_date"))* 1000L);
			 
			log.info("add_date  : " + date); 

			Bookmark bookmark = new Bookmark();
			
			bookmark.setAddDate(date); 
			bookmark.setUrl(url); 
			bookmark.setText(link.text());
			
			HashMap<String,String> mapURLobjects = urlParser(href);
			
			if(!mapURLobjects.isEmpty())
			{			
				bookmark.setProtocol(mapURLobjects.get("protocol"));
				bookmark.setAuthority(mapURLobjects.get("authority"));
				bookmark.setHost(mapURLobjects.get("host"));
				bookmark.setPort(Integer.parseInt(mapURLobjects.get("port")));
				bookmark.setPath(mapURLobjects.get("path"));
				bookmark.setQuery(mapURLobjects.get("query")); 
				bookmark.setFilename(mapURLobjects.get("filename")); 
				bookmark.setRef(mapURLobjects.get("ref")); 
				bookmark.setEpochTime(System.currentTimeMillis()); 
			}
			
			bookmarkRepository.save(bookmark);
		}
		
		log.info("moving file : "+System.getProperty("user.dir")+"\\bm\\"+htmlFilesName);
		File htmlfile = new File(System.getProperty("user.dir")+"\\bm\\"+htmlFilesName);
		if(htmlfile.renameTo(new File(System.getProperty("user.dir")+"\\bm\\processed\\"+htmlFilesName)))
		{
			log.info("file moved successfully");
		}
	}

	@Override
	public void getBookmarks() 
	{
		log.info("Calendar.DAY_OF_MONTH  : "+Calendar.DAY_OF_MONTH);
		log.info("LocalDateTime.now()  : "+LocalDateTime.now());
		log.info("LocalDate.now()  : "+LocalDate.now());
	}

	@Override
	//@Async
	public void BookmarkLiveChromeParser() 
	{
		log.info("chromeBookmarksFileWindows  : "+chromeBookmarksFileWindows);
		
		JSONParser parser = new JSONParser();
		
		try 
		{
			Object obj = parser.parse(new FileReader(chromeBookmarksFileWindows));
			
			JSONObject jsonObject = (JSONObject) obj;
			
			String checksum = (String) jsonObject.get("checksum");
			
			log.info("checksum "+checksum);
			
			JSONObject roots =  (JSONObject) jsonObject.get("roots");

			JSONObject bookmarkBar = (JSONObject) roots.get("bookmark_bar");
			
			Object children = bookmarkBar.get("children");
			
			JSONArray bookmarkList = (JSONArray) children;
			
			log.info("bookmarkList "+bookmarkList.size()); 
			
			for(int i=0; i<bookmarkList.size(); i++)
			{
				JSONObject bookmarkJSON = (JSONObject) bookmarkList.get(i);
				
				Bookmark bookmark = new Bookmark();
				
				String url = (String) bookmarkJSON.get("url"); 
						
				Date date = new Date(Long.parseLong((String) bookmarkJSON.get("date_added")));
				log.info("date  - "+date);
				log.info("date bm  - "+bookmarkJSON.get("date_added"));
				log.info("date parsed - "+Long.parseLong((String) bookmarkJSON.get("date_added")));
				
				long unix_seconds = Long.parseLong((String) bookmarkJSON.get("date_added"));
				log.info("unix_seconds - "+unix_seconds);
				Date dateD = new Date(unix_seconds * 1000L);
				log.info("dateD - "+dateD);
				SimpleDateFormat jdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				jdf.setTimeZone(TimeZone.getDefault());
				log.info("jdf.format - "+jdf.format(dateD));

				String java_date = jdf.format(dateD);
				log.info("java_date - "+java_date);
				Date date1 = jdf.parse(java_date);
				log.info("date1 - "+date1);
				
				bookmark.setUrl(url);
				bookmark.setAddDate(date); 
				bookmark.setText((String) bookmarkJSON.get("name")); 				
				bookmark.setSource("browser");
				
				if(url.contains("view-source:"))
				{
					url = url.replace("view-source:","");
				}
				
				HashMap<String,String> mapURLobjects = urlParser(url);
				
				if(!mapURLobjects.isEmpty())
				{					
					bookmark.setProtocol(mapURLobjects.get("protocol"));
					bookmark.setAuthority(mapURLobjects.get("authority"));
					bookmark.setHost(mapURLobjects.get("host"));
					bookmark.setPort(Integer.parseInt(mapURLobjects.get("port")));
					bookmark.setPath(mapURLobjects.get("path"));
					bookmark.setQuery(mapURLobjects.get("query")); 
					bookmark.setFilename(mapURLobjects.get("filename")); 
					bookmark.setRef(mapURLobjects.get("ref")); 
					bookmark.setEpochTime(System.currentTimeMillis()); 
				}
				
				bookmarkRepository.save(bookmark); 
			}
		} 
		catch (IOException | ParseException | java.text.ParseException e) 
		{
			e.printStackTrace();
		}	
	} 

	@Override
	public void classifyHost() 
	{
		List<Bookmark> unclassifiedHosts = bookmarkRepository.getUnclassifiedBookmarks();
		
		if(unclassifiedHosts.size()!=0)
		{
			bookmarkRepository.updateWebsiteCategory();
		}
	}
}
