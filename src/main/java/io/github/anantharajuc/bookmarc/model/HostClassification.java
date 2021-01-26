package io.github.anantharajuc.bookmarc.model;

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
@Table(name = "host_classification")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HostClassification extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="host", nullable = true)
	private String host;
	
	@Enumerated(EnumType.STRING)
	@Column(name="website_category", nullable = true)
	private WebsiteCategory websiteCategory;
}
