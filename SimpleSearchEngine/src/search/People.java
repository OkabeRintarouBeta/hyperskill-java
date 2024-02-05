package search;

public class People {
    private String[] info;

    People(String[] info){
        this.info=info;
    }

    public String[] getItems(){
        return this.info;
    }

    public String toString(){
        StringBuilder ret= new StringBuilder();
        for(String i:info){
            ret.append(i);
            ret.append(" ");
        }
        return ret.toString().trim();
    }
}
