package com.springdata;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Маша on 11.11.2017.
 */
public class Run {
    public static void main(String[] args) {
        ApplicationContext ctx=new ClassPathXmlApplicationContext("ApplicationContext");
        StudentDAO studentDAO=(StudentDAO)ctx.getBean("studentNameJdbaDao");
        System.out.println(studentDAO.getStatistica());
        //studentDAO.insertAll(new Student("Nina",955,2,new Facultet("TK","EK")));
        //System.out.println(studentDAO.getStudentById(6));
        //studentDAO.insertStudent(new Student("Vera",945,4,new Facultet("TK","EVM")));
        //System.out.println(studentDAO.getListSudentByName("Vera"));
        studentDAO.newInsert(new Student("Oleg",945,3,1));

    }
}
