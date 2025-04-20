package com.yourname.summariser;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.yourname.summariser.Entity.SummaryRecord;
import com.yourname.summariser.repository.SummaryRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;


@SpringBootApplication
@RestController
@RequestMapping("/summarize")
public class SummarizerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SummarizerApplication.class, args); // Start Spring Boot Application
    }

}    