package it.polimi.deib.provaFinale.cantiniDignani.view.gui;

import it.polimi.deib.provaFinale.cantiniDignani.utilita.Coppia;
import it.polimi.deib.provaFinale.cantiniDignani.utilita.GestoreCoda;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FinestraChiediNome extends JFrame {

	private static final long serialVersionUID = -2198792582824078626L;

	private JPanel contentPane;
	private JTextField txtNome;
	private JLabel lblInserisciIlNome;
	private JLabel lblIlNomeScelto;
	private JPasswordField passwordField;

	private GestoreCoda <Coppia<String, String>> coda = new GestoreCoda<Coppia<String, String>>();

	public FinestraChiediNome(boolean visualizzaMessaggioErrore) {
		super("Sheepland - Inserisci il nome");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 150, 400, 400);
		contentPane = new BackgroundPanel(Toolkit.getDefaultToolkit().getImage(CostantiGui.PERCORSO_IMMAGINI + "sfondoPastorePecore.png").getScaledInstance(400, 400, Image.SCALE_SMOOTH));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtNome = new JTextField(10);
		txtNome.setBounds(50, 130, 180, 30);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(50, 160, 180, 30);

		contentPane.setLayout(null);
		contentPane.add(txtNome);
		contentPane.add(passwordField);

		lblInserisciIlNome = new JLabel("<html>Inserisci il nome e clicca invia<br />per iniziare una nuova partita</html>");
		lblInserisciIlNome.setBounds(50, 94, 206, 38);
		contentPane.add(lblInserisciIlNome);

		lblIlNomeScelto = new JLabel("<html>Il nome scelto è già occupato,<br />scegliere un altro nome e riprovare!</html>");
		lblIlNomeScelto.setForeground(Color.RED);
		lblIlNomeScelto.setBounds(50, 61, 250, 38);
		if (visualizzaMessaggioErrore) {
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
				if(txtNome.getText().length() != 0 && passwordField.getPassword().toString().length() != 0) {
					coda.aggiungi(Coppia.creaCoppia(txtNome.getText(), passwordField.getPassword().toString()));
				}
			}
		});
		btnInvia.setBounds(113, 200, 117, 29);
		contentPane.add(btnInvia);
	}

	public Coppia<String, String> riceviNome() {
		setVisible(true);
		Coppia<String, String> temp = null;
		temp = coda.aspetta();
		dispose();
		return temp;
	}
}
