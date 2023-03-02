package me.learn.springnote.refactoring.ver2.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao extends UserDao{

    /**
     * getConnection에서 JDBC가 정의한 Connection 인터페이스를 구현한 Connection 객체를 생성 (팩토리 메서드 패턴)
     * NUserDao와 DUserDao는 Connection 객체를 생성하는데 관심. (어떻게 만들지, 어떻게 connection 기능을 제공하는지)
     * --팩토리 메서드 패턴 :서브 클래스에서 구체적인 오브젝트 생성 방법을 결정한다.
     */
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //DB 커넥션 생성 (N용 DB)
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost/yoon", "yoon", "yoon"
        );
        return c;
    }
}
