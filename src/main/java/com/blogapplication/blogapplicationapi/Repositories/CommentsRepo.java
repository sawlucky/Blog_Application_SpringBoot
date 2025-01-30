package com.blogapplication.blogapplicationapi.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapplication.blogapplicationapi.Model.Comments;
import com.blogapplication.blogapplicationapi.Model.Post;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {
      
	List<Comments> findByPost(Post post);
}
