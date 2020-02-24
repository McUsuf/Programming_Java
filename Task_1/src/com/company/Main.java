package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // write your code here
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
            String bufStr = null;
            //System.out.println(bufStr);
            Map<String, Integer> wordsMap = new HashMap<String, Integer>();

            while ((bufStr = reader.readLine()) != null) {
                String[] words = bufStr.split("[^a-zA-Z0-9]");

                for (String curW : words) {
                    //System.out.println(curW);
                    if (wordsMap.containsKey(curW)) {
                        wordsMap.put(curW, wordsMap.get(curW) + 1);
                    } else {
                        wordsMap.putIfAbsent(curW, 1);
                    }
                }
            }

            wordsMap.remove("");

            int countOfWords = 0;

            for (Map.Entry<String, Integer> curPair : wordsMap.entrySet()) countOfWords += curPair.getValue();

            List<Map.Entry<String, Integer>> toSort = new ArrayList<>();
            for (Map.Entry<String, Integer> e : wordsMap.entrySet()) {
                toSort.add(e);
            }
            toSort.sort(Map.Entry.<String, Integer>comparingByValue().reversed());

            FileWriter fosCSV = new FileWriter("words.csv");

            for (Map.Entry<String, Integer> e : toSort) {
                StringBuilder freq = new StringBuilder(Double.toString((double) e.getValue() / countOfWords * 100));
                freq.setLength(5);
                fosCSV.write(e.getKey() + ";" + e.getValue() + ";" + freq +"\n");
                fosCSV.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}