package me.learn.springnote.springTest.basic.dao;

import me.learn.springnote.springTest.basic.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

//테스트 메소드에서 어플리케이션 컨텍스트의 구성이나 상태를 변경한다는 것을
//테스트 컨텍스트 프레임워크에 알려준다.
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "/config/springTest/applicationContext.xml")
@DirtiesContext
class UserDaoTest {
    @Autowired
    private UserDao dao;
    private User user1;
    private User user2;
    private User user3;

    @BeforeEach
    void setUp(){
        DataSource datasource = new SingleConnectionDataSource(
                "jdbc:mysql://localhost/testdb", "yoon", "yoon", true
        );
        //코드에 의한 수동 DI
        dao.setDataSource(datasource);
    }

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