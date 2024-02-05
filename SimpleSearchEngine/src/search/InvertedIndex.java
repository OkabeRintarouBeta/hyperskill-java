package search;

import java.lang.reflect.Array;
import java.util.*;

public class InvertedIndex {
    private static Map<String,ArrayList<Integer>>indexMap;

    InvertedIndex(List<People> peopleList) {
        indexMap = new HashMap<>();

        for (int i = 0; i < peopleList.size(); i++) {
            String[] person = peopleList.get(i).getItems();
            for (String word : person) {
                word = word.toLowerCase().trim();
                if (indexMap.containsKey(word)) {
                    if (!indexMap.get(word).contains(i)) {
                        indexMap.get(word).add(i);
                    }
                } else {
                    ArrayList<Integer> newList = new ArrayList<>();
                    newList.add(i);
                    indexMap.put(word, newList);
                }
            }
        }
    }
    Map<String,ArrayList<Integer>> getIndexMap(){
        return indexMap;
    }
}
