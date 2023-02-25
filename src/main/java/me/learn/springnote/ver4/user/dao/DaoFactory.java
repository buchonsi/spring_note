package me.learn.springnote.ver4.user.dao;

/**
 * Dao가 새로 만들졌을 때 코드의 중복발생
 */
public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(new DConnectionMaker());
    }

    public AccountDao accountDao() {
        return new AccountDao(new DConnectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(new DConnectionMaker());
    }
}
