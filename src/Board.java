import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Random;

public class Board {
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	Random rd = new Random();

	private int width;
	private int height;
	private int mines;
	private short[][] board;

	public Board(int width, int height, int mines) {
		this.width = width;
		this.height = height;
		this.mines = mines;
		this.board = new short[height][width];
	}

	// place mines
	public void setMines() {
		int boardSize = width * height;
		HashMap<Integer, Boolean> mineCoordinates = new HashMap<Integer, Boolean>();
		int generatedMine = 0;

		int random;
		while (generatedMine != mines) {
			random = rd.nextInt(boardSize);
			if (mineCoordinates.get(random) == null) {
				mineCoordinates.put(random, true);
				generatedMine++;
			}
		}

		for (int i : mineCoordinates.keySet())
			board[i / width][i % width] = -1;

		return;
	}

	// mark all cells by mines nearby
	public void countMines() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board[i][j] != -1)
					board[i][j] = findmines(j, i);
			}
		}
	}

	// find how many mines are near a cell
	private short findmines(int x, int y) {
		final int[][] var = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 }, { 1, 1 } };
		short count = 0;

		for (int[] i : var) {
			try {
				if (board[y + i[1]][x + i[0]] == -1)
					count++;
			} catch (ArrayIndexOutOfBoundsException e) {
				continue;
			}
		}
		return count;
	}

	// print out board
	public void printBoard() throws IOException {
		bw.write("    ");
		for (int i = 1; i <= 9; i++)
			bw.write(" " + i + " ");
		for (int i = 10; i <= width; i++)
			bw.write(i + " ");
		
		// upper horizontal line
		bw.newLine();
		bw.write("    ");
		for(int i=0;i<width*3;i++)
			bw.write("─"); // _ ─
		bw.newLine();
		
		// board display
		for (short i = 0; i < height; i++) {
			
			// alphabet, left vertical line
			bw.write(65 + i);
			bw.write("  |");

			// ■ □ ▓ ▣ ■
			// main display
			for (short j = 0; j < width; j++) {
				if (board[i][j] == -1) {
					bw.write(" M ");
				} else if (board[i][j] == 0) {
					bw.write("   ");
				} else {
					bw.write(" " + board[i][j] + " ");
				}
			}
			
			// right vertical line
			bw.write("|\n");
		}
		
		// lower horizontal line
		bw.write("    ");
		for(int i=0;i<width*3;i++)
			bw.write("─");
		bw.flush();
	}

}
