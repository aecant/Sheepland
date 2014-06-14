package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMessaggi extends JPanel{

	private static final long serialVersionUID = -5161847623471545868L;
	
	private JLabel lblMessaggi;
	
	protected PanelMessaggi() {
		setLayout(new BorderLayout());
		setBackground(CostantiGui.COLORE_SFONDO_MESSAGGI);
		
		lblMessaggi = new JLabel();
		lblMessaggi.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessaggi.setVerticalAlignment(SwingConstants.CENTER);
		lblMessaggi.setFont(CostantiGui.FONT_MESSAGGI);
		lblMessaggi.setForeground(CostantiGui.COLORE_TESTO_MESSAGGI);

		
		add(lblMessaggi, BorderLayout.CENTER);
		setVisible(false);
	}
	
	public void visualizzaMessaggio(String messaggio) {
		lblMessaggi.setText(messaggio);
		setBounds(0, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height) / 2, CostantiGui.DIMENSIONE_PANEL_MESSAGGI.width, CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height);
		setVisible(true);
		
		
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
		setVisible(false);
	}
}
