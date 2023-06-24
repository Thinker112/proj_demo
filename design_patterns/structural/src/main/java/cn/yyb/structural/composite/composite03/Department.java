package cn.yyb.structural.composite.composite03;

// 部门类
class Department implements Component {
    private String name;

    public Department(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        System.out.println("Department: " + name);
    }
}
