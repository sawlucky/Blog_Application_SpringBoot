package com.blogapplication.blogapplicationapi.Utils;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostResponse<T> {
	
	private long totalElements;
	private int pageNumber;
	private int pageSize;
	private int totalPages;
	private T datas;
	private boolean lastPage;

}
