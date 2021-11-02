package com.example.demoLogAPI.log;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class LogService {

	//@Autowired
	private final LogRepository logRepository;
	
	
	public LogService(LogRepository logRepository) {
		
		this.logRepository = logRepository;
	}
	
	
	public List<LogDefaultRecord> testLog(){
		return logRepository.findAll();
	}
	
	
	public void addLogEntry(LogDefaultRecord logRecord) {
		
				
		logRecord.setDateTime(LocalDateTime.now());
		
		logRepository.save(logRecord);
	}
	
	public void addLogEntryText(String logEntry) {
		
		LogDefaultRecord logRecord = new LogDefaultRecord();
		logRecord.setDateTime(LocalDateTime.now());
		logRecord.setContent(logEntry);
		
		try (Scanner scanner = new Scanner(logEntry);) {
		    int nword = 0;
		    while (scanner.hasNext()) {
		    String sentWord = scanner.next();
		    if(nword == 2)
		    	logRecord.setLevel(sentWord);
		    else if(nword == 6) {
		    	logRecord.setClassName(sentWord);
		    	break;
		    }
		    nword++;
		    
		    }
		}
		
		
		logRepository.save(logRecord);
		
		
	}
	
	public void addLogEntryText(int levelPos, int classPos, String logEntry) {
		
		LogDefaultRecord logRecord = new LogDefaultRecord();
		logRecord.setDateTime(LocalDateTime.now());
		logRecord.setContent(logEntry);
		
		try (Scanner scanner = new Scanner(logEntry);) {
		    int nword = 0;
		    while (scanner.hasNext()) {
		    String sentWord = scanner.next();
		    if(nword == levelPos)
		    	logRecord.setLevel(sentWord);
		    else if(nword == classPos) {
		    	logRecord.setClassName(sentWord);
		    	break;
		    }
		    nword++;
		    
		    }
		}
		
		
		logRepository.save(logRecord);
		
		
	}
	
	
	public Page<LogDefaultRecord> getLogs(LocalDateTime start, LocalDateTime end, int pageSize, int pageNumber){
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return logRepository.findByDateTimeBetween( start, end, pageable);

	}
	
	
	
}
