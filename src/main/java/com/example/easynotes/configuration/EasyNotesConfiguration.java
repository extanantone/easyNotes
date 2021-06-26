package com.example.easynotes.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EasyNotesConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}