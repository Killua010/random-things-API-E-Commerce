package br.com.randomthings.storage;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class LocalStorage {
	
	@Value("${storage.path}")
	private String pathStorage;
	
	private Path path;
	
	@PostConstruct
	public void init() {
		this.path = getDefault().getPath(pathStorage, "files");
		try {
			Files.createDirectories(path);
		} catch (IOException error) {
			throw new RuntimeException("Error creating local files' folder.", error);
		}
	}
	
	public void store(MultipartFile file, String name) {
		try {
			file.transferTo(new File(this.path.toAbsolutePath().toString() + getDefault().getSeparator() + name));
		} catch (IOException erro) {
			throw new RuntimeException("Erro ao salvar o arquivo local. ", erro);
		}
	}
	
	public byte[] loadBytes(String name) {
		try {
			return Files.readAllBytes(this.path.resolve(name));
		} catch (IOException erro) {
			erro.printStackTrace();
			throw new RuntimeException("Erro ao carregar o arquivo local. ", erro);
		}
	}

}
