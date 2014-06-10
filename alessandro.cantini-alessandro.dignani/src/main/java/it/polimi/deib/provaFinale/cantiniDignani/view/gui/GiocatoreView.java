package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Giocatore;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
		setPreferredSize(CostantiGui.DIMENSIONE_PANEL_GIOCATORE);
		setBackground(g.getPastori().get(0).getColore().coloreView);
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lblNome = new JLabel(g.getNome());
		lblNome.setFont(CostantiGui.FONT_NOME_GIOCATORE);
		lblNome.setVerticalAlignment(SwingConstants.CENTER);
		
		panelNome.setBackground(CostantiGui.COLORE_SFONDO_NOME_GIOC);
		panelNome.add(lblNome);
		
		lblSoldi = new JLabel(g.getDenaro().toString());
		lblSoldi.setFont(CostantiGui.FONT_SOLDI);
		
		Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png").getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, 0);
		panelSoldi = new BackgroundPanel(img);
		panelSoldi.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_SOLDI);
		panelSoldi.add(lblSoldi);
		panelSoldi.setBackground(new Color(0, 0, 0, 0));
		lblSoldi.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(panelNome, BorderLayout.CENTER);
		add(panelSoldi, BorderLayout.WEST);
	}
}
