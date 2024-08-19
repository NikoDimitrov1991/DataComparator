import java.io.*;
import java.util.HashSet;
import java.util.Set;


public class DataComparator {

    public static void main(String[] args) {

        compareDataAndPrintAndWriteResults("C:\\Users\\nikolay.dimitrov\\Desktop\\DataComarator\\", "dataA.txt", "dataB.txt", "results.txt");
    }

    private static void compareDataAndPrintAndWriteResults(String filePath, String fileAName, String fileBName, String resultFileName) {

        final String resultFilePath = filePath + resultFileName;

        final Set<String> dataA = loadDataFromFile(filePath + fileAName);
        final Set<String> dataB = loadDataFromFile(filePath + fileBName);

        final Set<String> uniqueInA = new HashSet<>(dataA);
        uniqueInA.removeAll(dataB);


        final Set<String> uniqueInB = new HashSet<>(dataB);
        uniqueInB.removeAll(dataA);

        final Set<String> commonInAAndB = new HashSet<>(dataA);
        commonInAAndB.retainAll(dataB);

        displayInTerminalAndWriteInFile(resultFilePath, uniqueInA, uniqueInB, commonInAAndB);

    }

    private static void displayInTerminalAndWriteInFile(String resultFilePath, Set<String> uniqueInA, Set<String> uniqueInB, Set<String> commonInAAndB) {
        try (FileWriter resultsWriter = new FileWriter(resultFilePath)) {

            resultsWriter.write("Data contained only in A:\n");
            System.out.println("Data contained only in A:");
            for (String record : uniqueInA) {
                System.out.println(record);
                resultsWriter.write(record + "\n");
            }

            resultsWriter.write("\nData contained only in B:\n");
            System.out.println("\nData contained only in B:");
            for (String record : uniqueInB) {
                System.out.println(record);
                resultsWriter.write(record + "\n");
            }

            resultsWriter.write("\nData contained in both A and B:\n");
            System.out.println("\nData contained in both A and B:");
            for (String record : commonInAAndB) {
                System.out.println(record);
                resultsWriter.write(record + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error writing to results file: " + resultFilePath);
            e.printStackTrace();
        }
    }


    private static Set<String> loadDataFromFile(String filePath) {
        Set<String> data = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + filePath);
            e.printStackTrace();
        }
        return data;
    }
}