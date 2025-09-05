package view;

public class Menu {
    Item[] items;
    InputOutput inOut;

    public Menu(Item[] items, InputOutput inOut) {
        this.items = items;
        this.inOut = inOut;
    }

    public void runMenu() {
        while (true) {
            for (int i = 0; i < items.length; i++) {
                inOut.outputLine((i + 1) + ". " + items[i].displayedName());
            }
            // 1. add car 2. remove car 3. exit
            Integer selected = inOut.inputInteger("Type item number", 1, items.length);
            if (selected == null) return;
            try {
                items[selected - 1].perform();
            } catch (Exception e) {
                inOut.outputLine(e.getMessage());
            }
            if (items[selected - 1].isExit()) return;
        }
    }
}
