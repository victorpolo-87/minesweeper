package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.Main;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.JLabel;

public class VentanaRanking extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable facil;
	private JTable normal;
	private JTable dificil;
	private int dificultad=1;


	public VentanaRanking() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 894, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		
		JLabel lblNewLabel_3 = new JLabel("VICTORIA");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 6;
		gbc_lblNewLabel_3.gridy = 0;
		contentPane.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 6;
		gbc_panel.gridy = 1;
		contentPane.add(panel, gbc_panel);
		
		
		
		
		
		
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridheight = 2;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 6;
		gbc_panel_1.gridy = 2;
		contentPane.add(panel_1, gbc_panel_1);
		
		
		
		if (dificultad==1) {
			JLabel lblNewLabel = new JLabel("Ranking Dificultad Facíl:");
			panel.add(lblNewLabel);
			
		facil = new JTable(6,2);
		panel_1.add(facil);
		
		facil.setValueAt("Nombre", 0, 0);
		facil.setValueAt("Puntos", 0, 1);
		} else if (dificultad==2) {
			JLabel lblNewLabel_1 = new JLabel("Ranking Dificultad Normal:");
			panel.add(lblNewLabel_1);
		normal = new JTable(6,2);
		panel_1.add(normal);
		
		normal.setValueAt("Nombre", 0, 0);
		normal.setValueAt("Puntos", 0, 1);
		} else if (dificultad==3) {
			JLabel lblNewLabel_2 = new JLabel("Ranking Dificultad Dificil:");panel.add(lblNewLabel_2);
			dificil = new JTable(6,2);
			panel_1.add(dificil);
			
			dificil.setValueAt("Nombre", 0, 0);
			dificil.setValueAt("Puntos", 0, 1);
		}
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Main.cerrarRanking();
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 11;
		gbc_btnVolver.gridy = 5;
		contentPane.add(btnVolver, gbc_btnVolver);
	}

}