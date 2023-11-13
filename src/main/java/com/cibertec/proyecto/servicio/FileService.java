package com.cibertec.proyecto.servicio;

import java.io.IOException;
import java.net.MalformedURLException;
import org.springframework.core.io.Resource;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	public Resource load(String filename) throws MalformedURLException;

    public String copy(MultipartFile file) throws IOException;

    public boolean delete(String filename);
	
}
