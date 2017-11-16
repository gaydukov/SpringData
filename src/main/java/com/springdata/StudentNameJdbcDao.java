package com.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;



@Component
public class StudentNameJdbcDao implements StudentDAO {

    public SimpleJdbcInsert insertStudent;
    public NamedParameterJdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource (DataSource dataSource){
        this.insertStudent=new SimpleJdbcInsert(dataSource).withTableName("student").usingColumns("name","grupa","ocenka","facultetid");
        this.jdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }
    // Add new student and new facultet
    public void insertAll(Student student) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String sqlFacultet="insert into facultet (facultetname,kafedraname) values (:facultetname,:kafedraname)";
        String sqlStudent="insert into student (name,grupa,ocenka,facultetid) values (:name,:grupa,:ocenka,:facultetid)";
        Facultet facultet=student.getFacultet();
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("facultetname",facultet.getFacultetName());
        params.addValue("kafedraname",facultet.getKafedra());
        jdbcTemplate.update(sqlFacultet,params,keyHolder);

        int facultetId=keyHolder.getKey().intValue();
        params.addValue("name",student.getName());
        params.addValue("grupa",student.getGrupa());
        params.addValue("ocenka",student.getOcenka());
        params.addValue("facultetid",facultetId);
        jdbcTemplate.update(sqlStudent,params);


    }
    // Add new student and new facultet
    @Transactional (propagation = Propagation.REQUIRED)
    public void insertStudent(Student student) {
        String sqlStudent="insert into student (name,grupa,ocenka,facultetid) values (:name,:grupa,:ocenka,:facultetid)";
        MapSqlParameterSource params =new MapSqlParameterSource();
        int facultetId=insertFacultet(student.getFacultet());
        params.addValue("name",student.getName());
        params.addValue("grupa",student.getGrupa());
        params.addValue("ocenka",student.getOcenka());
        params.addValue("facultetid",facultetId);
        jdbcTemplate.update(sqlStudent,params);

    }
    // Add new facultet
    @Transactional(propagation = Propagation.REQUIRED)
    public int insertFacultet(Facultet facultet) {
        KeyHolder keyHolder=new GeneratedKeyHolder();
        String sqlFacultet="insert into facultet (facultetname,kafedraname) values (:facultetname,:kafedraname)";
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("facultetname",facultet.getFacultetName());
        params.addValue("kafedraname",facultet.getKafedra());
        jdbcTemplate.update(sqlFacultet,params,keyHolder);
        return keyHolder.getKey().intValue();
    }
    // Add new student
    @Transactional(propagation = Propagation.REQUIRED)
    public int newInsert(Student student) {
        MapSqlParameterSource params=new MapSqlParameterSource();
        params.addValue("name",student.getName());
        params.addValue("grupa",student.getGrupa());
        params.addValue("ocenka",student.getOcenka());
        params.addValue("facultetid",student.getFacultetId());
        return insertStudent.execute(params);
    }

    public void delete(int id) {
        String sql ="delete from student where idstudent=:id";
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("id",id);
        jdbcTemplate.update(sql,params);
    }

    public Student getStudentById(int id) {
        String sql ="select * from view_student where idstudent=:id";
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("id",id);
        return jdbcTemplate.queryForObject(sql,params,new StudentRowMapper());
    }

    public List<Student> getListSudentByName(String name) {
        String sql ="select * from view_student where name=:name";
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("name",name);
        return jdbcTemplate.query(sql,params,new StudentRowMapper());
    }

    public List<Student> getListStudentByGrupa(int grupa) {
        String sql ="select * from view_student where grupa=:grupa";
        MapSqlParameterSource params =new MapSqlParameterSource();
        params.addValue("grupa",grupa);
        return jdbcTemplate.query(sql,params,new StudentRowMapper());
    }

    public Map<Integer, Integer> getStatistica() {
        String sql ="select grupa, count(*) as count from student group by grupa";
        return jdbcTemplate.query(sql, new ResultSetExtractor<Map<Integer, Integer>>() {
            public Map<Integer, Integer> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                Map<Integer, Integer> map=new TreeMap<Integer, Integer>();
                while (resultSet.next()){
                    int grupa=resultSet.getInt("grupa");
                    int count = resultSet.getInt("count");
                    map.put(grupa,count);
                }
                return map;
            }
        });
    }

    private static final class StudentRowMapper implements RowMapper<Student> {

        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Facultet facultet=new Facultet();
            facultet.setFacultetName(resultSet.getString("facultetname"));
            facultet.setKafedra(resultSet.getString("kafedraname"));

            Student student=new Student();
            student.setIdstudent(resultSet.getInt("idstudent"));
            student.setName(resultSet.getString("name"));
            student.setGrupa(resultSet.getInt("grupa"));
            student.setOcenka(resultSet.getInt("ocenka"));
            student.setFacultet(facultet);
            return student;
        }
    }
}
