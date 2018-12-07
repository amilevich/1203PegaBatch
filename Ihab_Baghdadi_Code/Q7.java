/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw;

/**
 *
 * @author ijb87
 */
public class Q7 implements Comparable{
    String name;
    double salary;

    //Other implementations

    public int compareTo(Object o1) {

        Employee e = (Employee)o1;
        int iSalComaprison = Integer.compare((int) this.salary, e.salary);
        if (iSalComaprison == 0)//Salaries are equal use name as comparison criteria
        {
            //left hand side name comparison with right hand side name
            return name.compareTo(e.name);
        }
        //Now if salaries are not equal, return comparison of salaries
        return iSalComaprison;

    }

    private static class Employee {

        private String name;
        private int salary;

        public Employee() {
        }
    }

}
