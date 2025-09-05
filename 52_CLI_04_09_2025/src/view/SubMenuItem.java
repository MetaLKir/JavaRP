package view;

public class SubMenuItem implements Item {
    String displayedName;
    InputOutput inOut;
    Item[] items;

    public SubMenuItem(String displayedName, InputOutput inOut, Item[] items) {
        this.displayedName = displayedName;
        this.inOut = inOut;
        this.items = items;
    }

    @Override
    public String displayedName() {
        return displayedName;
    }

    @Override
    public void perform() {
        Menu menu = new Menu(items, inOut);
        menu.runMenu();
    }


}
