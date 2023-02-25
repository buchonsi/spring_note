package me.learn.springnote.ver4.user.dao;

/**
 * 중복 코드를 메소드로 추출
 */
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }

    private static ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
