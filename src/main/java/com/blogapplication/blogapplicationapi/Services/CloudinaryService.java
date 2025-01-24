package com.blogapplication.blogapplicationapi.Services;

import java.util.*;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
	public Map<String, String> upload(MultipartFile file);

}
