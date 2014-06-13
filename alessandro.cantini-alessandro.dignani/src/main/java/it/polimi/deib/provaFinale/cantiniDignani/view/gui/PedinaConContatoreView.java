package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import javax.swing.JLabel;


public class PedinaConContatoreView extends PedinaView {
	
	private static final long serialVersionUID = 6864251732112605373L;
	
	private JLabel lblContatore;

	public PedinaConContatoreView(int posizioneX, int posizioneY) {
		super(posizioneX, posizioneY);
		
		lblContatore = new JLabel("0");
		lblContatore.setBounds((CostantiGui.DIMENSIONE_PECORA.width / 2), (CostantiGui.DIMENSIONE_PECORA.height / 2) - 5, 15, 15);
		lblContatore.setFont(CostantiGui.FONT_CONTATORI_ANIMALI);
		add(lblContatore);
	}
	
	public void incrementa() {
		Integer n = getConteggio();
		n++;
		lblContatore.setText(n.toString());
		lblContatore.repaint();
	}
	
	public Integer getConteggio() {
		return Integer.valueOf(lblContatore.getText());
	}
}
