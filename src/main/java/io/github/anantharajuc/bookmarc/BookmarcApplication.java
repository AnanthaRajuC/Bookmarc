package io.github.anantharajuc.bookmarc;

import java.awt.Desktop;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

import io.github.anantharajuc.bookmarc.service.OsCheck;
import io.github.anantharajuc.bookmarc.service.impl.AppServiceImpl;
import io.github.anantharajuc.bookmarc.service.impl.FileServiceImpl;
import io.github.anantharajuc.bookmarc.service.impl.OtherServicesImpl;
import lombok.extern.log4j.Log4j2;

/**
 * Bookmarc.
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@EnableJpaAuditing
@SpringBootApplication
@EnableAsync(proxyTargetClass=true)
public class BookmarcApplication implements CommandLineRunner
{	
	@Autowired
	private AppServiceImpl appServiceImpl;
	
	@Autowired
	private OtherServicesImpl otherServicesImpl;
	
	@Autowired
	private FileServiceImpl fileServiceImpl;
	
	@Autowired
	private Environment environment;
	
	public static void main(String[] args) 
	{		
		SpringApplicationBuilder builder = new SpringApplicationBuilder(BookmarcApplication.class);

		builder.headless(false);

		ConfigurableApplicationContext context = builder.run(args);
	}

	@Override
	public void run(String... args) throws Exception 
	{		
		log.info("--------------------------------------------------------------------------------------------------");

		log.info("");
		
		log.info("-----> Initial Application Settings Key Value Load.");	
		
		otherServicesImpl.loadApplicationSettings();
		
		log.info(otherServicesImpl.getApplicationName());
		log.info(otherServicesImpl.getApplicationVersion());
		
		OsCheck.OSType ostype=OsCheck.getOperatingSystemType();
		
		String port = environment.getProperty("local.server.port");
		
		log.info("application port : "+port);
		
		String url = "http://localhost:"+port+"/bookmark/home";

		log.info("-----> Starting Processing of Bookmarks' file's.");
		fileServiceImpl.importBrowserBookmark();
		log.info("-----> Finished Processing of Bookmarks' file's.");
		
		log.info("-----> Starting Processing of Live Bookmark's in Chrome Browser");
		appServiceImpl.BookmarkLiveChromeParser();
		log.info("-----> Finished Processing of Live Bookmark's in Chrome Browser");
		
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) 
		{
			log.info("opening application url : "+"http://localhost:"+port+"/bookmark/home");
			
		    Desktop.getDesktop().browse(new URI(url));
		}
		else
		{
			log.info("unable to open url");
		}
	}
}
