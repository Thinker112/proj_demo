package cn.yyb.structural.composite.composite02;

public class Client {
    public static void main(String[] args) {
        Component menuItem1 = new MenuItem("Menu Item 1");
        Component menuItem2 = new MenuItem("Menu Item 2");
        Component menuItem3 = new MenuItem("Menu Item 3");

        Menu menu = new Menu("Main Menu");
        menu.addMenuItem(menuItem1);
        menu.addMenuItem(menuItem2);

        Menu subMenu = new Menu("Sub Menu");
        subMenu.addMenuItem(menuItem3);

        menu.addMenuItem(subMenu);

        menu.display();
    }
}
