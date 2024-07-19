import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tutorial {

	public static void tutorial() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		bw.write("Input tutorial: \n");
		bw.write("Lower case or upper case of your input does not matter.\n");
		bw.write("You can use single character on any dicisions.\n");
		bw.write("ex) \'B\', \'b\', \"BiGinNeR\" => Biginner\n");
		bw.write("Press <Enter> to continue...");
		bw.flush();
		br.readLine();
		
		Board Tboard = new Board(9, 9, 10);
		Tboard.setMines();
		Tboard.countMines();
		Tboard.printBoard();
		
		
		bw.close();
		return;
	}
}
