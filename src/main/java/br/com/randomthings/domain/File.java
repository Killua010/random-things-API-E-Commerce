package br.com.randomthings.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@MappedSuperclass
@Setter
public class File extends NamedEntity {
	
	@Basic
	@Column(name = "origin_name", nullable = true, length = 100)
	protected String originName;

	@Basic
	@Column(name = "size", nullable = false)
	protected Long size;

	@Basic
	@Column(name = "type", nullable = false, length = 100)
	protected String type;
}
