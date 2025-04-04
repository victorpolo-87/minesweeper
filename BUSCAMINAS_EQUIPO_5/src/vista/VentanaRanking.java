package vista;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Dimension;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel contentPane_1;
	private JTable facil;
	private int dificultad=1;
	private String nombre="";


	public VentanaRanking() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 150, 535, 445);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		
		
		//JPanel y fondo

        contentPane_1 = new JPanel() {

            private final ImageIcon fondo = new ImageIcon("src/images/fondoVentanas.jpg"); 

            @Override

            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                if (fondo.getImage() != null) {

                    g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

                }

            }

        };

		setContentPane(contentPane_1);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane_1.setLayout(gbl_contentPane);
		
		
		JLabel lblNewLabel_3 = new JLabel("VICTORIA");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 6;
		gbc_lblNewLabel_3.gridy = 0;
		contentPane_1.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		
		//Boton para volver al menu principal
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
				ventanaMenuPrincipal.setVisible(true);
			}
		});
		
		
		
		
			JLabel ranking = new JLabel(nombre);
			GridBagConstraints gbc_ranking = new GridBagConstraints();
			gbc_ranking.insets = new Insets(0, 0, 5, 5);
			gbc_ranking.gridx = 6;
			gbc_ranking.gridy = 1;
			contentPane_1.add(ranking, gbc_ranking);
		
		facil = new JTable(11,2);
		facil.setPreferredSize(new Dimension(225, 175));
		facil.setMinimumSize(new Dimension(200, 150));
		GridBagConstraints gbc_facil = new GridBagConstraints();
		gbc_facil.gridheight = 3;
		gbc_facil.insets = new Insets(0, 0, 5, 5);
		gbc_facil.gridx = 6;
		gbc_facil.gridy = 2;
		contentPane_1.add(facil, gbc_facil);
		
		facil.setValueAt("Nombre", 0, 0);
		facil.setValueAt("Puntos", 0, 1);
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 11;
		gbc_btnVolver.gridy = 5;
		contentPane_1.add(btnVolver, gbc_btnVolver);
	}

}