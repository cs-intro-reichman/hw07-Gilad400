

public class HashTagTokenizer {

	public static void main(String[] args) {

		String hashTag = args[0];
		String []dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag, dictionary);
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

	public static boolean existInDictionary(String word, String[] dictionary) {
		int index = 0;
		while (index < dictionary.length) {
			if (word.equals(dictionary[index++])) {
				return true;
			}
		}
		return false;
	}

	public static void breakHashTag(String hashtag, String[] dictionary) {
		//pre-processing
		hashtag = hashtag.toLowerCase();
		// Base case: do nothing (return) if hashtag is an empty string.
        if (hashtag.isEmpty()) {
            return;
        }

        int N = hashtag.length();

        for (int i = 1; i <= N; i++) {
			String temp = hashtag.substring(0, i);
			if(existInDictionary(temp, dictionary)){
				System.out.println(temp);
				hashtag = hashtag.substring(i);
				breakHashTag(hashtag, dictionary);
				return;
				
			}
        }

		
    }

}
