package com.blogapplication.blogapplicationapi.Model;

import java.time.Instant;
import org.hibernate.annotations.CreationTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "post")
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private int postId;

	@Column(name = "post_title", nullable = false, length = 255)
	private String postTitle;

	@Column(name = "post_content", nullable = false, columnDefinition = "TEXT")
	private String postContent;

	@Column(name = "post_image", length = 500)
	private String postImage;

	@CreationTimestamp
	@Column(name = "post_date", nullable = false, updatable = false)
	private Instant postDate;

	@ManyToOne(optional = false)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@ManyToOne(optional = false) 
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
