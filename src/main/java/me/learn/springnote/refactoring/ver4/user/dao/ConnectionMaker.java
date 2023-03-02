package me.learn.springnote.refactoring.ver4.user.dao;

import java.sql.Connection;
import java.sql.SQLException;
public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
