package search;

public enum MenuOption {
    SEARCH(1, "Find a person"),
    PRINT_ALL(2,"Print all people"),
    EXIT(0,"Exit");

    private final int option;
    private final String description;

    MenuOption(int option,String description){
        this.option=option;
        this.description=description;
    }

    public int getOption(){
        return option;
    }
    public String getDescription(){
        return description;
    }

    public static void displayMenu(){
        System.out.println("=== Menu ===");
        for(MenuOption op : MenuOption.values()){
            System.out.println(op.getOption()+". "+op.getDescription());
        }
    }
}
