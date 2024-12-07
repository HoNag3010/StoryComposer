import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

/**
 * This class is for choosing random words
 */
public class WordChooser {
    private Random ran;
    private LinkedHashMap<String, String> wordsMap;

    public WordChooser() {
        ran = new Random();
        wordsMap = new LinkedHashMap<>();
    }

    /**
     * Loading words from opened file to LinkedHashMap
     * @param words is an ArrayList loaded
     */
    public void loadWords(ArrayList<String> words) {
        for (String word : words) {
            String[] parts = word.split("\t");
            if (parts.length == 2) {
                String wordText = parts[0].trim();
                String type = parts[1].trim();
                wordsMap.put(wordText, type);
            }
        }
    }

    public String chooseNoun() {
        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : wordsMap.keySet()) {
            if (wordsMap.get(word).equals("n")) {
                filteredWords.add(word);
            }
        }
        return chooseRandomWordByType(filteredWords);
    }

    public String chooseVerb() {
        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : wordsMap.keySet()) {
            if (wordsMap.get(word).equals("v")) {
                filteredWords.add(word);
            }
        }
        return chooseRandomWordByType(filteredWords);
    }

    public String chooseAdjective() {
        ArrayList<String> filteredWords = new ArrayList<>();
        for (String word : wordsMap.keySet()) {
            if (wordsMap.get(word).equals("a")) {
                filteredWords.add(word);
            }
        }
        return chooseRandomWordByType(filteredWords);
    }

    /**
     * This makes word chosen randomly
     * @param filteredWords is words that got splitted by tab
     * @return the random word
     */
    private String chooseRandomWordByType(ArrayList<String> filteredWords) {
        if (filteredWords.isEmpty()) {
            return null;
        }
        int index = ran.nextInt(filteredWords.size());
        return filteredWords.get(index);
    }
}