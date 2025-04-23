package vista;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import modelo.Casilla;
import modelo.CasillaBomba;
import modelo.CasillaNumero;
import modelo.CasillaVacia;
import modelo.Juego;
import modelo.EstadoEnum;

public class VentanaJuego extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Juego juego;
	private int x;
	private int y;
	private JButton[][] botones;
	private ImageIcon imgUnrevealed;
	private ImageIcon imgEmpty;
	private ImageIcon imgBomb;
	private ImageIcon imgFlag;
	private ImageIcon imgBombFlaggedLost;
	private ImageIcon imgBombExploded;
	private ImageIcon[] imgNumeros;
	private ImageIcon[] imgDigitos;
	private ImageIcon imgDigitDash;
	private ImageIcon imgCaritaFeliz;
	private ImageIcon imgCaritaSorpresa;
	private ImageIcon imgCaritaTriste;
	private ImageIcon imgCaritaGana;
	private JLabel[] lblBombas;
	private JLabel[] lblTiempo;
	private JButton btnReset;

	public VentanaJuego(int x, int y, Juego juego) {
		setResizable(false);
		this.x = x;
		this.y = y;
		this.juego = juego;
		this.juego.setVentana(this); // Aqui estoy asociando la ventana con la clase juego
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);

		// Este apartado es del panel superior que contiene el tiempo y demas
		JPanel panelSuperior = new JPanel();
		GridBagConstraints gbc_panelSuperior = new GridBagConstraints();
		gbc_panelSuperior.gridwidth = 4;
		gbc_panelSuperior.insets = new Insets(5, 5, 5, 5);
		gbc_panelSuperior.fill = GridBagConstraints.HORIZONTAL;
		gbc_panelSuperior.gridx = 0;
		gbc_panelSuperior.gridy = 0;
		contentPane.add(panelSuperior, gbc_panelSuperior);
		GridBagLayout gbl_panelSuperior = new GridBagLayout();
		panelSuperior.setLayout(gbl_panelSuperior);

		// Este es el contador de las bombas
		JPanel panelBombas = new JPanel();
		GridBagConstraints gbc_panelBombas = new GridBagConstraints();
		gbc_panelBombas.insets = new Insets(0, 0, 0, 5);
		gbc_panelBombas.gridx = 0;
		gbc_panelBombas.gridy = 0;
		panelSuperior.add(panelBombas, gbc_panelBombas);
		lblBombas = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			lblBombas[i] = new JLabel();
			panelBombas.add(lblBombas[i]);
		}

		// Añadi el boton de reset como en el juego original
		btnReset = new JButton();
		GridBagConstraints gbc_btnReset = new GridBagConstraints();
		gbc_btnReset.insets = new Insets(0, 5, 0, 5);
		gbc_btnReset.gridx = 1;
		gbc_btnReset.gridy = 0;
		panelSuperior.add(btnReset, gbc_btnReset);

		// Aqui se gestiona el tiempo y se modifica el contador
		JPanel panelTiempo = new JPanel();
		GridBagConstraints gbc_panelTiempo = new GridBagConstraints();
		gbc_panelTiempo.insets = new Insets(0, 5, 0, 0);
		gbc_panelTiempo.gridx = 2;
		gbc_panelTiempo.gridy = 0;
		panelSuperior.add(panelTiempo, gbc_panelTiempo);
		lblTiempo = new JLabel[3];
		for (int i = 0; i < 3; i++) {
			lblTiempo[i] = new JLabel();
			panelTiempo.add(lblTiempo[i]);
		}

		// Aqui genero la cuadricula en base a la dificultad seleccionada

		JPanel panelCuadricula = new JPanel();
		GridBagConstraints gbc_panelCuadricula = new GridBagConstraints();
		gbc_panelCuadricula.insets = new Insets(5, 5, 5, 5);
		gbc_panelCuadricula.fill = GridBagConstraints.BOTH;
		gbc_panelCuadricula.gridx = 0;
		gbc_panelCuadricula.gridy = 1;
		gbc_panelCuadricula.gridwidth = 4;
		contentPane.add(panelCuadricula, gbc_panelCuadricula);
		GridBagLayout gbl_panelCuadricula = new GridBagLayout();
		panelCuadricula.setLayout(gbl_panelCuadricula);

		// Cargar imágenes
		imgUnrevealed = new ImageIcon("src/images/blank-0000.jpg");
		imgEmpty = new ImageIcon("src/images/open0.jpg");
		imgBomb = new ImageIcon("src/images/bombrevealed-0000.jpg");
		imgFlag = new ImageIcon("src/images/bombflagged-0000.jpg");
		imgBombFlaggedLost = new ImageIcon("src/images/bombmisflagged-0000.jpg");
		imgBombExploded = new ImageIcon("src/images/bombdeath-0000.jpg");
		imgNumeros = new ImageIcon[9];
		for (int i = 1; i <= 8; i++) {
			imgNumeros[i] = new ImageIcon("src/images/" + i + ".jpg");
		}

		// estas son las imagenes del contador de tiempo
		imgDigitos = new ImageIcon[10];
		for (int i = 0; i < 10; i++) {
			imgDigitos[i] = new ImageIcon("src/images/time" + i + ".jpg");
		}
		imgDigitDash = new ImageIcon("src/images/time-.jpg");

		// estas son las imagenes del boton de reset
		imgCaritaFeliz = new ImageIcon("src/images/logo.png.png");
		imgCaritaSorpresa = new ImageIcon("src/images/logo.png.png");
		imgCaritaTriste = new ImageIcon("src/images/logo.png.png");
		imgCaritaGana = new ImageIcon("src/images/logo.png.png");
		btnReset.setIcon(imgCaritaFeliz);
		btnReset.setPreferredSize(new Dimension(imgCaritaFeliz.getIconWidth(), imgCaritaFeliz.getIconHeight()));
		btnReset.setBorderPainted(false);

		// Acción del botón de reset
		btnReset.addActionListener(e -> {
			juego.reiniciarJuego();
		});

		// Configurar la cuadrícula
		Dimension buttonSize = new Dimension(imgUnrevealed.getIconWidth(), imgUnrevealed.getIconHeight());
		botones = new JButton[x][y];
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.fill = GridBagConstraints.NONE;

		for (int fila = 0; fila < x; fila++) {
			for (int columna = 0; columna < y; columna++) {
				gbc.gridx = columna;
				gbc.gridy = fila;
				JButton boton = new JButton();
				boton.setIcon(imgUnrevealed);
				boton.setPreferredSize(buttonSize);
				boton.setMinimumSize(buttonSize);
				boton.setMaximumSize(buttonSize);
				boton.setMargin(new Insets(0, 0, 0, 0));
				boton.setBorderPainted(false);
				botones[fila][columna] = boton;
				panelCuadricula.add(boton, gbc);

				final int f = fila;
				final int c = columna;
				boton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if (e.getButton() == MouseEvent.BUTTON1) {
							juego.procesarClic(f, c, false);
						} else if (e.getButton() == MouseEvent.BUTTON3) {
							juego.procesarClic(f, c, true);
						}
					}

					@Override
					public void mousePressed(MouseEvent e) {
						if (juego.getEstadoJuego() == EstadoEnum.EN_CURSO) {
							btnReset.setIcon(imgCaritaSorpresa);
						}
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						if (juego.getEstadoJuego() == EstadoEnum.EN_CURSO) {
							btnReset.setIcon(imgCaritaFeliz);
						}
					}
				});
			}
		}

		actualizarInterfaz();
		pack();
		setLocationRelativeTo(null);
	}

	public void actualizarInterfaz() {
		actualizarBotones();
		actualizarContadores();
		actualizarCarita();
	}

	private void actualizarBotones() {
		Casilla[][] casillas = juego.getTablero().getCasillas();
		boolean juegoPerdido = juego.getEstadoJuego() == modelo.EstadoEnum.PERDIDO;
		boolean juegoGanado = juego.getEstadoJuego() == modelo.EstadoEnum.GANADO;

		for (int fila = 0; fila < x; fila++) {
			for (int columna = 0; columna < y; columna++) {
				Casilla casilla = casillas[fila][columna];
				JButton boton = botones[fila][columna];

				if (juegoPerdido && casilla instanceof CasillaBomba) {
					if (juego.getCasillaClicada(fila, columna)) {
						boton.setIcon(imgBombExploded);
					} else if (casilla.marcada) {
						boton.setIcon(imgBombFlaggedLost);
					} else {
						boton.setIcon(imgBomb);
					}
				}  else if (casilla.marcada) {
					boton.setIcon(imgFlag); 
				} else if (!casilla.revelada) {
					boton.setIcon(imgUnrevealed); 
				} else {
					if (casilla instanceof CasillaBomba) {
						boton.setIcon(imgBomb); 
					} else if (casilla instanceof CasillaNumero) {
						int numero = ((CasillaNumero) casilla).getNumero();
						boton.setIcon(imgNumeros[numero]);
					} else if (casilla instanceof CasillaVacia) {
						boton.setIcon(imgEmpty); // open0.jpg
					}
				}
			}
		}

		// Cambiar a VentanaRanking si se gana
		if (juegoGanado) {
			dispose();
			VentanaRanking ventanaRanking = new VentanaRanking();
			ventanaRanking.setVisible(true);
		}
	}

	private void actualizarContadores() {
		// Bombas restantes
		int bombas = Math.max(0, juego.getNumBombasRestantes());
		int centenas = bombas / 100;
		int decenas = (bombas % 100) / 10;
		int unidades = bombas % 10;
		lblBombas[0].setIcon(imgDigitos[centenas]);
		lblBombas[1].setIcon(imgDigitos[decenas]);
		lblBombas[2].setIcon(imgDigitos[unidades]);

		// Tiempo
		int tiempo = Math.min(999, juego.getTiempo());
		centenas = tiempo / 100;
		decenas = (tiempo % 100) / 10;
		unidades = tiempo % 10;
		lblTiempo[0].setIcon(imgDigitos[centenas]);
		lblTiempo[1].setIcon(imgDigitos[decenas]);
		lblTiempo[2].setIcon(imgDigitos[unidades]);
	}

	private void actualizarCarita() { // en este metodo voy a añadir las imagenes del boton reset
		EstadoEnum estado = juego.getEstadoJuego();
		if (estado == EstadoEnum.GANADO) {
			btnReset.setIcon(imgCaritaGana);
		} else if (estado == EstadoEnum.PERDIDO) {
			btnReset.setIcon(imgCaritaTriste);
		} else {
			btnReset.setIcon(imgCaritaFeliz);
		}
	}
}