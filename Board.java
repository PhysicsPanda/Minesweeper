import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Random;

public class Board {
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	Random rd = new Random();
	
	public void SetMines(short[][] board, int width, int height, int mines) {
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

	public void countMines(short[][] board, int width, int height) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if (board[i][j] != -1)
					board[i][j] = findmines(board, j, i);
			}
		}
	}

	public short findmines(short[][] board, int x, int y) {
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

	public void printBoard(short[][] board, int width, int height) throws IOException {
		bw.write("    ");
		for (int i = 1; i <= 9; i++)
			bw.write(" " + i + " ");
		for (int i = 10; i <= width; i++)
			bw.write(i + " ");

		bw.newLine();
		bw.newLine();
		for (short i = 0; i < height; i++) {
			bw.write(65 + i);
			bw.write("   ");

			for (short j = 0; j < width; j++) {
				if (board[i][j] == -1) {
					bw.write(" * ");
				} else {
					bw.write(" " + board[i][j] + " ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
	}

}
