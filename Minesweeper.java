import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Minesweeper {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	Board Board = new Board();

	public Minesweeper() throws IOException {

		String input;
		boolean validanswer = false;
		int difficulty = 0;
		int width = 9;
		int height = 9;
		int mineN = 10;

		bw.write("Minesweeper \n" + "2024.07.19 \n" + "HarryHan \n" + "============ \n\n");

		// difficulty setting
		bw.write("There are three difficulties, [Biginner, Intermediate, Expert]\n" + "Choose your difficulty: ");
		bw.flush();
		while (!validanswer) {
			input = br.readLine().toUpperCase();
			if (input.equals("BIGINNER") || input.equals("B")) {
				bw.write("\nChoosen difficulty is Biginner.\n" + "the board set to 9x9, 10mines.(12% mines)\n\n");
				difficulty = 0;
				validanswer = true;
			} else if (input.equals("INTERMEDIATE") || input.equals("I")) {
				bw.write("\nChoosen difficulty is Intermediate.\n" + "the board set to 16x16, 40mines.(16% mines)\n\n");
				difficulty = 1;
				validanswer = true;
			} else if (input.equals("EXPERT") || input.equals("E")) {
				bw.write("\nChoosen difficulty is Experts.\n" + "the board set to 30x16, 99mines.(21% mines)\n\n");
				difficulty = 2;
				validanswer = true;
//			} else if() {
			}else{
				bw.write("\nYou have put wrong input. Might have spelled wrong. \n" + "Try again : ");
				bw.flush();
			}
		}
		validanswer = false;

		bw.write("Press <Enter> to start. \n\n");
		bw.flush();
		br.readLine();

		// set board
		// -1 : mine, 0 ~ 8 : mines nearby, 10 ~ 18 : open cell,
		// -2 : not open, -3 : flag
		if (difficulty == 0) {
			width = 9;
			height = 9;
			mineN = 10;
		} else if (difficulty == 1) {
			width = 16;
			height = 16;
			mineN = 40;
		} else {
			width = 30;
			height = 16;
			mineN = 99;
		}
		short[][] board = new short[height][width];

		// set mines on board ■▢
		Board.SetMines(board, width, height, mineN);
		Board.countMines(board, width, height);

		
//		boolean gameEnd = false;
//		while(!gameEnd) {
//			
//			
//			
//		}
		
		
		// test view
		Board.printBoard(board, width, height);
		

		bw.close();
	}

	public void Tutorial() {

	}

	
}
