package com.springdata;

/**
 * Created by Маша on 11.11.2017.
 */
public class Student {
    public int idstudent;
    public String name;
    public int grupa;
    public int ocenka;
    public int facultetId;
    public Facultet facultet;

    public Student(String name, int grupa, int ocenka, Facultet facultet) {
        this.name = name;
        this.grupa = grupa;
        this.ocenka = ocenka;
        this.facultet = facultet;
    }

    public Student(String name, int grupa, int ocenka) {

        this.name = name;
        this.grupa = grupa;
        this.ocenka = ocenka;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "idstudent=" + idstudent +
                ", name='" + name + '\'' +
                ", grupa=" + grupa +
                ", ocenka=" + ocenka +
                ", facultet=" + facultet +
                '}';
    }

    public int getIdstudent() {
        return idstudent;
    }

    public void setIdstudent(int idstudent) {
        this.idstudent = idstudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getOcenka() {
        return ocenka;
    }

    public void setOcenka(int ocenka) {
        this.ocenka = ocenka;
    }
    public Facultet getFacultet() {
        return facultet;
    }

    public void setFacultet(Facultet facultet) {
        this.facultet = facultet;
    }


}
