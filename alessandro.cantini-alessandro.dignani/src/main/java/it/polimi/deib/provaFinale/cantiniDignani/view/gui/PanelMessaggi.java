package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelMessaggi extends JPanel{

	private static final long serialVersionUID = -5161847623471545868L;
	
	private JLabel lblMessaggi;
	
	protected PanelMessaggi() {
		setLayout(new FlowLayout());
		setBackground(CostantiGui.COLORE_SFONDO_MESSAGGI);
		setBounds(0, (CostantiGui.DIMENSIONE_SCHERMO.height - CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height) / 2, CostantiGui.DIMENSIONE_PANEL_MESSAGGI.width,
				CostantiGui.DIMENSIONE_PANEL_MESSAGGI.height);
		
		lblMessaggi = new JLabel();
		lblMessaggi.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMessaggi.setVerticalTextPosition(SwingConstants.CENTER);
		
		add(lblMessaggi);
		setVisible(false);
	}
	
	public void visualizzaMessaggio(String messaggio) {
		lblMessaggi.setText(messaggio);
		setVisible(true);
		repaint();
		try {
			Thread.sleep(CostantiGui.TEMPO_VISUALIZZAZIONE_MESSAGGIO);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setVisible(false);
	}
}
