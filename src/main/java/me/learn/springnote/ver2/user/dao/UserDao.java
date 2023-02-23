package me.learn.springnote.ver2.user.dao;

import me.learn.springnote.ver2.user.domain.User;

import java.sql.*;

/**
 * 개선 목표 : 1. DB와 연결을 위한 커넥션 가져오기
 *           2. DB에 보낼 sql 문장을 담을 statement를 만들고 실행 (바인딩과 실행도 분리할 수 있음)
 *           3. 리소스의 반환
 * 기타 문제 : 예외 처리가 없음 (나중에 처리)
 */
public abstract class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection c = getConnection();

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
        Connection c = getConnection();

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
        Connection c = getConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from users;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    /**
     * 중복 메소드 추출
     * 상속을 통해 서브클래스에서 DB커넥션 연결을 선택하도록 한다. (템플릿 메소트 패턴)
     * --템플릿 메소트 패턴 : 슈퍼클래스에서 기본적인 로직의 흐름을 만들고 기능의 일부를 추상메소드나
     *                     오버라이딩 가능한 protected로 만들어 서브클래스에서 이런 메소드를 필요에 맞게 구현.
     *
     */
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
}
