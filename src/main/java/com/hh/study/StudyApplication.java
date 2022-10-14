package com.hh.study;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class StudyApplication {

    @Bean
    public ModelMapper modelMapper() { return new ModelMapper(); }

    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

}
