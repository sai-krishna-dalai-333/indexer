import java.io.*;
import java.util.*;

public class ForwardIndex {
    private Map<Integer, Map<String, Integer>> forwardIndex;

    public ForwardIndex() {
        forwardIndex = new HashMap<>();
    }

    public void addDocument(int docID, List<String> words) {
        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }
        forwardIndex.put(docID, wordFreq);
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<Integer, Map<String, Integer>> entry : forwardIndex.entrySet()) {
                writer.write(entry.getKey() + ": ");
                for (Map.Entry<String, Integer> wordEntry : entry.getValue().entrySet()) {
                    writer.write(wordEntry.getKey() + ": " + wordEntry.getValue() + "; ");
                }
                writer.newLine();
            }
        }
    }
}