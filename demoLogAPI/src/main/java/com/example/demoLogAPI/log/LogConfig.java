package com.example.demoLogAPI.log;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class LogConfig {

	@Bean
	CommandLineRunner logCommandLineRunner(LogRepository repository) {

		return args -> {
			
			LogDefaultRecord initialRecord = new LogDefaultRecord( LocalDateTime.now(), "TEST", "Log API", "initializing the log registry");
			
			repository.save(initialRecord);
			
//			repository.saveAll(
//						List.of(initialRecord, )
//					);
		};

	}
	
}
