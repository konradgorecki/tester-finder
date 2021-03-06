package com.tester.finder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TesterFinderApplication {

    public static void main(String[] args) {
        SpringApplication.run(TesterFinderApplication.class, args);
    }
}
