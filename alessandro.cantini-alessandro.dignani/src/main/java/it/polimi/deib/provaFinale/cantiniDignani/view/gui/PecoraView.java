package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PecoraView extends JPanel {

	private static final long serialVersionUID = 4393985322855180188L;
	private JLabel lblContatore;
	private PecoraImmagine pi;

	public PecoraView(int posizioneX, int posizioneY) {
		this.setBounds(new Rectangle(new Point(posizioneX, posizioneY), CostantiGui.DIMENSIONE_PECORA));
		this.setLayout(null);
		this.setOpaque(false);
		
		lblContatore = new JLabel("0");
		lblContatore.setBounds((CostantiGui.DIMENSIONE_PECORA.width/2), (CostantiGui.DIMENSIONE_PECORA.height/2)-5, 10, 10);
		
		pi = new PecoraImmagine();
		pi.setBounds(new Rectangle(new Point(0, 0), CostantiGui.DIMENSIONE_PECORA));
		
		this.add(lblContatore);
		this.add(pi);
		
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("Pecora in [" + e.getX() + "][" + e.getY() + "]");
				
			}
		});
	}

	public void muoviPecora(Point destinazione) {
		Point[] posizioni = creaArrayPosizioniSpostamento(destinazione);
		for(Point p : posizioni) {
			this.setLocation(p);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		this.setLocation(destinazione);
	}

	private Point[] creaArrayPosizioniSpostamento(Point destinazione) {
		Point[] posizioni = new Point[CostantiGui.NUM_PASSI_SPOSTAMENTO];
		posizioni[0] = this.getLocation();
		Point temp = new Point(posizioni[0]);
		for(int i=1; i < CostantiGui.NUM_PASSI_SPOSTAMENTO; i++) {
			temp.translate((int) (destinazione.getX() - temp.getX())/(CostantiGui.NUM_PASSI_SPOSTAMENTO-i), (int) (destinazione.getY() - temp.getY())/(CostantiGui.NUM_PASSI_SPOSTAMENTO-i)); 
			posizioni[i] = new Point(temp);
		}
		return posizioni;
	}
}
