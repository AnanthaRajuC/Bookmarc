package io.github.anantharajuc.bookmarc.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import io.github.anantharajuc.bookmarc.model.Bookmark;
import io.github.anantharajuc.bookmarc.model.ExecutionSummary;
import io.github.anantharajuc.bookmarc.repository.BookmarkRepository;
import io.github.anantharajuc.bookmarc.repository.ExecutionSummaryRepository;
import io.github.anantharajuc.bookmarc.service.FileService;
import lombok.var;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class FileServiceImpl implements FileService
{
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Autowired
	private ExecutionSummaryRepository executionSummaryRepository;
	
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	@Override
	public HashMap<String, String> fileConditionCheck() 
	{
		log.info("Working Directory = " + System.getProperty("user.dir"));
		
		String bookmarcFolder = System.getProperty("user.dir")+"\\bm";
		String bookmarcFolderProcessed = bookmarcFolder+"\\processed";
		
		File fileBookmarcFolder = new File(bookmarcFolder);
		File fileBookmarcProcessedFolder = new File(bookmarcFolderProcessed);
		
		if (!fileBookmarcFolder.exists()) 
		{
			log.info("No Folder");
			
			fileBookmarcFolder.mkdir();
			fileBookmarcProcessedFolder.mkdir();
			
            log.info("Folder created");
        }
		else
		{
			if (!fileBookmarcProcessedFolder.exists()) 
			{
				fileBookmarcProcessedFolder.mkdir();
			}
		}
		
		log.info("");
		log.info("----> STEP 01 : Checking for Valid HTML files.");
		log.info("");
		
		File folder = new File(bookmarcFolder);
		File[] files = folder.listFiles();
		
		int validFilesCount = 0;
		
		HashMap<String,String> mapBookmarkFileDetails = new HashMap<>();
		List<String> listBookmarkFilesNames = new ArrayList<>();   
		
		for (File file : files)
        {	
			long fileLength      = file.length();
			String fileExtension = FilenameUtils.getExtension(file.getName());
			
			if(file.isFile() && fileExtension.equalsIgnoreCase("html") && fileLength>0)
			{
				//Converting file size from bytes to KB
				fileLength = fileLength/1000;
				
				validFilesCount++;
				 
				listBookmarkFilesNames.add(file.getName());
				
				mapBookmarkFileDetails.put("file"+validFilesCount+"Name", file.getName());
				mapBookmarkFileDetails.put(file.getName()+"fileSize", String.valueOf(fileLength));
				
				log.info("file "+validFilesCount+"    : "+mapBookmarkFileDetails.get("file"+validFilesCount+"Name"));
				log.info("file size : "+mapBookmarkFileDetails.get(mapBookmarkFileDetails.get("file"+validFilesCount+"Name")+"fileSize")+" KB");
				log.info("");
			}			
        } 
		
		otherServicesImpl.setValidHTMLfilesCount(validFilesCount); 
		
		log.info("* Valid HTML files count : "+validFilesCount);

		return mapBookmarkFileDetails;
	}

	@Override
	public void exportBookmarcToCSV() 
	{
		log.info("");
		log.info("----> Exporting Bookmarc to .CSV file");
		
		String fileName = "src/main/resources/Bookmark.csv";
        Path myPath = Paths.get(fileName);
        
        var bookmarks = bookmarkRepository.findAll();
        
        try (var writer = Files.newBufferedWriter(myPath, StandardCharsets.UTF_8)) 
        {
        	StatefulBeanToCsv<Bookmark> beanToCsv = new StatefulBeanToCsvBuilder<Bookmark>(writer)
										        			.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
										                    .build();

            beanToCsv.write(bookmarks);
        } 
        catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) 
        {
			e.printStackTrace();
		}
        
        log.info("----> Export completed - Bookmarc to .CSV file");
	}

	@Override
	public void importBookmarcCSV() 
	{
		log.info("");
		log.info("----> Importing Bookmarc .CSV file");
	}

	@Override
	public void importBrowserBookmark() 
	{
		log.info("");
		log.info("----> importBrowserBookmark");
		
		HashMap<String, String> mapBookmarkFileDetails = fileConditionCheck();
		
		if(!mapBookmarkFileDetails.isEmpty())
		{			
			log.info("");
			log.info("----> STEP 02 : Processing HTML files.");			
			
			for(int i=0; i<otherServicesImpl.getValidHTMLfilesCount(); i++)
			{
				int j = i+1;

				String fileSizeKey = mapBookmarkFileDetails.get("file"+j+"Name");
				
				String executionIdStart = Long.toString(System.currentTimeMillis());
				log.info("executionIdStart "+executionIdStart);
				appServiceImpl.htmlParser(mapBookmarkFileDetails.get("file"+j+"Name"));
				
				String executionIdEnd=Long.toString(System.currentTimeMillis());
				log.info("executionIdEnd "+executionIdEnd);
				
				ExecutionSummary executionSummary = new ExecutionSummary();	
				
				executionSummary.setFileName(mapBookmarkFileDetails.get("file"+j+"Name"));
				executionSummary.setFileSize(mapBookmarkFileDetails.get(fileSizeKey+"fileSize")); 
				executionSummary.setUrlCount(otherServicesImpl.getUrlCount()); 
				
				executionSummary.setExecutionId(executionIdStart+"-"+executionIdEnd);
				
				executionSummaryRepository.save(executionSummary);
			}
		}
		else
		{
			log.info("");
			log.info("There are no files in bm folder.");
		}
		
	}
}
