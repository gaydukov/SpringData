package com.springdata;

import java.util.List;
import java.util.Map;

/**
 * Created by Маша on 11.11.2017.
 */
public interface StudentDAO {

    void insertAll(Student student);
    void insertStudent (Student student);
    int insertFacultet (Facultet facultet);
    int newInsert (Student student);
    void delete (int id);
    Student getStudentById (int id);
    List<Student> getListSudentByName (String name);
    List<Student> getListStudentByGrupa (int grupu);
    Map<Integer,Integer> getStatistica();
}
