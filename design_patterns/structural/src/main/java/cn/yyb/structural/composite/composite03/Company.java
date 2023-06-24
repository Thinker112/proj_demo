package cn.yyb.structural.composite.composite03;

import java.util.ArrayList;
import java.util.List;

// 公司类
class Company implements Component {
    private final String name;
    private final List<Component> departments = new ArrayList<>();

    public Company(String name) {
        this.name = name;
    }

    public void addDepartment(Component department) {
        departments.add(department);
    }

    public void removeDepartment(Component department) {
        departments.remove(department);
    }

    @Override
    public void display() {
        System.out.println("Company: " + name);
        System.out.println("Departments:");
        for (Component department : departments) {
            department.display();
        }
    }
}