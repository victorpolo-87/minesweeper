package vista;

import java.awt.EventQueue;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;
import modelo.Dificultad;
import modelo.DificultadEnum;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;

public class VentanaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;

	public VentanaMenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ikasle.ARANGOYA.000\\Desktop\\logo.png.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 373);

		// JPanel y fondo
		contentPane = new JPanel() {
			private final ImageIcon fondo = new ImageIcon("src/images/fondoVentanas.jpg");

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (fondo.getImage() != null) {
					g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
				}
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// Configuración del GridBagLayout
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		JPanel panel = new JPanel();
		panel.setOpaque(false); // Hacer el panel transparente
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 4;
		gbc_panel.gridheight = 6;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 3;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel lblNewLabel = new JLabel("BUSCAMINAS");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		panel.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Introduce tu nombre:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.gridx = 1;
		gbc_textNombre.gridy = 2;
		panel.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Dificultad:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		Choice choiceDificultad = new Choice();
		GridBagConstraints gbc_choiceDificultad = new GridBagConstraints();
		gbc_choiceDificultad.insets = new Insets(0, 0, 5, 5);
		gbc_choiceDificultad.gridx = 1;
		gbc_choiceDificultad.gridy = 4;
		panel.add(choiceDificultad, gbc_choiceDificultad);

		JPanel panel_1 = new JPanel();
		panel_1.setOpaque(false); // Hacer el panel_1 transparente
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 0, 5);
		gbc_panel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 5;
		panel.add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 0, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		JButton btnJugar = new JButton("Jugar");
		GridBagConstraints gbc_btnJugar = new GridBagConstraints();
		gbc_btnJugar.insets = new Insets(0, 0, 0, 5);
		gbc_btnJugar.gridx = 0;
		gbc_btnJugar.gridy = 0;
		panel_1.add(btnJugar, gbc_btnJugar);

		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.gridx = 1;
		gbc_btnSalir.gridy = 0;
		panel_1.add(btnSalir, gbc_btnSalir);

		btnJugar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String seleccion = choiceDificultad.getSelectedItem();
				if (!seleccion.equals("")) {
					try {
						String nombre = textNombre.getText().trim();
						if (nombre.isEmpty()) {
							JOptionPane.showMessageDialog(VentanaMenuPrincipal.this, "Por favor, introduce tu nombre",
									"Advertencia", JOptionPane.WARNING_MESSAGE);
							return;
						}
						String seleccionNormalizada = seleccion.toUpperCase();
						DificultadEnum nivel = DificultadEnum.valueOf(seleccionNormalizada);
						Dificultad d1 = new Dificultad(nivel);
						Main.setNombreJugador(nombre);
						Main.setDificultad(d1);
						Main.getFilasColumnas(d1);
						Main.cambiarVentanaAJuego();
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(VentanaMenuPrincipal.this, "Selecciona una dificultad válida",
								"Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(VentanaMenuPrincipal.this, "Por favor, selecciona una dificultad",
							"Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Opciones del Choice
		choiceDificultad.add("");
		choiceDificultad.add("FACIL");
		choiceDificultad.add("NORMAL");
		choiceDificultad.add("DIFICIL");

		setLocationRelativeTo(null);
	}
}