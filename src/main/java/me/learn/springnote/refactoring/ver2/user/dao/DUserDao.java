package me.learn.springnote.refactoring.ver2.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //DB 커넥션 생성 (D용 DB)
        return null;
    }
}
