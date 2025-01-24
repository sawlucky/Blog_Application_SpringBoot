package com.blogapplication.blogapplicationapi.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogapplication.blogapplicationapi.Model.Category;

// it is not good to write @Reposistory here  because spring will create a proxy class which will 
// give the implementation of the interface
@Repository
public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
