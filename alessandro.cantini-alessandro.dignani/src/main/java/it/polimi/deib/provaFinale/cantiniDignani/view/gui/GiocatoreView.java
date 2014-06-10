package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class GiocatoreView extends JPanel {

	static final long serialVersionUID = 554747254657044516L;
	
	JPanel panelNome = new JPanel(new FlowLayout());
	JLabel lblNome;
	
	JPanel panelSoldi;
	JLabel lblSoldi;

	public GiocatoreView(Giocatore g) {
		super();
		setPreferredSize(CostantiGui.dimensionePanelGiocatore);
		setBackground(g.getPastori().get(0).getColore().coloreView);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lblNome = new JLabel(g.getNome());
		lblNome.setFont(new Font("Chalkduster", Font.PLAIN, 16));
		lblNome.setVerticalAlignment(SwingConstants.CENTER);
		
		panelNome.setBackground(CostantiGui.coloreSfondoNomeGiocatore);
		panelNome.add(lblNome);
		
		lblSoldi = new JLabel(g.getDenaro().toString());
		lblSoldi.setFont(new Font("Farisi", Font.PLAIN, 30));
		
		Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.percorsoImmagini + "soldi.png").getScaledInstance(CostantiGui.dimensionePanelTessera.width, CostantiGui.dimensionePanelTessera.height, 0);
		panelSoldi = new BackgroundPanel(img);
		panelSoldi.setPreferredSize(CostantiGui.dimensioneAgnelloPanelSoldi);
		panelSoldi.add(lblSoldi);
		panelSoldi.setBackground(new Color(0, 0, 0, 0));
		lblSoldi.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(panelNome, BorderLayout.CENTER);
		add(panelSoldi, BorderLayout.WEST);
	}
}
