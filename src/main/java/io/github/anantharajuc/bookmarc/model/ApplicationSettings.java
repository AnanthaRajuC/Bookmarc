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
@Table(name = "application_settings")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApplicationSettings extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	@Column(name="settings_Key", nullable = false)
	String settingsKey;
	
	@Column(name="settings_Value", nullable = false)
	String settingsValue;
}