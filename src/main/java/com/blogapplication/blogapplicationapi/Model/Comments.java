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
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "comment_Id")
	private int commentId;
	@Column(name = "comment_Content", nullable = false, length = 255)
	private String commentContent;
	@CreationTimestamp
	@Column(name = "comment_Date", updatable = true, nullable = false)
	private Instant commentDate;

	@ManyToOne
	@JoinColumn(name = "post_id", nullable = false)
	private Post post;
}
