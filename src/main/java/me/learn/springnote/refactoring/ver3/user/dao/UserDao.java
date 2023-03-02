package me.learn.springnote.refactoring.ver3.user.dao;

import me.learn.springnote.refactoring.ver3.user.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 개선 사항 : 1.구현 객체를 직접 생성하는 코드를 제거 > 클라이언트에 역할을 넘김
 *           2.UserDaoTest가 클라이언트 역할 (UserDao를 사용하므로)
 *           3. UserDaoTest - UserDao - ConnectionMaker 구조가 전략패턴
 *           -- 전략 패턴 : 변경이 필요한 알고리즘을 인터페이스로 분리시키고,
 *                        구현할 클래스를 필요에 따라 바꿔서 사용할 수 있는 디자인패턴.
 *                        전략을 선택하고 바꾸는 역할은 client(UserDaoTest)에서 한다.
 */
public class UserDao {

    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

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
        Connection c = connectionMaker.makeConnection();

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
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
