package me.learn.springnote.ver3.user.dao;

import me.learn.springnote.ver3.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 개선 사항 : 1. 커넥션을 제공하는 클래스를 분리 (SimpleConnectionMaker)
 *           2. 커넥션 클래스를 객체로 가져와 add()와 get() 에서 사용
 * 기타 문제 : 관심사의 분리를 클래스를 분리하여 나눴지만 이전 NUserDao와 DUserDao처럼
 *            커넥션 기능의 확장이 불가능 해짐.
 */
public class UserDao {

    private SimpleConnectionMaker simpleConnectionMaker;

    public UserDao() {
        /**
         * 자유로운 확장이 불가능해지고,
         * 커넥션을 생성하는 클래스가 변경되면 생성 코드도 변경을 해주어야 한다.
         * 커넥션을 제공하는 클래스를 직접 생성하였기때문.
         */
        this.simpleConnectionMaker = new SimpleConnectionMaker();
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        /**
         * 메소드 이름의 강제성이 업으므로 SimpleConnectionMaker의 메소드 명이 변경되면
         * 메소드를 사용한 코드도 변경해주아야 한다.
         */
        Connection c = simpleConnectionMaker.makeNewConnection();

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
        Connection c = simpleConnectionMaker.makeNewConnection();

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
        Connection c = simpleConnectionMaker.makeNewConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
