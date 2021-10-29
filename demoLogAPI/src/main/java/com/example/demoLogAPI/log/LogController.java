package com.example.demoLogAPI.log;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/log")
public class LogController {

	private final LogService logService;
	
	@Autowired
	public LogController(LogService logService) {
		this.logService = logService;
	}
	
	@GetMapping
	public List<LogDefaultRecord> testLog(){
		
		return logService.testLog();
		
	}
	
	@PostMapping
	public void addLogEntry(@RequestBody LogDefaultRecord logRecord) {

		logService.addLogEntry(logRecord);

	}
	
	@PostMapping(path= "/text", consumes = "text/plain; charset: utf-8")
	public void addLogeEntryText(@RequestBody String logEntry) {
		
		logService.addLogEntryText(logEntry);
		
	}
	
	
	
}
