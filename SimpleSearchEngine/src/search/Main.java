package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        if(args.length<2){
            throw new IllegalArgumentException("At least two arguments are required.");
        }
        String pathToFile=args[1];

        File file=new File(pathToFile);
        List<People> peopleList=new ArrayList<>();

        try(Scanner scanner=new Scanner(file)){
            while(scanner.hasNext()){
                String info=scanner.nextLine().trim();
                if(info.isEmpty()) continue;
                String[] info_list=info.split(" ");
                peopleList.add(new People(info_list));
            }
        }catch(FileNotFoundException e){
            System.out.println("No file found: "+pathToFile);
            return;
        }

        InvertedIndex invertedIndex=new InvertedIndex(peopleList);

        int option=-1;

        Scanner scanner=new Scanner(System.in);
        while(option!=0){
            MenuOption.displayMenu();
            option=Integer.parseInt(scanner.nextLine());
            switch(option){
                case 1:
                    searchPeople(invertedIndex,peopleList.size(),peopleList);
                    break;
                case 2:
                    System.out.println("=== List of people ===");
                    for(People people:peopleList){
                        System.out.println(people);
                    }
                    break;
                case 0:
                    System.out.println("Bye!");
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }

    }

    private static void searchPeople(InvertedIndex invertedIndex,int n, List<People> peopleList){
        System.out.println("Select a matching strategy: ALL, ANY, NONE");

        Scanner scanner=new Scanner(System.in);
        String strategy;
        while(true){
            strategy=scanner.nextLine();
            strategy=strategy.toUpperCase();
            if(!(strategy.equals("ALL") || strategy.equals("ANY") || strategy.equals("NONE"))){
                System.out.println("Please choose a valid strategy");
            }else break;
        }

        System.out.println("Enter a name or email to search all suitable people.");

        String query=scanner.nextLine().trim();

        Set<Integer>indexSet=new HashSet<>();
        Map<String,ArrayList<Integer>> indexMap=invertedIndex.getIndexMap();
        SearchEngine searchEngine;
        switch (strategy){
            case "ALL":
                searchEngine=new SearchAll(indexMap,n);
                indexSet=searchEngine.findData(query);
                break;
            case "ANY":
                searchEngine=new SearchAny(indexMap,n);
                indexSet=searchEngine.findData(query);
                break;
            case "NONE":
                searchEngine=new SearchNone(indexMap,n);
                indexSet=searchEngine.findData(query);
                break;
        }

        if(indexSet.isEmpty()){
            System.out.println("No matching people found.");
        }else{
            System.out.println(indexSet.size()+" persons found:");
            for(int idx:indexSet){
                System.out.println(peopleList.get(idx));
            }
        }
    }
}