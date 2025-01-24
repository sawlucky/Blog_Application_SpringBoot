package com.blogapplication.blogapplicationapi.Services.Impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.blogapplicationapi.Services.CloudinaryService;
import com.cloudinary.Cloudinary;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

	@Autowired
	private Cloudinary cloudinary;

	@Override
	public Map upload(MultipartFile file) {
		try {
			Map uploadResult = cloudinary.uploader().upload(file.getBytes(), Map.of());
//			ApiResponse<T>
			return uploadResult;
		} catch (Exception e) {
			throw new RuntimeException("Image uploading failed: " + e.getMessage());
		}
	}

}
