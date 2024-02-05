package search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SearchNone extends SearchEngine{

    SearchNone(Map<String,ArrayList<Integer>> indexMap,int total){
        this.indexMap=indexMap;
        this.total=total;
    }

    @Override
    public Set<Integer> findData(String query){
        query=query.toLowerCase().trim();
        Set<Integer> indexSet=new HashSet<>();
        String[] words=query.split(" ");
        HashSet<Integer>excludedSet=new HashSet<>();
        for(String word:words){
            word=word.toLowerCase();
            if(indexMap.containsKey(word)){
                ArrayList<Integer> indexes=indexMap.get(word);
                excludedSet.addAll(indexes);
            }
        }
        for(int i=0;i<total;i++){
            if(!excludedSet.contains(i)) indexSet.add(i);
        }
        return indexSet;
    }

}
