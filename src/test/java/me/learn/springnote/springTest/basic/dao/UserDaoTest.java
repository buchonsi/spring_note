package me.learn.springnote.springTest.basic.dao;

import me.learn.springnote.springTest.basic.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@RunWith(SpringJUnit4ClassRunner.class)  --Junit4
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/config/springTest/applicationContext.xml")
class UserDaoTest {
    @Autowired
    private UserDao dao;
    @Autowired
    DataSource dataSource;
    private User user1;
    private User user2;
    private User user3;

    @Test
    void addAndGet() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        this.user1 = new User("yoon", "윤", "yoon");
        this.user2 = new User("kim", "김", "kim");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName()).isEqualTo(user1.getName());
        assertThat(userget1.getPassword()).isEqualTo(user1.getPassword());

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName()).isEqualTo(user2.getName());
        assertThat(userget2.getPassword()).isEqualTo(user2.getPassword());
    }

    @Test
    void count() throws SQLException {
        this.user1 = new User("yoon", "윤", "yoon");
        this.user2 = new User("kim", "김", "kim");
        this.user3 = new User("park", "박", "park");

        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        dao.add(user1);
        assertThat(dao.getCount()).isEqualTo(1);

        dao.add(user2);
        assertThat(dao.getCount()).isEqualTo(2);

        dao.add(user3);
        assertThat(dao.getCount()).isEqualTo(3);
    }

    //    JUnit4 에러 처리 방식
//    @Test(expected = EmptyResultDataAccessException.class)
    @Test
    void getUserFailure() throws SQLException {
        dao.deleteAll();
        assertThat(dao.getCount()).isEqualTo(0);

        EmptyResultDataAccessException exception = assertThrows(EmptyResultDataAccessException.class, () -> dao.get("unknown_id"));
//        assertEquals(exception.getMessage(),"Incorrect result size: expected 1, actual 0");
    }
}