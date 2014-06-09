package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.view.gui.CostantiGui;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FinestraChiediNome extends JFrame {

	private static final long serialVersionUID = -2198792582824078626L;
	private static int numTentativi = 0;

	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblInserisciIlNome;
	private JLabel lblIlNomeScelto;

	private BlockingQueue<String> coda = new LinkedBlockingQueue<String>();
	Gui gui;

	public FinestraChiediNome(final Gui gui) {
		super("Sheepland - Inserisci il nome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 400, 400);
		contentPane = new BackgroundPanel(Toolkit.getDefaultToolkit().getImage(CostantiGui.percorsoImmagini + "sfondoPastorePecore.png").getScaledInstance(400, 400, Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtNome = new JTextField(10);
		txtNome.setBounds(50, 130, 180, 30);

		contentPane.setLayout(null);
		contentPane.add(txtNome);

		lblInserisciIlNome = new JLabel("<html>Inserisci il nome e clicca invia<br />per iniziare una nuova partita</html>");
		lblInserisciIlNome.setBounds(50, 94, 206, 38);
		contentPane.add(lblInserisciIlNome);

		lblIlNomeScelto = new JLabel("<html>Il nome scelto è già occupato,<br />scegliere un altro nome e riprovare!</html>");
		lblIlNomeScelto.setForeground(Color.RED);
		lblIlNomeScelto.setBounds(50, 61, 250, 38);
		if (numTentativi >= 1) {
			lblIlNomeScelto.setVisible(true);
		} else {
			lblIlNomeScelto.setVisible(false);
		}
		contentPane.add(lblIlNomeScelto);

		JButton btnInvia = new JButton("Connetti");
		btnInvia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Controllo che il campo testo non sia vuoto (sarebbe meglio aggiungere altri controlli)
				if(txtNome.getText().length() != 0) {
					coda.add(txtNome.getText());
				}
			}
		});
		btnInvia.setBounds(113, 159, 117, 29);
		contentPane.add(btnInvia);
	}

	public String riceviNome() {
		numTentativi++;
		setVisible(true);
		String temp = null;
		try {
			temp = coda.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		dispose();
		return temp;
	}
}
