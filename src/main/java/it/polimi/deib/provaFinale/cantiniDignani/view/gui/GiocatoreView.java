package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
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

/**
 * Classe che si occupa della visualizzazione grafica di un giocatore
 * @author alessandrodignani
 *
 */
public class GiocatoreView extends JPanel {

	static final long serialVersionUID = 554747254657044516L;
	
	JPanel contenitore = new JPanel();
	
	JPanel panelNome = new JPanel(new FlowLayout());
	JLabel lblNome;
	
	JPanel panelSoldi;
	JLabel lblSoldi;

	protected GiocatoreView(Giocatore g) {
		super();
		setPreferredSize(CostantiGui.DIMENSIONE_PANEL_GIOCATORE);
		setOpaque(false);
		setLayout(new BorderLayout());
		
		contenitore.setPreferredSize(CostantiGui.DIMENSIONE_GIOCATORE_NON_CORRENTE);
		contenitore.setBackground(g.getPastori().get(0).getColore().getColoreView());
		contenitore.setLayout(new BorderLayout());
		contenitore.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		lblNome = new JLabel(g.getNome());
		lblNome.setFont(CostantiGui.FONT_NOME_GIOCATORE);
		lblNome.setVerticalAlignment(SwingConstants.CENTER);
		lblNome.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblSoldi = new JLabel(g.getDenaro().toString());
		lblSoldi.setFont(CostantiGui.FONT_SOLDI);
		
		Image img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png").getScaledInstance(CostantiGui.DIMENSIONE_PANEL_TESSERA.width, CostantiGui.DIMENSIONE_PANEL_TESSERA.height, 0);
		panelSoldi = new BackgroundPanel(img);
		panelSoldi.setPreferredSize(CostantiGui.DIMENSIONE_PANEL_SOLDI);
		panelSoldi.add(lblSoldi);
		panelSoldi.setBackground(new Color(0, 0, 0, 0));
		lblSoldi.setHorizontalAlignment(SwingConstants.CENTER);
		
		panelNome.setBackground(CostantiGui.COLORE_SFONDO_NOME_GIOC);
		panelNome.setLayout(new BorderLayout());
		panelNome.add(lblNome, BorderLayout.CENTER);
		panelNome.add(panelSoldi, BorderLayout.WEST);
		
		contenitore.add(panelNome, BorderLayout.CENTER);
		
		add(contenitore, BorderLayout.EAST);
	}

	protected void aggiorna() {
		lblSoldi.setText("");
		lblSoldi.setText(MainClient.getDatiPartita().getGiocatore(lblNome.getText()).getDenaro().toString());
		panelSoldi.repaint();
		panelNome.repaint();
		contenitore.repaint();
		repaint();
	}

	protected void setCorrente() {
		contenitore.setPreferredSize(CostantiGui.DIMENSIONE_GIOCATORE_CORRENTE);
		aggiorna();
	}

	protected void setNonCorrente() {
		contenitore.setPreferredSize(CostantiGui.DIMENSIONE_GIOCATORE_NON_CORRENTE);
		aggiorna();
	}
}
