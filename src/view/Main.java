package view;

import java.util.concurrent.Semaphore;

import controller.FormulaControll;

public class Main 
{
	public static void main(String[] args)
	{
		
		Semaphore perm = new Semaphore(5);
		Semaphore pausa = new Semaphore(0);
		
		FormulaControll carro[][] = new FormulaControll[2][7];
		FormulaControll carroVet[] = new FormulaControll[14];
		FormulaControll aux = new FormulaControll(0, 0, null, null);

		for(int i = 1; i <= 2; i++)
		{
			for (int j = 1; j <= 7; j++)
			{
				carro[i-1][j-1] = new FormulaControll(j, i, perm, pausa);
				carro[i-1][j-1].corrida();
			}
		}
		
		try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 7; i++)
		{
				carroVet[i] = carro[0][i];
				carroVet[i+7] = carro[1][i];
		}
		
		for (int i = 0; i < 14; i++)
		{
			for (int j = 0; j < 14; j++)
			{
				if (carroVet[i].bestTime < carroVet[j].bestTime)
				{
					aux = carroVet[i];
					carroVet[i] = carroVet[j];
					carroVet[j] = aux;
				}
			}
		}
		
		for (int i = 0; i < 14; i++)
		{
			carroVet[i].classificacao(i+1);
		}
		
	}

}
