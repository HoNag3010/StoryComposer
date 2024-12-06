import java.util.ArrayList;
import java.util.Random;

public class WordChooser {
    private Random ran;
    public WordChooser() {
        ran = new Random();
    }
    public String chooseWordFromList(ArrayList<String> words) {
        int index = ran.nextInt(words.size());
        return words.get(index);
    }
}
