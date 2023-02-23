package me.learn.springnote.ver2.user.dao;

import me.learn.springnote.ver2.user.domain.User;

import java.sql.*;

/**
 * 개선 목표 : 1. DB와 연결을 위한 커넥션 가져오기
 *           2. DB에 보낼 sql 문장을 담을 statement를 만들고 실행 (바인딩과 실행도 분리할 수 있음)
 *           3. 리소스의 반환
 * 기타 문제 : 예외 처리가 없음 (나중에 처리)
 */
public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
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


    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
        );

        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

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
}
