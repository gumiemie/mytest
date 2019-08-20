package com.guyang.sources.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * @author guyang <guyang@ebnew.com>
 * @description
 * @date 2019-06-22 13:33
 */
public class Employee implements Comparable<Employee> {
    private Integer id;
    private String name;
    private Integer age;
    private Double salary;
    private Date dateJoining;


    public Employee(Integer id, String name, Integer age, Double salary, Date dateJoining) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.dateJoining = dateJoining;
    }

    public static final Comparator<Employee> ageComparator = (o1,o2) -> o1.age - o2.age;
    public static final Comparator<Employee> nameComparator = (o1,o2) -> o1.name.charAt(0)-o2.name.charAt(0);
    public static final Comparator<Employee> dateComparator = (o1,o2) -> o1.dateJoining.compareTo(o2.dateJoining);

    public static void main(String []args){
        List<Employee> employees = Arrays.asList(new Employee(1, "guyang", 36, 16000D, new Date()), new Employee(2, "zhangjing", 34, 12000D, new Date()));
        employees.sort(ageComparator);
        System.out.println(employees);
    }

    @Override
    public int compareTo(Employee o) {
        return this.id - o.id;
    }
}
