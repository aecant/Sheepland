package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPanel;

public class PedinaView extends JPanel {

	private static final long serialVersionUID = 3540874383960169152L;
	
	/**
	 * Costruttore della pedina nella posizione e delle dimensioni specifitate nel rettangolo
	 * @param r
	 */
	public PedinaView(Rectangle r) {
		this.setBounds(r);
		this.setLayout(null);
		this.setOpaque(false);
	}
	
	public PedinaView(int posizioneX, int posizioneY, int larghezza, int altezza) {
		this(new Rectangle(posizioneX, posizioneY, larghezza, altezza));
	}
	
	public PedinaView(int posizioneX, int posizioneY, Dimension dimensione) {
		this(new Rectangle(posizioneX, posizioneY, dimensione.width, dimensione.height));
	}
	
	public PedinaView(Point coordinate, Dimension dimensione) {
		this(new Rectangle(coordinate.x, coordinate.y, dimensione.width, dimensione.height));
	}
	
	/**
	 * Metodo che muove la pedina con tanto di animazione, dal punto attuale
	 * fino al punto destinazione passato
	 * 
	 * @param destinazione
	 */
	protected void muovi(Point destinazione) {
		Point[] posizioni = creaArrayPosizioniSpostamento(destinazione);
		for (Point p : posizioni) {
			setPosizione(p);
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				System.err.println(e);
			}
		}
		setPosizione(destinazione);
	}

	/**
	 * Crea i punti della traiettoria per lo spostamento della pedina (per ora
	 * una retta)
	 * 
	 * @param destinazione
	 * @return un array di punti formanti la traiettoria
	 */
	private Point[] creaArrayPosizioniSpostamento(Point destinazione) {
		Point[] posizioni = new Point[CostantiGui.NUM_PASSI_SPOSTAMENTO];
		posizioni[0] = this.getPosizione();
		double dx = (double) (destinazione.getX() - posizioni[0].getX()) / CostantiGui.NUM_PASSI_SPOSTAMENTO;
		double dy = (double) (destinazione.getY() - posizioni[0].getY()) / CostantiGui.NUM_PASSI_SPOSTAMENTO;
		for (int i = 1; i < CostantiGui.NUM_PASSI_SPOSTAMENTO; i++) {
			posizioni[i] = new Point((int) posizioni[0].getX() + (int) (dx * i), (int) posizioni[0].getY() + (int) (dy * i));
		}
		return posizioni;
	}

	/**
	 * Metodo che disegna l'animale in un punto, però quel punto rappresenta il
	 * centro dell'immagine al contrario del setLocation()
	 * 
	 * @param coordinate
	 *            punto centrale di dove disegnare l'immagine
	 */
	private void setPosizione(Point coordinate) {
		this.setLocation(coordinate.x - (getWidth() / 2), coordinate.y - (getHeight() / 2));
	}

	/**
	 * Metodo che restituisce la coordinata centrale dell'animale invece che
	 * quella dell'angolo in alto a sinistra
	 * 
	 * @return coordinata centrale animale
	 */
	private Point getPosizione() {
		return new Point(getLocation().x + (getWidth() / 2), getLocation().y + (getHeight() / 2));
	}

	/**
	 * Metodo che elimina l'animale dal tabellone e invoca il repaint()
	 */
	protected void elimina() {
		Container container = getParent();
		getParent().remove(this);
		container.repaint();
	}
}
