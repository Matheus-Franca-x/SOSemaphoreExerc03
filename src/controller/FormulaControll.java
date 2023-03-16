package controller;

import java.text.DecimalFormat;
import java.util.concurrent.Semaphore;

public class FormulaControll
{
	
	private int carroId = 0;
	private int equipeId;
	private Semaphore semaforo;
	private Semaphore pausa;
	private int voltas = 0;
	public double bestTime = 1000;
	
	public FormulaControll(int equipe, int id, Semaphore semaforo, Semaphore pausa)
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
		DecimalFormat formatacao = new DecimalFormat("0.00");
		float tempoI = 0;
		float tempoF = 0;
		while (this.voltas < 3)
		{
			tempoI = System.nanoTime();
			try {
				Thread.sleep((int) (Math.random() * 1001) + 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			tempoF = (float) ((System.nanoTime() - tempoI) / Math.pow(10, 9));
			this.voltas++;
			System.out.println("O carro da quipe " + this.equipeId + " numero " + this.carroId + " correu na " + this.voltas + "° por " + formatacao.format(tempoF) + "s");
			if (this.bestTime > tempoF)
			{
				this.bestTime = tempoF;
			}
		}
	}
	
	public void classificacao(int i)
	{
		DecimalFormat formatacao = new DecimalFormat("0.00");
		System.out.println(i + "° A equipe " + this.equipeId + " numero " + this.carroId + " teve o tempo " + formatacao.format(this.bestTime));
	}
}
