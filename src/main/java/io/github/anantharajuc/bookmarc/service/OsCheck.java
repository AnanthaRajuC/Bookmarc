package io.github.anantharajuc.bookmarc.service;

import java.util.Locale;

import lombok.extern.log4j.Log4j2;

@Log4j2
public final class OsCheck 
{
	 /*
	  * types of Operating Systems
	  */
	  public enum OSType 
	  {
		  WINDOWS, 
		  MAC_OS, 
		  LINUX, 
		  OTHERS
	  }
	  
	  // cached result of OS detection
	  protected static OSType detectedOS;
	  
	  /**
	   * detect the operating system from the os.name System property and cache
	   * the result
	   * 
	   * @returns - the operating system detected
	   */
	  public static OSType getOperatingSystemType() 
	  {
		  if (detectedOS == null) 
	    {
	      String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
	      log.info("OS "+OS);
	      if ((OS.indexOf("mac") >= 0) || (OS.indexOf("darwin") >= 0)) 
	      {
	        detectedOS = OSType.MAC_OS;
	        log.info("detectedOS "+detectedOS);
	      } 
	      else if (OS.indexOf("win") >= 0) 
	      {
	        detectedOS = OSType.WINDOWS;
	        log.info("detectedOS "+detectedOS);
	      } 
	      else if (OS.indexOf("nux") >= 0) 
	      {
	        detectedOS = OSType.LINUX;
	        log.info("detectedOS "+detectedOS);
	      } 
	      else 
	      {
	        detectedOS = OSType.OTHERS;
	        log.info("detectedOS "+detectedOS);
	      }
	    }
	    return detectedOS;
	  }
}
