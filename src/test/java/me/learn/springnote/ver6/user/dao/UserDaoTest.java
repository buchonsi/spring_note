package me.learn.springnote.ver6.user.dao;

import me.learn.springnote.ver6.user.domain.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class UserDaoTest {
    @BeforeAll
    static void delete_user() throws SQLException {
        //given
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("config/ver6/applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

        dao.deleteAll();

    }

    @Test
    @DisplayName("유저 등록")
    void add_user() throws SQLException, ClassNotFoundException {
        //given
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("config/ver6/applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

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
//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
        ApplicationContext context = new GenericXmlApplicationContext("config/ver6/applicationContext.xml");
        UserDao dao = context.getBean("userDao", UserDao.class);

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