package io.github.anantharajuc.bookmarc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.bookmarc.enums.WebsiteCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bookmark extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="text", nullable = true, length = 600)
	private String text;
	
	@Column(name="add_date",nullable = true, updatable = true)
    private Date addDate;
	
	@Column(name="url", nullable = true, length = 600)
	private String url;
	
	@Column(name="protocol", nullable = true)
	private String protocol;
	
	@Column(name="authority", nullable = true)
	private String authority;
	
	@Column(name="host", nullable = true)
	private String host;
	
	@Column(name="port", nullable = true)
	private int port;
	
	@Column(name="path", nullable = true, length = 600)
	private String path;
	
	@Column(name="query", nullable = true, length = 600)
	private String query;

	@Column(name="filename", nullable = true, length = 600)
	private String filename;
	
	@Column(name="ref", nullable = true, length = 600)
	private String ref;
	
	@Column(name="epoch_time", nullable = true)
	private Long epochTime;
	
	@Column(name="source", nullable = true)
	private String source = "bookmark file";
	
	@Enumerated(EnumType.STRING)
	@Column(name="website_category", nullable = true)
	private WebsiteCategory websiteCategory;
}
