import java.util.Map;

public class Retriever {
    private InvertedIndex invertedIndex;

    public Retriever(InvertedIndex invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    // Method to retrieve information based on a query term
    public Map<Integer, Integer> retrieve(String term) {
        return invertedIndex.search(term);
    }
}