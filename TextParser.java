import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TextParser {
    private Set<String> stopWords;
    private Porter stemmer;

    public TextParser(Set<String> stopWords, Porter stemmer) {
        this.stopWords = stopWords;
        this.stemmer = stemmer;
    }

    public List<String> parse(String text) {
        List<String> words = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text.toLowerCase());

        while (matcher.find()) {
            String word = matcher.group();
            if (!stopWords.contains(word)) {
                words.add(stemmer.stripAffixes(word));
            }
        }
        return words;
    }
}