package controller;

import java.util.concurrent.Semaphore;

public class FormulaControll 
{
	
	private int carroId = 0;
	private int equipeId;
	private Semaphore semaforo;
	private Semaphore pausa;
	
	public FormulaControll(int id, int equipe, Semaphore semaforo, Semaphore pausa)
	{
		this.carroId = id;
		this.equipeId = equipe;
		this.semaforo = semaforo;
		this.pausa = pausa;
	}
	
	public void corrida()
	{
		
		int equipe = this.equipeId;
		new Thread()
		{
			public void run()
			{
				
				try {
					if(equipe == 2)
					{
						pausa.acquire();
					}
					semaforo.acquire();
					iniCorrida();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
					try {
						Thread.sleep(2000);
						pausa.release();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}.start();
	}
	
	

	
	public void iniCorrida()
	{
		System.out.println(this.carroId + " " + this.equipeId);
	}
	
	
	
	
	
	
}
