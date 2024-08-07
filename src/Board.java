import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Random;

public class Board {
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	Random rd = new Random();

	private short width;
	private short height;
	private short mines;
	private short[][] board;
	private short shot;
	private short openCells;
	private short totalCells;

	public Board(short width, short height, short mines) {
		this.width = width;
		this.height = height;
		this.mines = mines;
		this.board = new short[height][width];
		this.shot = 0;
		this.openCells = 0;
		this.totalCells = (short) (height * width);
	}

	// Tutorial board
	public void setTutorial() {
		final short[] TUTORIAL_MINES = new short[] { 8, 10, 23, 35, 51, 55, 62, 67, 68, 74 };

		for (short i : TUTORIAL_MINES)
			board[i / width][i % width] = -1;

		return;

	}

	// place mines
	public void setMines() {
		short boardSize = (short) (width * height);
		HashMap<Short, Boolean> mineCoordinates = new HashMap<Short, Boolean>();
		int generatedMine = 0;

		short random;
		while (generatedMine != mines) {
			random = (short) rd.nextInt(boardSize);
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
		return;
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
		for (int i = 0; i < width * 3; i++)
			bw.write("─"); // _ ─ -
		bw.newLine();

		// board display
		for (short i = 0; i < height; i++) {

			// alphabet, left vertical line
			bw.write(65 + i);
			bw.write("  |");

			// ■ □ ▓ ▣ ■
			// main display
			// -1 : mine, 0 ~ 8 : mines nearby, 10 ~ 18 : open cell,
			// -2 : flag
			for (short j = 0; j < width; j++) {
				if ((board[i][j] >= 0 && board[i][j] <= 8))// || board[i][j] == -1)
					bw.write("[] ");
				else if (board[i][j] == 10)
					bw.write("   ");
				else if (board[i][j] >= 11 && board[i][j] <= 18)
					bw.write(" " + board[i][j] + " ");
				else if (board[i][j] == -2)
					bw.write(" F ");
				else
					bw.write(" M ");
			}

			// right vertical line
			bw.write("|\n");
		}

		// lower horizontal line
		bw.write("    ");
		for (int i = 0; i < width * 3; i++)
			bw.write("─");
		bw.write("\n\n");
		bw.flush();
		return;
	}

	public short getHeight() {
		return height;
	}

	public short getWidth() {
		return width;
	}

	public short getCell(int x, int y) {
		return board[y][x];
	}

	public void makeCellOpen(int x, int y) {
		board[y][x] = (short) (board[y][x] + 10);
		shot++;
		return;
	}
	
	public void setFlag(int x, int y) {
		board[y][x] = -2;
		return;
	}
	
	public void unFlag(int x, int y) {
		board[y][x] = findmines(x, y);
		return;
	}

}
