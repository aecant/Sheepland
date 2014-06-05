package it.polimi.deib.provaFinale.cantiniDignani.controller;

public class TimerPartita extends Thread {
	private final int MILLESIMI = Costanti.SECONDI_TIMER * 1000;
	private boolean on = false;	
	private long inizio;
	
	
	@Override
	public void run() {
		while(true) {
			if(on) {
				if(System.currentTimeMillis() - inizio >= MILLESIMI) {
					ServerMain.iniziaPartita();
				}
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	 synchronized public void inizia() {
		inizio = System.currentTimeMillis();	
		on = true;
	}
	
	synchronized public void ferma() {
		on = false;
	}
	
}
