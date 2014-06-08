package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.view.Costanti;

import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PecoraView extends JPanel {

	private static final long serialVersionUID = 4393985322855180188L;
	private JLabel lblContatore;
	private PecoraImmagine pi;

	public PecoraView(int x, int y) {
		this.setBounds(new Rectangle(new Point(x, y), Costanti.dimensionePecora));
		this.setLayout(null);
		this.setOpaque(false);
		
		lblContatore = new JLabel("0");
		lblContatore.setBounds((Costanti.dimensionePecora.width/2), (Costanti.dimensionePecora.height/2)-5, 10, 10);
		
		pi = new PecoraImmagine();
		pi.setBounds(new Rectangle(new Point(0, 0), Costanti.dimensionePecora));
		
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
		Point[] posizioni = new Point[Costanti.risoluzioneSpostamento];
		posizioni[0] = this.getLocation();
		Point temp = new Point(posizioni[0]);
		for(int i=1; i < Costanti.risoluzioneSpostamento; i++) {
			temp.translate((int) (destinazione.getX() - temp.getX())/(Costanti.risoluzioneSpostamento-i), (int) (destinazione.getY() - temp.getY())/(Costanti.risoluzioneSpostamento-i)); 
			posizioni[i] = new Point(temp);
		}
		return posizioni;
	}
}
