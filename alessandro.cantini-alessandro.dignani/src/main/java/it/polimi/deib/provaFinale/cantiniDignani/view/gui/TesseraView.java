package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.model.Costanti;

import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TesseraView extends BackgroundPanel {

	private static final long serialVersionUID = 8284029875922825876L;

	JPanel angolino = new JPanel();
	JLabel lblPossedute;
	JPanel panelCosto;
	JLabel moneta;

	protected TesseraView(Image img, Integer costo, Integer possedute) {
		super(img);

		// imposto il label dell'angolino
		lblPossedute = new JLabel(possedute.toString());
		lblPossedute.setForeground(CostantiGui.COLORE_TESTO_ANGOLINI);
		lblPossedute.setFont(CostantiGui.FONT_ANGOLINI);

		// imposto il panel dell'angolino
		angolino.setBounds(0, 0, CostantiGui.DIMENSIONE_ANGOLINI, CostantiGui.DIMENSIONE_ANGOLINI);
		angolino.setBackground(CostantiGui.COLORE_SFONDO_ANGOLINI);
		angolino.add(lblPossedute);

//		moneta = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png")
//				.getScaledInstance(CostantiGui.DIMENSIONE_MONETA_TESSERA.width, CostantiGui.DIMENSIONE_MONETA_TESSERA.height, Image.SCALE_SMOOTH)));

		panelCosto = new JPanel();
		panelCosto.setBounds(0, (CostantiGui.DIMENSIONE_PANEL_TESSERA.height - CostantiGui.DIMENSIONE_MONETA_TESSERA.height - 10),
				(CostantiGui.DIMENSIONE_MONETA_TESSERA.width +5) * Costanti.MAX_VALORE_TESSERA, CostantiGui.DIMENSIONE_MONETA_TESSERA.height); // TODO da sistemare
		panelCosto.setOpaque(false);
		panelCosto.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
		for (int i = 0; i < costo; i++) {
			panelCosto.add((new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "soldi.png")
				.getScaledInstance(CostantiGui.DIMENSIONE_MONETA_TESSERA.width, CostantiGui.DIMENSIONE_MONETA_TESSERA.height, Image.SCALE_SMOOTH)))));
		}

		setLayout(null);
		setBackground(CostantiGui.COLORE_ACQUA);
		add(angolino);
		add(panelCosto);
	}
}
