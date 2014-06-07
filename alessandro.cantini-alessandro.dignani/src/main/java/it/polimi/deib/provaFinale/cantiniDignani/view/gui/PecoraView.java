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
		Point pos = this.getLocation();
		while (this.getLocation().distance(destinazione) > 20) {
			Point temp = this.getLocation();
			temp.translate((int) (((destinazione.getX() - pos.getX()) / 20) ), (int) (((destinazione.getY() - pos.getY()) / 20) ));
			this.setLocation(temp);
			try {
				Thread.sleep(30);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		this.setLocation(destinazione);
	}
}
