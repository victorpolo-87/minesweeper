package vista;

import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import modelo.GestorRanking;
import modelo.RankingEntry;

import java.util.List;

public class VentanaRanking extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tablaFacil;
    private JTable tablaNormal;
    private JTable tablaDificil;
    private String nombreJugador;

    public VentanaRanking(String nombre) {
        this.nombreJugador = nombre;
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 150, 535, 445);

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

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
        gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        gbl_contentPane.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
        gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
        contentPane.setLayout(gbl_contentPane);

        JLabel lblVictoria = new JLabel("VICTORIA");
        GridBagConstraints gbc_lblVictoria = new GridBagConstraints();
        gbc_lblVictoria.insets = new Insets(0, 0, 5, 5);
        gbc_lblVictoria.gridx = 3;
        gbc_lblVictoria.gridy = 0;
        contentPane.add(lblVictoria, gbc_lblVictoria);

        JLabel lblNombre = new JLabel("Jugador: " + nombreJugador);
        GridBagConstraints gbc_lblNombre = new GridBagConstraints();
        gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
        gbc_lblNombre.gridx = 3;
        gbc_lblNombre.gridy = 1;
        contentPane.add(lblNombre, gbc_lblNombre);

        // Tabla Fácil
        JLabel lblFacil = new JLabel("Fácil");
        GridBagConstraints gbc_lblFacil = new GridBagConstraints();
        gbc_lblFacil.insets = new Insets(0, 0, 5, 5);
        gbc_lblFacil.gridx = 1;
        gbc_lblFacil.gridy = 2;
        contentPane.add(lblFacil, gbc_lblFacil);

        tablaFacil = new JTable(6, 2);
        tablaFacil.setPreferredSize(new Dimension(150, 96));
        tablaFacil.setMinimumSize(new Dimension(150, 96));
        GridBagConstraints gbc_tablaFacil = new GridBagConstraints();
        gbc_tablaFacil.insets = new Insets(0, 0, 5, 5);
        gbc_tablaFacil.gridx = 1;
        gbc_tablaFacil.gridy = 3;
        contentPane.add(tablaFacil, gbc_tablaFacil);

        tablaFacil.setValueAt("Nombre", 0, 0);
        tablaFacil.setValueAt("Puntos", 0, 1);

        // Tabla Normal
        JLabel lblNormal = new JLabel("Normal");
        GridBagConstraints gbc_lblNormal = new GridBagConstraints();
        gbc_lblNormal.insets = new Insets(0, 0, 5, 5);
        gbc_lblNormal.gridx = 3;
        gbc_lblNormal.gridy = 2;
        contentPane.add(lblNormal, gbc_lblNormal);

        tablaNormal = new JTable(6, 2);
        tablaNormal.setPreferredSize(new Dimension(150, 96));
        tablaNormal.setMinimumSize(new Dimension(150, 96));
        GridBagConstraints gbc_tablaNormal = new GridBagConstraints();
        gbc_tablaNormal.insets = new Insets(0, 0, 5, 5);
        gbc_tablaNormal.gridx = 3;
        gbc_tablaNormal.gridy = 3;
        contentPane.add(tablaNormal, gbc_tablaNormal);

        tablaNormal.setValueAt("Nombre", 0, 0);
        tablaNormal.setValueAt("Puntos", 0, 1);

        // Tabla Difícil
        JLabel lblDificil = new JLabel("Difícil");
        GridBagConstraints gbc_lblDificil = new GridBagConstraints();
        gbc_lblDificil.insets = new Insets(0, 0, 5, 5);
        gbc_lblDificil.gridx = 5;
        gbc_lblDificil.gridy = 2;
        contentPane.add(lblDificil, gbc_lblDificil);

        tablaDificil = new JTable(6, 2);
        tablaDificil.setPreferredSize(new Dimension(150, 96));
        tablaDificil.setMinimumSize(new Dimension(150, 96));
        GridBagConstraints gbc_tablaDificil = new GridBagConstraints();
        gbc_tablaDificil.insets = new Insets(0, 0, 5, 5);
        gbc_tablaDificil.gridx = 5;
        gbc_tablaDificil.gridy = 3;
        contentPane.add(tablaDificil, gbc_tablaDificil);

        tablaDificil.setValueAt("Nombre", 0, 0);
        tablaDificil.setValueAt("Puntos", 0, 1);

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
        btnVolver.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                VentanaMenuPrincipal ventanaMenuPrincipal = new VentanaMenuPrincipal();
                ventanaMenuPrincipal.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnVolver = new GridBagConstraints();
        gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
        gbc_btnVolver.gridx = 3;
        gbc_btnVolver.gridy = 6;
        contentPane.add(btnVolver, gbc_btnVolver);

        cargarRankings();

        setLocationRelativeTo(null);
    }

    private void cargarRankings() {
        GestorRanking rankingManager = new GestorRanking();

        List<RankingEntry> rankingFacil = rankingManager.obtenerRanking("FACIL");
        for (int i = 0; i < rankingFacil.size(); i++) {
            RankingEntry entry = rankingFacil.get(i);
            tablaFacil.setValueAt(entry.getNombre(), i + 1, 0);
            tablaFacil.setValueAt(entry.getPuntuacion(), i + 1, 1);
        }

        List<RankingEntry> rankingNormal = rankingManager.obtenerRanking("NORMAL");
        for (int i = 0; i < rankingNormal.size(); i++) {
            RankingEntry entry = rankingNormal.get(i);
            tablaNormal.setValueAt(entry.getNombre(), i + 1, 0);
            tablaNormal.setValueAt(entry.getPuntuacion(), i + 1, 1);
        }

        List<RankingEntry> rankingDificil = rankingManager.obtenerRanking("DIFICIL");
        for (int i = 0; i < rankingDificil.size(); i++) {
            RankingEntry entry = rankingDificil.get(i);
            tablaDificil.setValueAt(entry.getNombre(), i + 1, 0);
            tablaDificil.setValueAt(entry.getPuntuacion(), i + 1, 1);
        }
    }
}