package com.lp3.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.lp3.dao.VeiculoDAO;
import com.lp3.infra.ConnectionFactory;
import com.lp3.util.Arquivo;
import com.lp3.util.Conversor;
import com.lp3.util.TrataString;

import net.sourceforge.tess4j.TesseractException;

public class Principal {

	/**
	 * 
	 */

	private JFrame frmLeitorDePlacas;
	private JTextField textField;
	private JLabel placaLbl;
	private JLabel lblNewLabel;
	private JMenu mnIniciar;
	private JMenuItem mntmAbrirImagem;
	private JSeparator separator;
	private JButton btnCapturarImagem;
	private Webcam webcam;
	private JMenuBar menuBar;
	private WebcamPanel painel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws TesseractException {

		Principal window = new Principal();
		window.frmLeitorDePlacas.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public Principal() {
		criaComponentesFrame();
		acoes();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void acoes() {

		btnCapturarImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				capturarImagem();
				try {
					String placa = converteImagem("placa-teste.png");
					textField.setText(placa);
					buscaPlacaNoBanco(placa);
					System.out.println(placa);
				} catch (TesseractException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

		mntmAbrirImagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				buscarImagem();
			}
		});

	}

	private void criaComponentesFrame() {

		frmLeitorDePlacas = new JFrame();
		frmLeitorDePlacas.setResizable(false);
		frmLeitorDePlacas.setTitle("Leitor de Placas");
		frmLeitorDePlacas.setBounds(100, 100, 661, 695);
		frmLeitorDePlacas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLeitorDePlacas.setLocationRelativeTo(null);
		frmLeitorDePlacas.getContentPane().setLayout(null);

		btnCapturarImagem = new JButton("Capturar Imagem");
		placaLbl = new JLabel("");
		placaLbl.setHorizontalAlignment(SwingConstants.CENTER);

		btnCapturarImagem.setBounds(36, 595, 168, 38);
		frmLeitorDePlacas.getContentPane().add(btnCapturarImagem);

		lblNewLabel = new JLabel("Placa:");
		lblNewLabel.setBounds(222, 595, 66, 38);
		frmLeitorDePlacas.getContentPane().add(lblNewLabel);
		textField = new JTextField();
		textField.setFont(new Font("Dialog", Font.PLAIN, 27));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setBounds(294, 575, 223, 59);
		frmLeitorDePlacas.getContentPane().add(textField);
		textField.setColumns(10);

		webcam = Webcam.getDefault();
		webcam.open();

		painel = new WebcamPanel(webcam);
		painel.setBounds(12, 26, 640, 480);

		frmLeitorDePlacas.getContentPane().add(painel);

		menuBar = new JMenuBar();
		frmLeitorDePlacas.setJMenuBar(menuBar);

		mnIniciar = new JMenu("Iniciar");
		menuBar.add(mnIniciar);

		mntmAbrirImagem = new JMenuItem("Abrir Imagem");

		mnIniciar.add(mntmAbrirImagem);

		separator = new JSeparator();
		menuBar.add(separator);
	}

	private void buscarImagem() {

		try {

			Arquivo arquivo = new Arquivo();
			arquivo.abrirArquivo();
			String path = arquivo.getPath();

			if (path != null) {

				String placa = converteImagem(path);
				textField.setText(placa);
				buscaPlacaNoBanco(placa);
				System.out.println(placa);

			} else {
				JOptionPane.showMessageDialog(null, "Você não selecionou uma imagem.");
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	private void capturarImagem() {
		try {

			ImageIO.write(webcam.getImage(), "PNG", new File("placa-teste.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private String converteImagem(String path) throws TesseractException {

		Conversor conversor = new Conversor();
		return TrataString.removeCharacterSpecials(conversor.converte(path));
	}

	private void buscaPlacaNoBanco(String placa) {

		try (Connection conexao = new ConnectionFactory().getConnectionMySQL()) {

			VeiculoDAO veiculoDAO = new VeiculoDAO(conexao);

			if (veiculoDAO.procuraVeiculoPor(placa) != 0) {
				textField.setBackground(Color.green);
				textField.setForeground(Color.WHITE);
				JOptionPane.showMessageDialog(null, "Entrada Autorizada. Bem vindo!");

			} else {
				textField.setForeground(Color.WHITE);
				textField.setBackground(Color.red);
				JOptionPane.showMessageDialog(null, "Entrada Não Autorizada. Cadastre seu veículo no sistema!");

			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}
