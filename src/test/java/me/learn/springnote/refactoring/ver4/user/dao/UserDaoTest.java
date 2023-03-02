package me.learn.springnote.refactoring.ver4.user.dao;

import me.learn.springnote.refactoring.ver4.user.dao.DaoFactory;
import me.learn.springnote.refactoring.ver4.user.dao.UserDao;
import me.learn.springnote.refactoring.ver4.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 문제점 : Test코드는 UserDao가 잘 동작하는지 테스트를 위해 만들어졌으나,
 *        ConnectionMaker 구현 클래스를 선택하는 client 기능이 추가되었다.
 *        성격이 다른 책임이나 관심사는 분리해야하므로 client 기능을 분리해야한다.
 * 해결 방안 : 팩토리
 */
class UserDaoTest {
    @Test
    @DisplayName("유저 등록")
    void add_user() throws SQLException, ClassNotFoundException {
        //given
        UserDao dao = new DaoFactory().userDao();

        //when
        User user = new User();
        user.setId("buchonsi");
        user.setName("yoon");
        user.setPassword("yoon1");

        dao.add(user);

        //then
        assertThat(user.getId()).isEqualTo("buchonsi");
        dao.deleteAll();
    }

    @Test
    @DisplayName("유저 조회")
    void select_user() throws SQLException, ClassNotFoundException {
        //given
        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("buchonsi");
        user.setName("yoon");
        user.setPassword("yoon1");

        dao.add(user);

        //when
        User findUser = dao.get("buchonsi");

        //then
        assertThat(findUser).usingRecursiveComparison().isEqualTo(user);
        dao.deleteAll();
    }
}