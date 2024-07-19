import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Minesweeper {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public Minesweeper() throws IOException {

		String input;
		boolean validanswer = false;
		int width=1, height=1, mines=1;
		
//		Tutorial.tutorial();

		// difficulty setting
		bw.write("There are three difficulties, [Biginner, Intermediate, Expert]\n" + "Choose your difficulty: ");
		bw.flush();
		while (!validanswer) {
			input = br.readLine().toUpperCase();
			if (input.equals("BIGINNER") || input.equals("B")) {
				bw.write("\nChoosen difficulty is Biginner.\n" + "the board set to 9x9, 10mines.(12% mines)\n\n");
				width = 9;
				height = 9;
				mines = 10;
				validanswer = true;
			} else if (input.equals("INTERMEDIATE") || input.equals("I")) {
				bw.write("\nChoosen difficulty is Intermediate.\n" + "the board set to 16x16, 40mines.(16% mines)\n\n");
				width = 16;
				height = 16;
				mines = 40;
				validanswer = true;
			} else if (input.equals("EXPERT") || input.equals("E")) {
				bw.write("\nChoosen difficulty is Experts.\n" + "the board set to 30x16, 99mines.(21% mines)\n\n");
				width = 30;
				height = 16;
				mines = 99;
				validanswer = true;
			}else{
				bw.write("\nYou have put wrong input. Might have spelled wrong. \n" + "Try again : ");
				bw.flush();
			}
		}
		Board board = new Board(width, height, mines);
		validanswer = false;

		bw.write("Press <Enter> to start. \n\n");
		bw.flush();
		br.readLine();

		// set board
		// -1 : mine, 0 ~ 8 : mines nearby, 10 ~ 18 : open cell,
		// -2 : not open, -3 : flag

		// set mines on board ■▢
		board.setMines();
		board.countMines();

		
//		boolean gameEnd = false;
//		while(!gameEnd) {
//			
//			
//			
//		}
		
		
		// test view
		board.printBoard();
		

		bw.close();
	}

	
}
