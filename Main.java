import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Load stop words
        Set<String> stopWords = loadStopWords("./stopwordlist.txt");

        // Initialize Porter stemmer
        Porter stemmer = new Porter();

        // Initialize text parser
        TextParser parser = new TextParser(stopWords, stemmer);

        // Initialize indices
        ForwardIndex forwardIndex = new ForwardIndex();
        InvertedIndex invertedIndex = new InvertedIndex();

        // Read documents from the 'documents' folder and build indices
        File folder = new File("./documents");
        if (!folder.exists() || !folder.isDirectory()) {
            System.err.println("Folder ./documents does not exist or is not a directory.");
            return;
        }

        int docID = 1;
        for (File file : folder.listFiles()) {
            if (file.isFile()) {
                System.out.println("Processing file: " + file.getAbsolutePath());
                String content = new String(Files.readAllBytes(file.toPath()));
                List<String> words = parser.parse(parseStructuredContent(content));
                forwardIndex.addDocument(docID, words);
                invertedIndex.addDocument(docID, words);
                docID++;
            }
        }

        // Save indices to files
        forwardIndex.saveToFile("forward_index.txt");
        invertedIndex.saveToFile("inverted_index.txt");

        // Initialize retriever
        Retriever retriever = new Retriever(invertedIndex);

        // Example search
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a term to search: ");
        String term = scanner.nextLine();
        scanner.close();
        Map<Integer, Integer> results = retriever.retrieve(term);
        System.out.println("Documents containing the term '" + term + "': " + results);
    }

    private static Set<String> loadStopWords(String filePath) throws IOException {
        Set<String> stopWords = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                stopWords.add(line.trim());
            }
        }
        return stopWords;
    }

    private static String parseStructuredContent(String content) {
        // Simple parser to extract text between <TEXT> tags
        int start = content.indexOf("<TEXT>");
        int end = content.indexOf("</TEXT>");
        if (start != -1 && end != -1) {
            return content.substring(start + 6, end).trim();
        }
        return "";
    }
}