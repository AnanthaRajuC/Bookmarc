package io.github.anantharajuc.bookmarc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bookmark_live_chrome")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookmarkLiveChrome extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="date_added", nullable = true)
	private String date_added;
	
	@Column(name="guid", nullable = true)
	private String guid;
	
	@Column(name="bookmark_bar_id", nullable = true)
	private String bookmarkBarId;
	
	@Column(name="name", nullable = true)
	private String name;
	
	@Column(name="type", nullable = true)
	private String type;
	
	@Column(name="url", nullable = true)
	private String url;
}
