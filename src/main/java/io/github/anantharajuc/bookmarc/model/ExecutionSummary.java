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
@Table(name = "execution_summary")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level=AccessLevel.PRIVATE)
public class ExecutionSummary extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	@Column(name="file_name", nullable = true)
	String fileName;
	
	@Column(name="file_size", nullable = true)
	String fileSize;
	
	@Column(name="url_count", nullable = true)
	int urlCount;
	
	@Column(name="execution_id", nullable = true)
	String executionId;
}
