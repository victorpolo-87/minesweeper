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

public class VentanaMenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;

	public VentanaMenuPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ikasle.ARANGOYA.000\\Desktop\\logo.png.png"));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 435, 455);

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
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0,
				0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Componentes
		/* Botón Info */
		JButton btnInfo = new JButton("!");
		GridBagConstraints gbc_btnInfo = new GridBagConstraints();
		gbc_btnInfo.insets = new Insets(0, 0, 5, 0);
		gbc_btnInfo.gridx = 7;
		gbc_btnInfo.gridy = 1;
		contentPane.add(btnInfo, gbc_btnInfo);

		JLabel lblNewLabel = new JLabel("BUSCAMINAS");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.gridwidth = 2;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 2;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Introduce tu nombre:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.gridwidth = 2;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 4;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);

		textNombre = new JTextField();
		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.gridwidth = 2;
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 3;
		gbc_textNombre.gridy = 5;
		contentPane.add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Dificultad:");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.gridwidth = 2;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 3;
		gbc_lblNewLabel_2.gridy = 7;
		contentPane.add(lblNewLabel_2, gbc_lblNewLabel_2);

		Choice choiceDificultad = new Choice();
		GridBagConstraints gbc_choiceDificultad = new GridBagConstraints();
		gbc_choiceDificultad.gridwidth = 2;
		gbc_choiceDificultad.insets = new Insets(0, 0, 5, 5);
		gbc_choiceDificultad.gridx = 3;
		gbc_choiceDificultad.gridy = 8;
		contentPane.add(choiceDificultad, gbc_choiceDificultad);
		// Opciones del Choice
        choiceDificultad.add("");
        choiceDificultad.add("Facil");
        choiceDificultad.add("Normal");
        choiceDificultad.add("Dificil");

        JButton btnJugar = new JButton("Jugar");
        btnJugar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String seleccion = choiceDificultad.getSelectedItem();
                if (!seleccion.equals("")) {
                    try {
                        String seleccionNormalizada = seleccion.toUpperCase();
                        DificultadEnum nivel = DificultadEnum.valueOf(seleccionNormalizada);
                        Dificultad d1 = new Dificultad(nivel);
                        Main.getFilasColumnas(d1); 
                        Main.cambiarVentanaAJuego(); 
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(VentanaMenuPrincipal.this,
                                "Selecciona una dificultad válida", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(VentanaMenuPrincipal.this,
                            "Por favor, selecciona una dificultad", "Advertencia", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
		GridBagConstraints gbc_btnJugar = new GridBagConstraints();
		gbc_btnJugar.insets = new Insets(0, 0, 5, 5);
		gbc_btnJugar.gridx = 3;
		gbc_btnJugar.gridy = 9;
		contentPane.add(btnJugar, gbc_btnJugar);

		JButton btnSalir = new JButton("Salir");
		GridBagConstraints gbc_btnSalir = new GridBagConstraints();
		gbc_btnSalir.insets = new Insets(0, 0, 5, 5);
		gbc_btnSalir.gridx = 4;
		gbc_btnSalir.gridy = 9;
		contentPane.add(btnSalir, gbc_btnSalir);
	}

}