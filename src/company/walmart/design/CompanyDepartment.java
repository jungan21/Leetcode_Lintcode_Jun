package company.walmart.design;

import java.util.ArrayList;
import java.util.List;

public class CompanyDepartment {
}

class Employee {

    // 姓名
    private String name;
    // 职位
    private String dept;
    // 下属员工
    private List<Employee> subordinates;

    public Employee(String name, String dept) {
        this.name = name;
        this.dept = dept;
        this.subordinates = new ArrayList<>();
    }

    public void addSub(Employee s) {
        subordinates.add(s);
    }

    public void show(int dept) {

        for (int i = 0; i < dept; i++) {
            System.out.print("-");
        }

        System.out.println(this.toString());

        for (Employee e : subordinates) {
            e.show(dept + 2);
        }

    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", dept='" + dept + '\'';
    }

}