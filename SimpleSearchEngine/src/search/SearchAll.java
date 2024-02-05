package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchAll extends SearchEngine{

    SearchAll(Map<String,ArrayList<Integer>> indexMap,int total){
        this.indexMap=indexMap;
        this.total=total;
    }

    @Override
    public Set<Integer> findData(String query){
        query=query.toLowerCase().trim();
        Set<Integer> indexSet=new HashSet<>();
        String[] words=query.split(" ");
        for(String word:words){
            word=word.toLowerCase().trim();
            if(indexMap.containsKey(word)){
                ArrayList<Integer> indexes=indexMap.get(word);
                if(indexSet.isEmpty()) indexSet.addAll(indexes);
                else indexSet.retainAll(indexes);
            }
        }
        return indexSet;
    }

}
