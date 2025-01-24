package com.blogapplication.blogapplicationapi.Model;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
//import jakarta.validation.constraints.AssertFalse.List;
//import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
public class Category {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer category_id;
	@Column(name = "title", nullable = false)
	private String category_title;
	@Column(name = "description")
	private String category_description;
	
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL)
	private List<Post> post_list= new ArrayList<Post>();
}
