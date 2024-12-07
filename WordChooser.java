import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;

public class WordChooser {
    private Random ran;
    private LinkedHashMap<String, String> wordsMap;

    public WordChooser() {
        ran = new Random();
        wordsMap = new LinkedHashMap<>();
    }

    public void loadWords(ArrayList<String> words) {
        for (String word : words) {
            String[] parts = word.split("\t");
            if (parts.length == 2) {
                String wordText = parts[0].trim();
                String type = parts[1].trim();
                wordsMap.put(wordText, type);
            }
        }
        printWords();
    }

    public String chooseNoun() {
        return chooseRandomWordByType("n");
    }

    public String chooseVerb() {
        return chooseRandomWordByType("v");
    }

    public String chooseAdjective() {
        return chooseRandomWordByType("a");
    }

    private String chooseRandomWordByType(String type) {
        ArrayList<String> filteredWords = new ArrayList<>();
        
        for (String word : wordsMap.keySet()) {
            if (wordsMap.get(word).equals(type)) {
                filteredWords.add(word);
            }
        }

        if (filteredWords.isEmpty()) {
            return null;
        }
        int index = ran.nextInt(filteredWords.size());
        return filteredWords.get(index);
    }
    public void printWords() {
        for (String type : wordsMap.keySet()) {
            System.out.println(type + ": " + wordsMap.get(type));
        }
    }
}