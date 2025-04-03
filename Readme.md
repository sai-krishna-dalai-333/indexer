# Java Indexing Program

This Java program generates two output files: `forward_index.txt` and `inverted_index.txt`. The program starts with `Main.java`.

1. **Navigate to the directory containing `Main.java`:**

    ```sh
    cd Indexer
    ```

2. **Compile the Java program:**

    ```sh
    javac Main.java
    ```

    This command compiles the `Main.java` file and generates the necessary `.class` files.


    3. **Run the Java program:**
    ```sh
    java Main
    ```

    This command runs the compiled Java program. Upon execution, the program will generate two output files: `forward_index.txt` and `inverted_index.txt`.

    4. **Search for a term:**

    After running the program, you will be prompted to enter a term to search. For example:

    ```plaintext
    Enter a term to search: moment
    Documents containing the term 'moment': {3=1, 11=2}
    ```

    This output indicates the documents containing the term 'moment' along with their respective frequencies.


## Output Files

- **forward_index.txt:** Contains the forward index data.
- **inverted_index.txt:** Contains the inverted index data.