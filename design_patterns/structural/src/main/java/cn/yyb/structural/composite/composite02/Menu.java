package cn.yyb.structural.composite.composite02;

import java.util.ArrayList;
import java.util.List;

// 菜单类
class Menu implements Component {
    private String name;
    private List<Component> menuItems = new ArrayList<>();

    public Menu(String name) {
        this.name = name;
    }

    public void addMenuItem(Component menuItem) {
        menuItems.add(menuItem);
    }

    public void removeMenuItem(Component menuItem) {
        menuItems.remove(menuItem);
    }

    @Override
    public void display() {
        System.out.println("Menu: " + name);
        System.out.println("Menu Items:");
        for (Component menuItem : menuItems) {
            menuItem.display();
        }
    }
}