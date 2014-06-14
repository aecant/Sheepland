package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;


public class PedinaConContatoreView extends PedinaView {
	
	private static final long serialVersionUID = 6864251732112605373L;
	
	private JLabel lblContatore= new JLabel("0");

	public PedinaConContatoreView(Rectangle r) {
		super(r);
		
		lblContatore.setBounds((CostantiGui.DIMENSIONE_PECORA.width / 2), (CostantiGui.DIMENSIONE_PECORA.height / 2) - 5, 15, 15);
		lblContatore.setFont(CostantiGui.FONT_CONTATORI_ANIMALI);
		lblContatore.setName("contatore");
		add(lblContatore);
	}
	
	public PedinaConContatoreView(int posizioneX, int posizioneY, int larghezza, int altezza) {
		this(new Rectangle(posizioneX, posizioneY, larghezza, altezza));
	}
	
	public PedinaConContatoreView(int posizioneX, int posizioneY, Dimension dimensione) {
		this(new Rectangle(posizioneX, posizioneY, dimensione.width, dimensione.height));
	}
	
	public PedinaConContatoreView(Point coordinate, Dimension dimensione) {
		this(new Rectangle(coordinate.x, coordinate.y, dimensione.width, dimensione.height));
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
