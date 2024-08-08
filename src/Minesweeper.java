import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Minesweeper {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public Minesweeper() throws IOException {

		String input;
		boolean validanswer = false;
		short width = 1, height = 1, mines = 1;
		short cell;
		
		bw.write("Press <Enter> to start...");
		bw.flush();
		input = br.readLine();
		fiveLines();
		if(!input.equals("skip")) {
			Tutorial tuto = new Tutorial();
			tuto.tutorial();
		}

		// difficulty setting
		bw.write("There are three difficulties, [Biginner, Intermediate, Expert]\n" + "Choose your difficulty: ");
		bw.flush();
		while (!validanswer) {
			input = br.readLine().toUpperCase();
			if (input.equals("BIGINNER") || input.equals("B")) {
				fiveLines();
				bw.write("\nChoosen difficulty is Biginner.\n" + "the board set to 9x9, 10mines.(12% mines)\n\n");
				width = 9;
				height = 9;
				mines = 10;
				validanswer = true;
			} else if (input.equals("INTERMEDIATE") || input.equals("I")) {
				fiveLines();
				bw.write("\nChoosen difficulty is Intermediate.\n" + "the board set to 16x16, 40mines.(16% mines)\n\n");
				width = 16;
				height = 16;
				mines = 40;
				validanswer = true;
			} else if (input.equals("EXPERT") || input.equals("E")) {
				fiveLines();
				bw.write("\nChoosen difficulty is Experts.\n" + "the board set to 30x16, 99mines.(21% mines)\n\n");
				width = 30;
				height = 16;
				mines = 99;
				validanswer = true;
			} else {
				bw.write("\nYou have put wrong input. Might have spelled wrong. \n" + "Try again : ");
				bw.flush();
			}
		}
		Board board = new Board(width, height, mines);
		validanswer = false;

		// set mines on board ■▢
		board.setMines();
		board.countMines();

		// game start
		bw.write("Press <Enter> to start...");
		bw.flush();
		br.readLine();
		boolean gameEnd = false;

		// {valid(0,1), x, y, flag(0,1)}
		// if invalid input => {0, 0, 0, 0}
		int[] inputArr = new int[] { 0, 0, 0, 0 };
		int gameover = 0;

		while (!gameEnd) {
			fiveLines();
			board.printBoard();

			while (true) {
				input = br.readLine().toUpperCase();
				inputArr = isValid(board, input);
				if (inputArr[0] == 0) {
					bw.write("Invalid input! try again!\n");
					bw.flush();
					continue;
				}

				cell = board.getCell(inputArr[1], inputArr[2]);

				if (cell >= 1 && cell <= 8) { // if chosen cell is not open
					board.addShot();
					board.makeCellOpen(inputArr[1], inputArr[2]);
					break;
				}

				else if (cell == 0) { // if chosen cell is 0
					board.open0cells(inputArr[1], inputArr[2]);
					break;
				} else if (cell >= 10 && cell <= 18) { // if chosen cell is already opened
					char temporaryCharacter = (char) (65 + inputArr[2]);
					String OpenCellError = "Your selected cell, " + temporaryCharacter + (inputArr[1] + 1)
							+ " is already open.\n";
					bw.write(OpenCellError);
					bw.write("Try other cell : ");
					bw.flush();
					continue;
				} else if (inputArr[3] == 1) { // if input was flag
					if (cell != -2) {
						board.setFlag(inputArr[1], inputArr[2]);
						break;
					}
					board.unFlag(inputArr[1], inputArr[2]);
					break;
				} else if (cell == -1) { // if chosen cell is mine
					board.mindFound(inputArr[1], inputArr[2]);
					gameEnd = true;
					break;
				} else if(cell == -2) {
					char temporaryCharacter = (char) (65 + inputArr[2]);
					String OpenCellError = "Your selected cell, " + temporaryCharacter + (inputArr[1] + 1)
							+ " is flagged.\n";
					bw.write(OpenCellError);
					bw.write("Try other cell : ");
					bw.flush();
					continue;
				}
			}
			
			gameover = board.getTotalCells() - board.getOpenCells() - board.getMines();
			if(gameover == 0)
				gameEnd = true;
			

		}
		
		if(gameover == 0) {
			bw.write("You win!");
		}else {
			board.showMines();
			bw.write("You lose!");
		}
		
		bw.flush();
		bw.close();
	}

	public static int[] isValid(Board board, String str) {
		int[] ans = new int[] { 0, 0, 0, 0 };

		StringTokenizer st = new StringTokenizer(str);
		if(!st.hasMoreTokens())
			return ans;
		String str1 = st.nextToken();

		// is input length valid
		int len = str1.length();
		if (len < 2 || len > 3)
			return ans;

		// is alphabet(y value) valid
		char C = str1.charAt(0);
		if (C < 'A' && C > 'A' + board.getHeight())
			return ans;

		// is number(x value) valid
		int N;
		try {
			N = Integer.parseInt(str1.substring(1));
			if (N < 1 || N > board.getWidth())
				return ans;
		} catch (NumberFormatException e) {
			return ans;
		}

		ans[0] = 1;
		ans[1] = N - 1;
		ans[2] = C - 65;
		if (!st.hasMoreTokens())
			return ans;

		if (st.nextToken().charAt(0) == 'F')
			ans[3] = 1;

		return ans;
	}

	public void fiveLines() throws IOException{
		bw.write("\n\n\n\n\n");
		bw.flush();
		return;
	}
}
