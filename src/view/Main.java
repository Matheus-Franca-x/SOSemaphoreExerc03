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

		for(int i = 1; i <= 2; i++)
		{
			for (int j = 1; j <= 7; j++)
			{
				carro[i-1][j-1] = new FormulaControll(j, i, perm, pausa);
				carro[i-1][j-1].corrida();
			}
		}
		
	}

}
