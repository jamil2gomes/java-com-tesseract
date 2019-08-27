package com.lp3.util;

import java.io.File;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class Conversor {

	private ITesseract imagem2String;
	
	public Conversor() {
			
		File diretorio = new File("tessdata");
		String caminho = diretorio.getAbsolutePath();
		
		this.imagem2String = new Tesseract();
		this.imagem2String.setDatapath(caminho);
		this.imagem2String.setLanguage("eng");
	}
	
	
	public String converte(String caminho) throws TesseractException {
		return imagem2String.doOCR(new File(caminho));
	}
	
	
	
}
