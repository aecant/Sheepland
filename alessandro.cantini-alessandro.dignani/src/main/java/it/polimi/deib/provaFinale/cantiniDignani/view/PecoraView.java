package it.polimi.deib.provaFinale.cantiniDignani.view;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PecoraView extends JLabel {

	private static final long serialVersionUID = 4393985322855180188L;

	public PecoraView(int x, int y) {
		super(new ImageIcon(Toolkit.getDefaultToolkit().getImage(Costanti.percorsoImmagini + "pecora.png").getScaledInstance(30, 20, 0)));
		this.setBounds(x, y, 30, 20);
		this.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				System.out.println("Pecora in [" + e.getX() + "][" + e.getY() + "]");
			}
		});
	}
}
