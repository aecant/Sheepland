package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MotivoLancioDado;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PanelMessaggi extends JPanel {

	private static final long serialVersionUID = -5161847623471545868L;
	
	private JLabel lblMessaggi;
	Image img;
	
	protected PanelMessaggi() {
		
		setBorder(new EmptyBorder(5, 5, 5, 5));
		
		lblMessaggi = new JLabel();
		lblMessaggi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessaggi.setVerticalAlignment(SwingConstants.CENTER);
		lblMessaggi.setFont(CostantiGui.FONT_MESSAGGI);
		lblMessaggi.setForeground(CostantiGui.COLORE_TESTO_MESSAGGI);
		
		setVisible(false);
	}
	
	public void visualizzaMessaggio(String messaggio) {
		setLayout(new BorderLayout());
		setBackground(CostantiGui.COLORE_SFONDO_MESSAGGI);
		lblMessaggi.setText(messaggio);
		add(lblMessaggi, BorderLayout.CENTER);
		setBounds(0, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height) / 2, CostantiGui.DIMENSIONE_PANEL_MESSAGGI.width, CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height);
		setVisible(true);
		Gui.getFinestraPartita().repaint();
		repaint();
		
		
		/* prova dinamica
		int dimX = 0;
		int dimY = 0;
		
		double dx = CostantiGui.DIMENSIONE_PANEL_MESSAGGI.width / CostantiGui.NUM_FRAME_ANIM_MESS;
		double dy = CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height / CostantiGui.NUM_FRAME_ANIM_MESS;
		
		
		for(int i=0; i <= CostantiGui.NUM_FRAME_ANIM_MESS; i++) {
			dimX = (int) (dx * i);
			dimY = (int) (dy * i);
			
			setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - dimX) / 2, (CostantiGui.DIMENSIONE_SCHERMO.height - dimY) / 2, dimX, dimY);
			lblMessaggi.setLocation(0, 0);
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		*/
		
		try {
			Thread.sleep(CostantiGui.TEMPO_VISUALIZZAZIONE_MESSAGGIO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		stopMessaggio();
	}
	
	public void visualizzaLancioDado(Integer numero, MotivoLancioDado motivo) {
		setLayout(new BorderLayout(0, 5));
		setBackground(CostantiGui.COLORE_SFONDO_LANCIO_DADO);

		// Carico l'immagine del motivo del lancio
		img = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI_DADO + motivo.toString().toLowerCase() + ".png").getScaledInstance(CostantiGui.DIMENSIONE_IMG_MOTIVO_LANCIO.width, CostantiGui.DIMENSIONE_IMG_MOTIVO_LANCIO.height, Image.SCALE_SMOOTH);
		
		setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_PANEL_LANCIO_DADO.width) / 2, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_LANCIO_DADO.height) / 2, CostantiGui.DIMENSIONE_PANEL_LANCIO_DADO.width, CostantiGui.DIMENSIONE_PANEL_LANCIO_DADO.height);
		
		JPanel panelMotivo = new BackgroundPanel(img);
		panelMotivo.setPreferredSize(CostantiGui.DIMENSIONE_IMG_MOTIVO_LANCIO);
		
		JPanel panelDado = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JPanel dado = new BackgroundPanel(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI_DADO + "dado" + numero + ".png").getScaledInstance(CostantiGui.DIMENSIONE_IMG_MOTIVO_LANCIO.width, CostantiGui.DIMENSIONE_IMG_MOTIVO_LANCIO.height, Image.SCALE_SMOOTH));
		dado.setPreferredSize(CostantiGui.DIMENSIONE_IMG_DADO);
		dado.setBackground(CostantiGui.COLORE_SFONDO_LANCIO_DADO);
		
		panelDado.setOpaque(false);
		panelDado.add(dado);
		
		add(panelMotivo, BorderLayout.CENTER);
		add(panelDado, BorderLayout.SOUTH);
		
		visualizza();
		stopMessaggio();
	}

	private void visualizza() {
		setVisible(true);
		
		try {
			Thread.sleep(CostantiGui.TEMPO_VISUALIZZAZIONE_MESSAGGIO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void visualizzaAbbattimentoRiuscito() {
		Image imm = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "abbattimentoRiuscito.jpg").getScaledInstance(CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height, Image.SCALE_SMOOTH);
		visualizzaScenetta(imm);
	}
	
	public void visualizzaAbbattimentoFallito() {
		Image imm = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "abbattimentoFallito.jpg").getScaledInstance(CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height, Image.SCALE_SMOOTH);
		visualizzaScenetta(imm);
	}
	
	public void visualizzaAccoppiamentoRiuscito() {
		Image imm = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "accoppiamentoRiuscito.jpg").getScaledInstance(CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height, Image.SCALE_SMOOTH);
		visualizzaScenetta(imm);
	}
	
	public void visualizzaAccoppiamentoFallito() {
		Image imm = Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "accoppiamentoFallito.jpg").getScaledInstance(CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height, Image.SCALE_SMOOTH);
		visualizzaScenetta(imm);
	}
	
	public void visualizzaScenetta(Image imm) {
		setLayout(new FlowLayout());
		
		JPanel immagine = new BackgroundPanel(imm);
		immagine.setPreferredSize(CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA);
		add(immagine);
		setBounds((CostantiGui.DIMENSIONE_SCHERMO.width - CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width) / 2, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height) / 2, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.width, CostantiGui.DIMENSIONE_IMMAGINE_SCENETTA.height);
		
		visualizza();
		stopMessaggio();
	}

	private void stopMessaggio() {
		for(Component comp : getComponents()) {
			remove(comp);
		}
		setVisible(false);
	}
}
