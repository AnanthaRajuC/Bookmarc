package io.github.anantharajuc.bookmarc.web;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import io.github.anantharajuc.bookmarc.model.FormCommand;
import io.github.anantharajuc.bookmarc.repository.BookmarkRepository;
import io.github.anantharajuc.bookmarc.repository.ExecutionSummaryRepository;
import io.github.anantharajuc.bookmarc.service.FileService;
import lombok.extern.log4j.Log4j2;

/**
 * Bookmark Controller
 *
 * @author <a href="mailto:arcswdev@gmail.com">Anantha Raju C</a>
 *
 */
@Log4j2
@Controller
@RequestMapping({"/", "/bookmark"})
public class BookmarkController 
{
	@Autowired
	private Environment environment;
	
	@Autowired
	private FileService fileServiceImpl;
	
	@Autowired
	private BookmarkRepository bookmarkRepository;
	
	@Autowired
	private ExecutionSummaryRepository executionSummaryRepository;
	
	@GetMapping("/home")
	public String home(Model model, @RequestParam(defaultValue="731") int period)
	{ 
		//model.addAttribute("data", bookmarkRepository.getAllBetweenDates(period)); 
		model.addAttribute("data", bookmarkRepository.findAll());
		
		//int day = bookmarkRepository.getAllBetweenDates(period).size();
		int day = 1;
		
		model.addAttribute("day", day);
		
		return "pages/home"; 
	}
	
	@GetMapping("/about")
    public String about() 
	{
		return "pages/about";
    }
	
	@GetMapping("/dashboard")
    public String dashboard() 
	{
        return "pages/dashboard";
    }
	
	@GetMapping("/import-bookmark")
    public String importBookmark() 
	{
		fileServiceImpl.importBrowserBookmark();
		
        return "pages/settings";
    }
	
	@GetMapping("/export-bookmark")
    public String exportBookmark() 
	{
		fileServiceImpl.exportBookmarcToCSV();
		
        return "pages/settings";
    }
	
	@GetMapping("/settings")
    public String settings(Model model) 
	{
		model.addAttribute("command", new FormCommand());
		
        return "pages/settings";
    }
	
	@GetMapping("/close")
	public String close()
	{
		log.info("App Shutdown");
		String port = environment.getProperty("local.server.port");
		
		String command = "curl --location --request POST http://localhost:"+port+"/actuator/shutdown";
		
		try 
		{
			Process process = Runtime.getRuntime().exec(command);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return "pages/close";
	}
	
	@GetMapping("/executionSummary")
	public String executionSummary(Model model, @RequestParam(defaultValue="0") int page)
	{ 
		model.addAttribute("data", executionSummaryRepository.findAll());
		
		log.info("");
		log.info(model.toString()); 
		
		return "pages/executionSummary"; 
	}
	
	@PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) 
	{
		log.info("/upload");
		log.info(file.getOriginalFilename());

		try 
		{
			log.info("fin");
			
			InputStream fin=file.getInputStream();    
			int i=fin.read();  
			
			log.info(fin.toString());
			log.info((char)i);    
  
            fin.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
        redirectAttributes.addFlashAttribute("message", "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/bookmark/home";
    }
}
