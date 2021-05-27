package io.github.anantharajuc.bookmarc.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.github.anantharajuc.bookmarc.model.enumeration.WebsiteCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "bookmark")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class Bookmark extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="text", nullable = true, length = 600)
	String text;
	
	@Column(name="add_date",nullable = true, updatable = true)
    Date addDate;
	
	@Column(name="url", nullable = true, length = 600)
	String url;
	
	@Column(name="protocol", nullable = true)
	String protocol;
	
	@Column(name="authority", nullable = true)
	String authority;
	
	@Column(name="host", nullable = true)
	String host;
	
	@Column(name="port", nullable = true)
	int port;
	
	@Column(name="path", nullable = true, length = 600)
	String path;
	
	@Column(name="query", nullable = true, length = 600)
	String query;

	@Column(name="filename", nullable = true, length = 600)
	String filename;
	
	@Column(name="ref", nullable = true, length = 600)
	String ref;
	
	@Column(name="epoch_time", nullable = true)
	Long epochTime;
	
	@Column(name="source", nullable = true)
	String source = "bookmark file";
	
	@Enumerated(EnumType.STRING)
	@Column(name="website_category", nullable = true)
	WebsiteCategory websiteCategory;
}
