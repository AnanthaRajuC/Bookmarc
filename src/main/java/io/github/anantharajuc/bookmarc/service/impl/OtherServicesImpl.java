package io.github.anantharajuc.bookmarc.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.anantharajuc.bookmarc.model.ApplicationSettings;
import io.github.anantharajuc.bookmarc.repository.ApplicationSettingsRepository;
import io.github.anantharajuc.bookmarc.service.OtherServices;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@Service
public class OtherServicesImpl implements OtherServices
{
	private int validHTMLfilesCount;	
	private int urlCount;
	
	//Application Settings
	private String applicationName;
	private String applicationVersion;
	
	@Autowired
	private ApplicationSettingsRepository applicationSettingsRepository;
	
	@Override
	public void loadApplicationSettings() 
	{
		log.info("-----> Loading Application Settings Value");
		
		List<ApplicationSettings> applicationSettingsList = applicationSettingsRepository.findAll();
		
		HashMap<String, String> applicationSettingsHashMap = new HashMap<>(); 
		
		for(int i = 0; i< applicationSettingsList.size(); i++)
		{
			applicationSettingsHashMap.put(applicationSettingsList.get(i).getSettingsKey(), applicationSettingsList.get(i).getSettingsValue());
		}
		
		setApplicationName(applicationSettingsHashMap.get("applicationName"));
		setApplicationVersion(applicationSettingsHashMap.get("applicationVersion"));
	}
}
