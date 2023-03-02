package me.learn.springnote.springTest.basic.dao;

import me.learn.springnote.springTest.basic.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao {
    private ConnectionMaker connectionMaker;

    public AccountDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(Account account) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "insert into accounts(accountNo, owner) values(?,?)"
        );

        ps.setString(1, account.getAccountNo());
        ps.setString(2, account.getOwner());

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public Account get(String accountNo) throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "select * from accounts where accountNo = ?"
        );

        ps.setString(1, accountNo);

        ResultSet rs = ps.executeQuery();
        rs.next();

        Account account = new Account();
        account.setAccountNo(rs.getString("accountNo"));
        account.setOwner(rs.getString("owner"));

        rs.close();
        ps.close();
        c.close();

        return account;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException {
        Connection c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "delete from accounts;"
        );

        ps.executeUpdate();

        ps.close();
        c.close();
    }
}
