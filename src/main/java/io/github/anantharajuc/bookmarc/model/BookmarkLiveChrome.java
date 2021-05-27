package io.github.anantharajuc.bookmarc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "bookmark_live_chrome")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class BookmarkLiveChrome extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="date_added", nullable = true)
	String date_added;
	
	@Column(name="guid", nullable = true)
	String guid;
	
	@Column(name="bookmark_bar_id", nullable = true)
	String bookmarkBarId;
	
	@Column(name="name", nullable = true)
	String name;
	
	@Column(name="type", nullable = true)
	String type;
	
	@Column(name="url", nullable = true)
	String url;
}
