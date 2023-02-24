package me.learn.springnote.ver3.user.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 인터페이스를 만들어 느슨한 연결고리를 만든다.(추상화)
 */
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
