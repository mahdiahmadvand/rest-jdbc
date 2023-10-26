package com.mahdi.model.repository;

import com.mahdi.model.common.JDBC;
import com.mahdi.model.entity.Person;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonRepository implements AutoCloseable {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public PersonRepository() throws Exception {
        connection = JDBC.getConnection();
        connection.setAutoCommit(false);
    }

    public void insert(Person person) throws Exception {
        preparedStatement = connection.prepareStatement("select person_seq3.nextval x from dual");
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        person.setId(resultSet.getLong("x"));
        preparedStatement = connection.prepareStatement("insert into person (id, name, family, salary) values (?, ?, ?, ?)");
        preparedStatement.setLong(1, person.getId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setString(3, person.getFamily());
        preparedStatement.setLong(4, person.getSalary());
        preparedStatement.executeUpdate();
    }

    public String selectAll() throws Exception {
        preparedStatement = connection.prepareStatement("select * from person");
        ResultSet resultSet = preparedStatement.executeQuery();
//        List<Person> personList = new ArrayList<>();
//        while (resultSet.next()){
//            Person person = new Person();
//            person.setId(resultSet.getLong("id"));
//            person.setName(resultSet.getString("name"));
//            person.setFamily(resultSet.getString("family"));
//            person.setSalary(resultSet.getLong("salary"));
//            personList.add(person);
//        }
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", resultSet.getLong("id"));
            jsonObject.put("name", resultSet.getString("name"));
            jsonObject.put("family", resultSet.getString("family"));
            jsonObject.put("salary", resultSet.getLong("salary"));
            jsonArray.put(jsonObject);
        }
        return jsonArray.toString();
    }


    public void close() throws Exception {
        preparedStatement.close();
        connection.commit();
        connection.close();
    }
}
