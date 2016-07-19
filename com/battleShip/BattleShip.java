package com.battleShip;

import java.util.Random;
import java.util.Scanner;

public class BattleShip {
	protected char board[][] = new char[5][5];
	protected int battleShipPositionRow;
	protected int battleShipPositionColumn;
	
	BattleShip(){
		int counter = 0, counter1 = 0;
		for(counter = 0; counter < 5; counter++){
			for(counter1 = 0; counter1 < 5; counter1++){
				this.board[counter][counter1] = '*';
			}
		}
	}

	public void setBattleShipPosition(int row, int column) {
		this.battleShipPositionRow = row;
		this.battleShipPositionColumn = column;

	}

	public void getBattleShipPosition() {
		System.out.print(this.battleShipPositionRow);
		System.out.print(",");
		System.out.print(this.battleShipPositionColumn + "\n");
	}
	
	public boolean attackBattleShip(BattleShip battleShip, int attackedPositionRow, 
			int attackedPositionColumn){
		
		if(attackedPositionRow == this.battleShipPositionRow 
				&& attackedPositionColumn == this.battleShipPositionColumn){
			battleShip.board[attackedPositionRow][attackedPositionColumn] = '0';
			return true;
		}
		else {
			battleShip.board[attackedPositionRow][attackedPositionColumn] = '^';
		}
		return false;
		
	}

	public void printBoard(BattleShip battleShip) {
		
		for (int counter = 0; counter < 5; counter++) {
			System.out.print("| ");
			for (int counter1 = 0; counter1 < 5; counter1++) {
				System.out.print(battleShip.board[counter][counter1] + " | ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int chances = 3; // Integer.parseInt(args[0]);
		int counter = 0;
		BattleShip battleShip = new BattleShip();
		battleShip.setBattleShipPosition(new Random().nextInt(5), new Random().nextInt(5));
		//battleShip.getBattleShipPosition();
		int attackedPositionRow;
		int attackedPositionColumn;
		int triesCounter = 1;
		boolean isSuccessfullyBombed = false;
		Scanner scanner = new Scanner(System.in);
		battleShip.printBoard(battleShip);
		do {
			System.out.println("Chances to find the battle ship remaining = " + (chances - counter));
			System.out.println("Enter the positions to attack (row,col): ");
			attackedPositionRow = scanner.nextInt();
			attackedPositionColumn= scanner.nextInt();
			while (attackedPositionRow > 4 || attackedPositionColumn > 4) {
				if (triesCounter <= 3) {
					System.out.println(
							"Out of board cell selected to attack. Bounds is 0 - 4. Enter the positions within the cell to attack?");
					attackedPositionRow = scanner.nextInt();
					attackedPositionColumn = scanner.nextInt();
					triesCounter++;
				} else {
					System.out.println("You have exhausted all your, out of bound tries. So long, Sailor!");
					Runtime.getRuntime().exit(0);
				}
			}
			
			isSuccessfullyBombed = battleShip.attackBattleShip(battleShip, attackedPositionRow, 
										attackedPositionColumn);
			
			if (isSuccessfullyBombed) {
				System.out.println("You sank the Battle Ship!");
				battleShip.printBoard(battleShip);
				break;
			}
			battleShip.printBoard(battleShip);
			counter++;

		} while (counter < chances);
		if(counter >= chances){
			System.out.println("You need to work on your aim. So long, Sailor!");
		}
		scanner.close();
	}
}
