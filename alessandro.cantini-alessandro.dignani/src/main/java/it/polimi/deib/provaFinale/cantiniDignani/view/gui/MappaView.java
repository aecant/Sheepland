package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.ColorePastore;
import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoAnimale;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

public class MappaView extends BackgroundMappaPanel {

	private static final long serialVersionUID = 7034340670757736701L;

	protected Point[][] coordinateTerritori;
	protected Point[] coordinateStrade;
	
	protected List<SegnalinoStrada> segnalini = new ArrayList<SegnalinoStrada>();

	protected List<TerritorioView> territoriView = new ArrayList<TerritorioView>();

	public MappaView() {
		super(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "mappaSheepland.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_MAPPA.width, CostantiGui.DIMENSIONE_MAPPA.height, Image.SCALE_SMOOTH));
		this.setPreferredSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMaximumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setMinimumSize(CostantiGui.DIMENSIONE_MAPPA);
		this.setLayout(null);

		// Creo l'array con le coordinate scalate dei territori
		coordinateTerritori = new Point[Costanti.NUM_TERRITORI][TipoAnimale.values().length];
		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			for (int j = 0; j < TipoAnimale.values().length; j++) {
				coordinateTerritori[i][j] = new Point((int) (CostantiGui.COORDINATE_TERRITORI[i][j].getX() * CostantiGui.FATTORE_DI_SCALA),
						(int) (CostantiGui.COORDINATE_TERRITORI[i][j].getY() * CostantiGui.FATTORE_DI_SCALA));
			}
		}

		// Creo l'array con le coordinate scalate delle strade
		coordinateStrade = new Point[Costanti.NUM_STRADE];
		for (int i = 0; i < Costanti.NUM_STRADE; i++) {
			coordinateStrade[i] = new Point((int) (CostantiGui.COORDINATE_STRADE[i].getX() * CostantiGui.FATTORE_DI_SCALA),
					(int) (CostantiGui.COORDINATE_STRADE[i].getY() * CostantiGui.FATTORE_DI_SCALA));
		}

		for (int i = 0; i < Costanti.NUM_TERRITORI; i++) {
			territoriView.add(new TerritorioView(i, coordinateTerritori[i]));
		}
	}

	

	public void creaPastore(int strada, ColorePastore colore) {
		Point coordinataStrada = coordinateStrade[strada];
		PastoreView past = new PastoreView(coordinataStrada.x - (CostantiGui.DIMENSIONE_PASTORE.width / 2), coordinataStrada.y - (CostantiGui.DIMENSIONE_PASTORE.height / 2), colore);
		add(past);
		past.getParent().repaint();
	}

	protected void disegnaTerritorio(Integer codTerritorio) {
		territoriView.get(codTerritorio).aggiorna();
		territoriView.get(codTerritorio).disegna();
	}

	public Point[][] getCoordinateTerritori() {
		return coordinateTerritori;
	}

	public Point[] getCoordinateStrade() {
		return coordinateStrade;
	}

	public void movimentoPecora(TipoAnimale tipoOvino, int origine, int destinazione) {
		Point orig = territoriView.get(origine).getCoordinate(tipoOvino);
		Point dest = territoriView.get(destinazione).getCoordinate(tipoOvino);
		PedinaView pedina;
		switch (tipoOvino) {
		case AGNELLO: pedina = new AgnelloView(orig, 0);
			break;
		case MONTONE: pedina = new MontoneView(orig, 0);
			break;
		case PECORA: pedina = new PecoraView(orig, 0);
			break;
		default: pedina = null;
			break;
		}
		
		territoriView.get(origine).aggiorna();
		territoriView.get(origine).disegna();
		add(pedina);
		pedina.muovi(dest);
		remove(pedina);
		territoriView.get(destinazione).aggiorna();
		territoriView.get(destinazione).disegna();
	}

	public void movimentoPecoraNera(int origine, int destinazione) {
		territoriView.get(origine).getPecoraNera().muovi(territoriView.get(destinazione).getCoordinate(TipoAnimale.PECORA_NERA));
		territoriView.get(origine).aggiorna();
		territoriView.get(origine).disegna();
		territoriView.get(destinazione).aggiorna();
		territoriView.get(destinazione).disegna();
	}

	public void movimentoLupo(int origine, int destinazione) {
		territoriView.get(origine).getLupo().muovi(territoriView.get(destinazione).getCoordinate(TipoAnimale.LUPO));
		territoriView.get(origine).aggiorna();
		territoriView.get(origine).disegna();
		territoriView.get(destinazione).aggiorna();
		territoriView.get(destinazione).disegna();
	}



	public void inserisciSegnaliniIniziali(boolean[] stradeLibere) {
		for(int i = 0; i < Costanti.NUM_STRADE; i++) {
			if(stradeLibere[i]) {
				SegnalinoStrada segnalinoTemp = new SegnalinoStrada(coordinateStrade[i], false, i);
				segnalini.add(segnalinoTemp);
				add(segnalinoTemp);
				repaint();
			}
		}
	}
}
