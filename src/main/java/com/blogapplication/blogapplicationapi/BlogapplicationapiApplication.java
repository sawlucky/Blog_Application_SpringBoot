package com.blogapplication.blogapplicationapi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.*;

import com.cloudinary.Cloudinary;

@SpringBootApplication
public class BlogapplicationapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogapplicationapiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	public Cloudinary getCloudinary() {
	    Map<String, String> config = new HashMap<>();
	    config.put("cloud_name", "dd7gcrlnm");
	    config.put("api_key", "749124619917326");
	    config.put("api_secret", "8uIR4kv3G5E5bLgxlvK9bZWS7-Y");

	    return new Cloudinary(config);
	}


}
