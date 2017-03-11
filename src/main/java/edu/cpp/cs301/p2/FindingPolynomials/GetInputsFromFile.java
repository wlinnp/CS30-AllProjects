package edu.cpp.cs301.p2.FindingPolynomials;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wai Phyo
 *         CS-301  |  Proj-2
 *         Prof. A. Raheja
 */
public class GetInputsFromFile {
    private File inputFile;

    public GetInputsFromFile(File inputFile) {
        this.inputFile = inputFile;
        validateFile();
    }

    public GetInputsFromFile(String inputFileName) {
        this.inputFile = new File(inputFileName);
        validateFile();
    }

    private void validateFile() {
        if (!inputFile.exists() || inputFile.isDirectory()) {
            throw new IllegalArgumentException("Invalid file path.");
        }
    }

    public List<XYPair> getPolynomialPairsFromFile() {
        return getAllPairs(getLinesFromFile());
    }

    /**
     * Get all lines from the file and store them to list
     *
     * @return List of String. Each line is an entry
     */
    private List<String> getLinesFromFile() {
        List<String> lines = new ArrayList<String>();
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return lines;
    }

    /**
     * Convert raw data to formatted pairs
     * Expected format:
     * first line x values separated by space
     * second line f(x) values separated by space
     * <p>
     * If invalid format, throw exception
     * If one of them is not a number, throw exception
     * <p>
     * NOTE: will not access empty file
     *
     * @param List of lines
     * @return List of (x, f(x)) pairs
     */
    private List<XYPair> getAllPairs(List<String> lines) {
        List<XYPair> allPairs = new ArrayList<XYPair>();
        if (lines.size() != 2) {
            throw new IllegalArgumentException("Inalid file format");
        }
        String[] xValues = lines.get(0).split(Misc.SPACE);
        String[] yValues = lines.get(1).split(Misc.SPACE);
        if (xValues.length != yValues.length) {
            throw new IllegalArgumentException("x and f(x) values do not match");
        }
        for (int i = 0; i < xValues.length; i++) {
            if (!Misc.isNumber(xValues[i]) || !Misc.isNumber(yValues[i])) {
                System.out.println(xValues[i] + " " + yValues[i]);
                throw new IllegalArgumentException("Value is not a number");
            }
            allPairs.add(new XYPair(Double.parseDouble(xValues[i]), Double.parseDouble(yValues[i])));
        }
        return allPairs;
    }
}
