import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tutorial {

	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public void tutorial() throws IOException {

		bw.write("Input tutorial: \n");
		bw.write("Lower case or upper case of your input does not matter.\n");
		bw.write("You can use single character on any dicisions.\n");
		bw.write("ex) \'B\', \'b\', \"BiGinNeR\" => Biginner\n\n");
		bw.write("Press <Enter> to continue...");
		bw.flush();
		br.readLine();
		newSession();

		final short width = 9;
		final short height = 9;
		final short mineN = 10;

		Board Tboard = new Board(width, height, mineN);
		Tboard.setTutorial();
		Tboard.countMines();
		Tboard.printBoard();

		// x, y
		int[] inputArr = new int[] { 0, 0 };

		bw.write("This is your Board.");
		bw.write("To open a cell, Type in coordinate of a cell.\n");
		bw.write("Let's open E5 cell.\n");
		bw.write("Type \"E5\"\n");
		bw.flush();
		dealInput("E5", inputArr);
		newSession();
		Tboard.open0cells(inputArr[0], inputArr[1]);
		Tboard.printBoard();

		bw.write("It is clear that B2 cell is a mine.\n");
		bw.write("Let's flag B2 cell.\n");
		bw.write("Type \"B2 F\"\n");
		bw.flush();
		dealInput("B2 F", inputArr);
		newSession();
		Tboard.setFlag(inputArr[0], inputArr[1]);
		Tboard.printBoard();

		bw.write("To Unflag a cell, do the same.\n");
		bw.write("Let's unflag B2 cell.\n");
		bw.write("Type \"B2 F\"\n");
		bw.flush();
		dealInput("B2 F", inputArr);
		newSession();
		Tboard.unFlag(inputArr[0], inputArr[1]);
		Tboard.printBoard();
		
		bw.write("The tutorial is done!\n");
		bw.write("Press <Enter> to continue...");
		bw.flush();
		br.readLine();
		newSession();
		
		return;
	}

	private void dealInput(String str, int[] inpArr) throws IOException {
		boolean valid = false;
		String input;
		while (!valid) {
			input = br.readLine().toUpperCase();
			if (!input.equals(str)) {
				bw.write("Try again. Type in \"" + str + "\".\n");
				bw.flush();
				continue;
			}
			inpArr[0] = str.charAt(1) - 49;
			inpArr[1] = str.charAt(0) - 65;
			valid = true;
		}
		return;
	}
	
	private void newSession() throws IOException {
		bw.write("\n\n\n\n\n");
		bw.flush();
	}
}
