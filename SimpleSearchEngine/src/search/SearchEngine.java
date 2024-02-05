package search;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public abstract class SearchEngine {
    protected static final String SEPARATOR="";
    protected Map<String,ArrayList<Integer>> indexMap;
    protected int total=0;

    public abstract Set<Integer>findData(String query);
}
