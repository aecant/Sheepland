package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Component;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraView extends PedinaConContatoreView {

	private static final long serialVersionUID = 4393985322855180188L;
	
	private JLabel pi;

	public PecoraView(int posizioneX, int posizioneY) {
		super(posizioneX, posizioneY, CostantiGui.DIMENSIONE_PECORA);
		
		pi = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "pecora.png").getScaledInstance(CostantiGui.DIMENSIONE_PECORA.width, CostantiGui.DIMENSIONE_PECORA.height, Image.SCALE_SMOOTH)));
		pi.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PECORA));

		this.add(pi);

		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Component c = e.getComponent();
				if(c  instanceof PecoraView) {
					((PecoraView) c).incrementa();
				}
				
			}
		});
	}
	
	public PecoraView(Point coordinate) {
		this(coordinate.x, coordinate.y);
	}
}
