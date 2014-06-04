package it.polimi.deib.provaFinale.cantiniDignani.view;

import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

public class PannelloTessere extends JPanel {

	private static final long serialVersionUID = -594444461810094160L;

	JPanel panelMontagna;
	JPanel panelDeserto;
	JPanel panelLago;
	JPanel panelBosco;
	JPanel panelPascolo;
	JPanel panelCampo;
	
	Image[] immaginiTerreni = new Image[TipoTerritorio.values().length];
	Image imgMontagna;
	Image imgDeserto;
	Image imgLago;
	Image imgBosco;
	Image imgPascolo;
	Image imgCampo;
	
	public PannelloTessere() {
		super(new GridLayout(6, 1));
		
		//carico le immagini
		//for()
		imgMontagna = creaCasella("src/images/montagna.png");
		imgDeserto = creaCasella("src/images/deserto.png");
		imgLago = creaCasella("src/images/montagna.png");
		imgMontagna = creaCasella("src/images/montagna.png");
		imgMontagna = creaCasella("src/images/montagna.png");
		imgMontagna = creaCasella("src/images/montagna.png");
		
		//inizializzo i panel
		panelMontagna = new BackgroundPanel(imgMontagna);
		
		this.setSize(100, 600);
		
	}
	
	private Image creaCasella(String percorsoImg) {
		return Toolkit.getDefaultToolkit().getImage(percorsoImg).getScaledInstance(100, 100, 0);
	}

}
