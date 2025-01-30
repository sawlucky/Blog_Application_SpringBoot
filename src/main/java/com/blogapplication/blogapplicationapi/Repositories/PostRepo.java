package com.blogapplication.blogapplicationapi.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.blogapplication.blogapplicationapi.Model.Category;
import com.blogapplication.blogapplicationapi.Model.Post;
import com.blogapplication.blogapplicationapi.Model.User;

//@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {

	List<Post> findByUser(User user);

	Page<Post> findByCategory(Category category, Pageable p);

	Page<Post> findByUser(User user, Pageable p);

	 List<Post> findBypostTitleContaining(String title);

//	void deleteAllByUser(User user);

//	void deleteAllByUserId(Integer userId);
}
