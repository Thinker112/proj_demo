package cn.yyb.structural.composite.composite02;

// 菜单项类
class MenuItem implements Component {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("Menu Item: " + name);
    }
}