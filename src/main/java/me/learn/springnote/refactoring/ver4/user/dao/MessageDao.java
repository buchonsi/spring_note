package me.learn.springnote.refactoring.ver4.user.dao;

import me.learn.springnote.refactoring.ver4.user.domain.Message;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageDao {
    private ConnectionMaker connectionMaker;

    public MessageDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(Message message) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into messages(id, content) values(?,?)"
        );

        ps.setLong(1, message.getId());
        ps.setString(2,message.getContent());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Message get(Long id) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from messages where id = ?"
        );
        ps.setLong(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        Message message = new Message();
        message.setId(rs.getLong("id"));
        message.setContent(rs.getString("content"));

        rs.close();
        ps.close();
        c.close();

        return message;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from messages;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
