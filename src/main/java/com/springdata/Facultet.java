package com.springdata;

/**
 * Created by Маша on 11.11.2017.
 */
public class Facultet {
    public int idFacultet;
    public String facultetName;
    public String kafedra;

    public Facultet() {
    }
    public Facultet(String facultetName, String kafedra) {
        this.facultetName = facultetName;
        this.kafedra = kafedra;
    }

    public int getIdFacultet() {
        return idFacultet;
    }

    public void setIdFacultet(int idFacultet) {
        this.idFacultet = idFacultet;
    }

    public String getFacultetName() {
        return facultetName;
    }

    public void setFacultetName(String facultetName) {
        this.facultetName = facultetName;
    }

    public String getKafedra() {
        return kafedra;
    }

    public void setKafedra(String kafedra) {
        this.kafedra = kafedra;
    }

    @Override
    public String toString() {
        return "Facultet{" +
                "idFacultet=" + idFacultet +
                ", facultetName='" + facultetName + '\'' +
                ", kafedra='" + kafedra + '\'' +
                '}';
    }
}
