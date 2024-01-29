
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tailStr = "";
		if (str.length() == 1) {
			return tailStr;
		} else{
			tailStr = str.substring(1);
			return tailStr;
		}
	}

	public static int levenshtein(String word1, String word2) {
		//pre-processing
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		
		if (word2.length() == 0) {
			return word1.length();
		}
		if (word1.length() == 0) {
			return word2.length();
		}
		if (word1.charAt(0) == word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}
		return 1 + (Math.min(levenshtein(tail(word1), word2), Math.min(levenshtein(word1, tail(word2)), levenshtein(tail(word1), tail(word2)))));
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		int index = 0;
		while (!in.isEmpty()) {
			dictionary[index++] = in.readLine(); 
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String mostSimilar = "";
		int minDistance = word.length();
		for(int i = 0; i < dictionary.length; i++){
			int tempDistance = levenshtein(word, dictionary[i]);
			if (tempDistance < minDistance) {
				minDistance = tempDistance;
				mostSimilar = dictionary[i];
			}
		}
		if (minDistance > threshold) {
			return word;
		}else{
			return mostSimilar;
		}
	}

}
