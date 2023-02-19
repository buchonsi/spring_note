package me.learn.springnote.ver1.user.dao;

import me.learn.springnote.ver1.user.domain.User;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
<<<<<<< HEAD
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
=======
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "spring", "book"
>>>>>>> f15d4c3 (#1 - User Dao 구현)
        );

        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)"
        );

        ps.setString(1, user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

<<<<<<< HEAD

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
=======
    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/springbook", "spring", "book"
>>>>>>> f15d4c3 (#1 - User Dao 구현)
        );

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
<<<<<<< HEAD
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
=======
        user.setId(rs.getString("name"));
        user.setId(rs.getString("password"));
>>>>>>> f15d4c3 (#1 - User Dao 구현)

        rs.close();
        ps.close();
        c.close();

        return user;
    }
<<<<<<< HEAD

    public void deleteAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
        );

        PreparedStatement ps = c.prepareStatement(
                "delete from users;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }
=======
>>>>>>> f15d4c3 (#1 - User Dao 구현)
}
