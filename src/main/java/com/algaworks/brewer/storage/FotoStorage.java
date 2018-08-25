package com.algaworks.brewer.storage;

import org.springframework.web.multipart.MultipartFile;

public interface FotoStorage {
	
	public static final String THUMBNAIL_PREFIX = "thumbnail.";
	
	public String salvar(MultipartFile[] files);

	public byte[] recuperarFoto(String nome);

	public byte[] recuperarThumbnail(String fotoCerveja);

	public void excluir(String foto);

	public String getUrl(String foto);
	

}