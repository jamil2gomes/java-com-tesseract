package com.lp3.util;

import javax.swing.JFileChooser;

public class Arquivo {

	private JFileChooser arquivo;
	private String path;

	public Arquivo() {
		this.arquivo = new JFileChooser();
	}

	public String getPath() {
		return this.path;
	}

	public void abrirArquivo() {
		Integer retorno = arquivo.showOpenDialog(null);

		if (retorno == JFileChooser.APPROVE_OPTION) {
			this.path = arquivo.getSelectedFile().toString();
		}

	}
}
