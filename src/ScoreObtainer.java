import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ScoreObtainer {
	
	public static void main(String[] args) {
		System.out.println(getBomberScore());
	}
	
	
	public static void incrementKnightScore() {
		try { 
			FileReader r1 = new FileReader("files/highscores.txt");
			@SuppressWarnings("resource")
			BufferedReader br1 = new BufferedReader(r1);
			
			String originalText = br1.readLine();
			String numberToBeIncremented = "";
			int firstPeriodAtChar = 0;
			for (int i = 0; i < originalText.length(); i++) {
				if (originalText.charAt(i) == 46) {
					firstPeriodAtChar = i;
					break;
				}
				else {
					numberToBeIncremented += originalText.charAt(i);
				}
			}
			
			String originalSubstring = originalText.substring(firstPeriodAtChar,
					                   originalText.length() - 1);
			int num = Integer.parseInt(numberToBeIncremented) + 1;
			String finalText = num + originalSubstring;
			
			
			FileWriter r2 = new FileWriter("files/highscores.txt");
			BufferedWriter br2 = new BufferedWriter(r2);
			
		    br2.write(finalText, 0, finalText.length());
		    br2.write('.');
		    br2.flush();
		    br2.close();
			
		}
		catch (IOException e) {
			System.out.println("Resulted in IOException.");
		}
	}
	
	public static void incrementRangerScore() {
		try { 
			FileReader r1 = new FileReader("files/highscores.txt");
			@SuppressWarnings("resource")
			BufferedReader br1 = new BufferedReader(r1);
			
			String originalText = br1.readLine();
			
			int numPeriods = 0;
			int placeOfFirstPeriod = 0;
			int placeOfSecondPeriod = 0;
			
			for (int i = 0; i < originalText.length(); i++) {
				if (originalText.charAt(i) == 46) {
					numPeriods++;
					if (numPeriods == 1) {
						placeOfFirstPeriod = i;
					}
					if (numPeriods == 2) {
						placeOfSecondPeriod = i;
					}
				}
			}
			
			String originalSubstring1 = originalText.substring(0, placeOfFirstPeriod + 1);
			String originalSubstring2 = originalText.substring(placeOfSecondPeriod,
					                    originalText.length() - 1);
			String numberToBeIncremented = originalText.substring(placeOfFirstPeriod + 1, 
					                       placeOfSecondPeriod); 
			
			int num = Integer.parseInt(numberToBeIncremented) + 1;
			String finalText = originalSubstring1 + num + originalSubstring2;
			FileWriter r2 = new FileWriter("files/highscores.txt");
			BufferedWriter br2 = new BufferedWriter(r2);
		    br2.write(finalText, 0, finalText.length());
		    br2.write('.');
		    br2.flush();
		    br2.close();
			
		}
		catch (IOException e) {
			System.out.println("Resulted in IOException.");
		}
	}
	
	public static void incrementBomberScore() {
		try { 
			FileReader r1 = new FileReader("files/highscores.txt");
			@SuppressWarnings("resource")
			BufferedReader br1 = new BufferedReader(r1);
			
			String originalText = br1.readLine();
			
			int numPeriods = 0;
			int placeOfSecondPeriod = 0;
			
			for (int i = 0; i < originalText.length(); i++) {
				if (originalText.charAt(i) == 46) {
					numPeriods++;
					if (numPeriods == 2) {
						placeOfSecondPeriod = i;
					}
				}
			}
			
			String originalSubstring = originalText.substring(0, placeOfSecondPeriod + 1);
			String numberToBeIncremented = originalText.substring(placeOfSecondPeriod + 1, 
					                       originalText.length() - 1); 
			
			int num = Integer.parseInt(numberToBeIncremented) + 1;
			String finalText = originalSubstring + num;
			FileWriter r2 = new FileWriter("files/highscores.txt");
			BufferedWriter br2 = new BufferedWriter(r2);
		    br2.write(finalText, 0, finalText.length());
		    br2.write('.');
		    br2.flush();
		    br2.close();
			
		}
		catch (IOException e) {
			System.out.println("Resulted in IOException.");
		
	}
	}
	
	public static int getKnightScore() {
		int[] scores = obtainUnsortedScores();
		return scores[0];
	}
	
	public static int getRangerScore() {
		int[] scores = obtainUnsortedScores();
		return scores[1];
	}
	
	public static int getBomberScore() {
		int[] scores = obtainUnsortedScores();
		return scores[2];
	}
	
	public static int[] sortArrayDescending(int[] unsortedScores) {
		int[] sortedScores = new int[3];
		int greatest = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
				if (unsortedScores[i] > greatest) {
					greatest = unsortedScores[i];
				}
			}
		int least = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
				if (unsortedScores[i] < least) {
					least = unsortedScores[i];
				}
			}
		sortedScores[0] = greatest;
		sortedScores[2] = least;
		int repeatsGreatest = 0;
		int repeatsLeast = 0;
		for (int i = 0; i < 3; i++) {
			if (unsortedScores[i] > least && unsortedScores[i] != greatest) {
				sortedScores[1] = unsortedScores[i];
					break;
					}
			if (unsortedScores[i] < greatest && unsortedScores[i] != least) {
				sortedScores[1] = unsortedScores[i];
					break;
			}
			if (unsortedScores[i] == greatest) {
				repeatsGreatest++;
				}
			if (unsortedScores[i] == least) {
				repeatsLeast++;
				}
			if (i == 2 && repeatsLeast == 2) {
				sortedScores[1] = least;
				break;
				}
			if (i == 2 && repeatsGreatest == 2) {
				sortedScores[1] = greatest;
				break;
				}
			if (i == 2) {
				sortedScores[1] = greatest;
			}	
		}
		return sortedScores;
	}
	
	public static int[] obtainUnsortedScores() {
		String[] scores = obtainScoresAsStrings();
		int[] unsortedScores = new int[3];
		for (int i = 0; i < 3; i++) {
			unsortedScores[i] = Integer.parseInt(scores[i]);
		}
		int greatest = Integer.MIN_VALUE;
		for (int i = 0; i < 3; i++) {
				if (unsortedScores[i] > greatest) {
					greatest = unsortedScores[i];
				}
			}
		int least = Integer.MAX_VALUE;
		for (int i = 0; i < 3; i++) {
				if (unsortedScores[i] < least) {
					least = unsortedScores[i];
				}
			}
		return unsortedScores;
	}

		
	public static String[] obtainScoresAsStrings() {
		String[] stringScores = new String[3];
		for (int i = 0; i < 3; i++) {
			stringScores[i] = "";
		}
		try { 
			FileReader r = new FileReader("files/highscores.txt");
			BufferedReader br = new BufferedReader(r);
			
			int periodCounter = 0;
			while (periodCounter < 3) {
				int currentChar = br.read();
				if (currentChar != 46) {
					stringScores[periodCounter] = stringScores[periodCounter] + (char) currentChar;
				}
				else {
					periodCounter++;
				}
		}
			br.close();
		}
		catch (IOException e) {
			System.out.println("Resulted in IOException.");
		}
		return stringScores;
	}
}
