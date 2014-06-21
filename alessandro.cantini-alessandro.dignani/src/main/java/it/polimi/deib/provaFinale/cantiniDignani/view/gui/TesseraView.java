package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.controller.MainClient;
import it.polimi.deib.provaFinale.cantiniDignani.model.CostantiModel;
import it.polimi.deib.provaFinale.cantiniDignani.model.Mazzo;
import it.polimi.deib.provaFinale.cantiniDignani.model.Tessera;
import it.polimi.deib.provaFinale.cantiniDignani.model.TipoTerritorio;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TesseraView extends BackgroundPanel {

	private static final long serialVersionUID = 8284029875922825876L;

	JPanel angolino = new JPanel();
	JLabel lblPossedute;
	JPanel panelCosto;
	JLabel moneta;
	
	Integer costo;
	Integer indice;

	protected TesseraView(Image img, Integer costo, Integer possedute, Integer indice) {
		super(img);

		this.costo = costo;
		this.indice = indice;
		
		if(possedute != null) {
			// imposto il label dell'angolino
			lblPossedute = new JLabel(possedute.toString());
			lblPossedute.setForeground(CostantiGui.COLORE_TESTO_ANGOLINI);
			lblPossedute.setFont(CostantiGui.FONT_ANGOLINI);
			lblPossedute.setHorizontalAlignment(SwingConstants.CENTER);
			lblPossedute.setVerticalAlignment(SwingConstants.CENTER);
			
			// imposto il panel dell'angolino
			angolino.setBounds(0, 0, CostantiGui.DIMENSIONE_ANGOLINI, CostantiGui.DIMENSIONE_ANGOLINI);
			angolino.setBackground(CostantiGui.COLORE_SFONDO_ANGOLINI);
			angolino.setLayout(new BorderLayout());
			angolino.add(lblPossedute, BorderLayout.CENTER);
			
			add(angolino);
		}
		
		// imposto il panel con il prezzo della tessera in cima di quel tipo
		panelCosto = new JPanel();
		panelCosto.setBounds(0, (CostantiGui.DIMENSIONE_PANEL_TESSERA.height - CostantiGui.DIMENSIONE_MONETA_TESSERA.height - 10),
				(CostantiGui.DIMENSIONE_MONETA_TESSERA.width +5) * CostantiModel.MAX_VALORE_TESSERA, CostantiGui.DIMENSIONE_MONETA_TESSERA.height); // TODO da sistemare
		panelCosto.setOpaque(false);
		panelCosto.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		disegnaCosto();

		setLayout(null);
		setBackground(CostantiGui.COLORE_ACQUA);
		add(panelCosto);
	}

	private void disegnaCosto() {
		for (int i = 0; i < costo; i++) {
			panelCosto.add((new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_MONETA_TESSERA.width, CostantiGui.DIMENSIONE_MONETA_TESSERA.height, Image.SCALE_SMOOTH)))));
		}
	}

	public void aggiorna() {
		aggiornaCosto();
		aggiornaPossedute();
	}

	private void aggiornaPossedute() {
		lblPossedute.setText(MainClient.getDatiPartita().getGiocatore(MainClient.getNome()).numeroTesserePerTipo().get(TipoTerritorio.valoriTessere()[indice]).toString());
		lblPossedute.repaint();
	}

	private void aggiornaCosto() {
		// prendo i dati della tessera in cima di questo tipo
		Tessera tess = MainClient.getDatiPartita().getTessereInCima()[indice];
		
		if (!tess.equals(Mazzo.TESSERA_FINITA)) {
			if(costo == tess.getCosto() - 1) {
				costo = tess.getCosto();
				panelCosto.add((new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png").getScaledInstance(CostantiGui.DIMENSIONE_MONETA_TESSERA.width, CostantiGui.DIMENSIONE_MONETA_TESSERA.height, Image.SCALE_SMOOTH)))));
			} else if(costo != tess.getCosto() - 1) {
				costo = tess.getCosto();
				panelCosto.removeAll();
				disegnaCosto();
			}
			panelCosto.repaint();
		}
		else {
			costo = 0;
			panelCosto.removeAll();
			disegnaCosto();
			setBorder(BorderFactory.createMatteBorder(
                    5, 5, 5, 5, CostantiGui.COLORE_TESSERA_FINITA));
		}
	}
	
	public Integer getIndice() {
		return indice;
	}
}
