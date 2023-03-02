package me.learn.springnote.refactoring.ver4.user.dao;

import me.learn.springnote.refactoring.ver4.user.dao.AccountDao;
import me.learn.springnote.refactoring.ver4.user.dao.DaoFactory;
import me.learn.springnote.refactoring.ver4.user.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class AccountDaoTest {
    @Test
    @DisplayName("계좌 등록")
    void add_user() throws SQLException, ClassNotFoundException {
        //given
        AccountDao dao = new DaoFactory().accountDao();

        //when
        Account account = new Account();
        account.setAccountNo("123-12-1234");
        account.setOwner("yoon");

        dao.add(account);

        //then
        assertThat(account.getOwner()).isEqualTo("yoon");
        dao.deleteAll();
    }

    @Test
    @DisplayName("유저 조회")
    void select_user() throws SQLException, ClassNotFoundException {
        //given
        AccountDao dao = new DaoFactory().accountDao();

        Account account = new Account();
        account.setAccountNo("123-12-1234");
        account.setOwner("yoon");

        dao.add(account);

        //when
        Account findAccount = dao.get("123-12-1234");

        //then
        assertThat(findAccount).usingRecursiveComparison().isEqualTo(account);
        dao.deleteAll();
    }
}