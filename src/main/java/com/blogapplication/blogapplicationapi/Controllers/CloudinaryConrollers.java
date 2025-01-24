package com.blogapplication.blogapplicationapi.Controllers;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.blogapplicationapi.Services.CloudinaryService;

@RestController
public class CloudinaryConrollers {
	@Autowired
	CloudinaryService coudinaryService;

	@PostMapping("api/cloudinary")
	public Map uploadImages(@RequestParam(value = "images") MultipartFile file) {
		try {
			System.out.println(file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map result = coudinaryService.upload(file);
		return result;
	}
}
