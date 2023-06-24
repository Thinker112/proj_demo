package cn.yyb.structural.composite.composite03;

public class Client {
    public static void main(String[] args) {
        Component engineering = new Department("Engineering");
        Component sales = new Department("Sales");
        Component marketing = new Department("Marketing");

        Company company = new Company("ABC Company");
        company.addDepartment(engineering);
        company.addDepartment(sales);
        company.addDepartment(marketing);

        Component hr = new Department("HR");
        Component finance = new Department("Finance");

        Company subCompany = new Company("XYZ Sub-Company");
        subCompany.addDepartment(hr);
        subCompany.addDepartment(finance);

        company.addDepartment(subCompany);

        company.display();
    }
}
