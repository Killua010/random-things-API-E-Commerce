package br.com.randomthings.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Entity
@Getter
@NoArgsConstructor
@Setter
@Table(name = "_image")
public class Image extends File {
	
	@Basic
	@Column(name = "description",  nullable = true)
	private String description;
	
	@Basic
	@Column(name = "heigth",  nullable = true)
	private double heigth;
	
	@Basic
	@Column(name = "width",  nullable = true)
	private double width;
	
	@Transient
	private MultipartFile file;
}
