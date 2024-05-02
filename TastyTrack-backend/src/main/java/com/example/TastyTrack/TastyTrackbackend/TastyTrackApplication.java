package com.example.TastyTrack.TastyTrackbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TastyTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(TastyTrackApplication.class, args);
	}

}
