package vista;

import java.awt.EventQueue;
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
import modelo.Casilla;
import modelo.CasillaBomba;
import modelo.CasillaNumero;
import modelo.CasillaVacia;
import modelo.Juego;

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
    private ImageIcon imgBombFlaggedLost; // Nueva imagen para bombas marcadas al perder
    private ImageIcon[] imgNumeros;

    public VentanaJuego(int x, int y, Juego juego) {
    	setResizable(false);
        this.x = x;
        this.y = y;
        this.juego = juego;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 690, 477);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        JPanel panel = new JPanel();
        GridBagConstraints gbc_panel = new GridBagConstraints();
        gbc_panel.insets = new Insets(0, 0, 5, 5);
        gbc_panel.fill = GridBagConstraints.BOTH;
        gbc_panel.gridx = 1;
        gbc_panel.gridy = 1;
        contentPane.add(panel, gbc_panel);

        // Configurar el GridBagLayout del panel para la cuadrícula de botones
        GridBagLayout gbl_panel = new GridBagLayout();
        panel.setLayout(gbl_panel);

        // Cargar imágenes
        imgUnrevealed = new ImageIcon("src/images/blank-0000.jpg");
        imgEmpty = new ImageIcon("src/images/open0-0000.jpg");
        imgBomb = new ImageIcon("src/images/bombrevealed-0000.jpg");
        imgFlag = new ImageIcon("src/images/bombflagged-0000.jpg");
        imgBombFlaggedLost = new ImageIcon("src/images/bombmisflagged-0000.jpg"); // Nueva imagen
        imgNumeros = new ImageIcon[9];
        for (int i = 1; i <= 8; i++) {
            imgNumeros[i] = new ImageIcon("src/images/" + i + ".jpg");
        }

        // Obtener el tamaño de la imagen
        Dimension buttonSize = new Dimension(imgUnrevealed.getIconWidth(), imgUnrevealed.getIconHeight());

        // Genera la cuadrícula en base a posiciones
        int filas = x;
        int columnas = y;
        botones = new JButton[filas][columnas];
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.fill = GridBagConstraints.NONE; // No estirar los botones
        gbc.weightx = 0.0; // No expandir horizontalmente
        gbc.weighty = 0.0; // No expandir verticalmente

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                gbc.gridx = columna;
                gbc.gridy = fila;
                JButton boton = new JButton();
                boton.setIcon(imgUnrevealed);
                // Ajustar el tamaño del botón al de la imagen
                boton.setPreferredSize(buttonSize);
                boton.setMinimumSize(buttonSize);
                boton.setMaximumSize(buttonSize);
                // Quitar márgenes y bordes
                boton.setMargin(new Insets(0, 0, 0, 0));
                boton.setBorderPainted(false);
                botones[fila][columna] = boton;
                panel.add(boton, gbc);

                // Listener para clics
                final int f = fila;
                final int c = columna;
                boton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON1) { // Clic izquierdo
                            juego.procesarClic(f, c, false);
                            actualizarBotones();
                        } else if (e.getButton() == MouseEvent.BUTTON3) { // Clic derecho
                            juego.procesarClic(f, c, true);
                            actualizarBotones();
                        }
                    }
                });
            }
        }
        pack();
        setLocationRelativeTo(null);
    }

    private void actualizarBotones() {
        Casilla[][] casillas = juego.getTablero().getCasillas();
        boolean juegoPerdido = juego.getEstadoJuego() == modelo.EstadoEnum.PERDIDO;

        for (int fila = 0; fila < x; fila++) {
            for (int columna = 0; columna < y; columna++) {
                Casilla casilla = casillas[fila][columna];
                JButton boton = botones[fila][columna];

                if (juegoPerdido && casilla instanceof CasillaBomba) {
                    if (casilla.marcada) {
                        boton.setIcon(imgBombFlaggedLost); // Bomba marcada al perder
                    } else {
                        boton.setIcon(imgBomb); // Bomba sin marcar al perder
                    }
                } else if (casilla.marcada) {
                    boton.setIcon(imgFlag);
                } else if (!casilla.revelada) {
                    boton.setIcon(imgUnrevealed);
                } else {
                    if (casilla instanceof CasillaBomba && !casilla.marcada) {
                        boton.setIcon(imgBombFlaggedLost);
                    } else if (casilla instanceof CasillaNumero) {
                        int numero = ((CasillaNumero) casilla).getNumero();
                        boton.setIcon(imgNumeros[numero]);
                    } else if (casilla instanceof CasillaVacia) {
                        boton.setIcon(imgEmpty);
                    }
                }
            }
        }
    }
}