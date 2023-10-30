import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ScoreManager {

	private String filename;
	public int max_scores = Constant.FAME_MAX;
	public String[] names;
	public int[] scores;

	public ScoreManager(String filename) {
		this.filename = filename;

	}

	public void save_score(String playername, int score) {
		try {
			String filePath = Constant.BASE_URL + filename;
			Scanner scanner = new Scanner(new File(filePath));
			scores = new int[max_scores];
			names = new String[max_scores];
			int count = 0;
			boolean isscoreadded = false;

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] data = line.split(" ");
				if (data.length == 2 && count < max_scores) {
					scores[count] = Integer.parseInt(data[1]);
					names[count] = data[0];
					count++;

				}
			}

			for (int i = 0; i < count; i++) {
				if (score > scores[i]) {
					for (int j = count - 1; j > i; j--) {
						scores[j] = scores[j - 1];
						names[j] = names[j - 1];
					}
					scores[i] = score;
					names[i] = playername;
					isscoreadded = true;
					break;
				}

			}

			if (!isscoreadded && count < max_scores) {
				scores[count] = score;
				names[count] = playername;
				count++;
			}

			PrintWriter writer = new PrintWriter(new FileWriter(filePath));
			for (int i = 0; i < count; i++) {
				writer.println(names[i] + " " + scores[i]);
			}
			writer.close();

		} catch (IOException e) {
			System.err.println("FAiled to save the score: " + e.getMessage());
		}
	}

	public String getScorebyRanking(int ranking) {
		if (ranking < 1 || ranking > max_scores) {
			return "Invalid";
		} else {
			return ranking + ":" + names[ranking - 1] + " : " + scores[ranking - 1];
		}

	}

}
