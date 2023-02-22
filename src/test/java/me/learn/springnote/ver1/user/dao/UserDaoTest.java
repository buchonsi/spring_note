package me.learn.springnote.ver1.user.dao;

import me.learn.springnote.ver1.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {
    @Test
    @DisplayName("유저 등록")
    void add_user() throws SQLException, ClassNotFoundException {
        //given
        UserDao dao = new UserDao();

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
        UserDao dao = new UserDao();

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