package com.example.demoLogAPI.log;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/log")
public class LogController {

	//@Autowired
	private final LogService logService;
	
	private final String timeStr = " 00:00:00";
		
	public LogController(LogService logService) {
		this.logService = logService;
	}
	
	@GetMapping
	public List<LogDefaultRecord> testLog(){
		
		return logService.testLog();
		
	}
	
	@GetMapping(path= "/logs", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Page<LogDefaultRecord>> getLogs(
			@RequestParam(required=true) String startDate,
			@RequestParam(required=false) String endDate,
			@RequestParam(required=true) int pageSize,
			@RequestParam(required=true) int pageNumber) {
		
		LocalDateTime end;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); 
		LocalDateTime start = LocalDateTime.parse(startDate + timeStr, formatter);
		if(endDate == null) {
			end = LocalDateTime.now().plusDays(1);
		}else {
			end = LocalDateTime.parse(endDate + timeStr, formatter);
		}
	
		System.out.println("entrou no controller...");
		
		System.out.println("processou o controller...");
		
		return ResponseEntity.status(HttpStatus.OK)
		        .body( logService.getLogs(start, end, pageSize, pageNumber) );
		
	
		
	}
	
	@PostMapping
	public void addLogEntry(@RequestBody LogDefaultRecord logRecord) {

		logService.addLogEntry(logRecord);

	}
	
	@PostMapping(path= "/text", consumes = "text/plain; charset: utf-8")
	public void addLogeEntryText(@RequestBody String logEntry) {
		
		logService.addLogEntryText(logEntry);
		
	}
	
	@PostMapping(path= "/param", consumes = "text/plain; charset: utf-8")
	public void addLogEntryTextParam(
			@RequestParam(required = true) int levelPosition,
			@RequestParam(required = true) int classPosition,
			@RequestBody String logEntry) {
		
		logService.addLogEntryText(levelPosition, classPosition, logEntry);
		
	}
	

	
	
}
