import java.io.*;
import java.util.*;

public class InvertedIndex {
    private Map<String, Map<Integer, Integer>> invertedIndex;

    public InvertedIndex() {
        invertedIndex = new HashMap<>();
    }

    public void addDocument(int docID, List<String> words) {
        for (String word : words) {
            invertedIndex.putIfAbsent(word, new HashMap<>());
            Map<Integer, Integer> docFreq = invertedIndex.get(word);
            docFreq.put(docID, docFreq.getOrDefault(docID, 0) + 1);
        }
    }

    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, Map<Integer, Integer>> entry : invertedIndex.entrySet()) {
                writer.write(entry.getKey() + ": ");
                for (Map.Entry<Integer, Integer> docEntry : entry.getValue().entrySet()) {
                    writer.write(docEntry.getKey() + ": " + docEntry.getValue() + "; ");
                }
                writer.newLine();
            }
        }
    }

    public Map<Integer, Integer> search(String term) {
        return invertedIndex.getOrDefault(term, Collections.emptyMap());
    }
}