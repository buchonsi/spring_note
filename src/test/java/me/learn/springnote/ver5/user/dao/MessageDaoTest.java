package me.learn.springnote.ver5.user.dao;

import me.learn.springnote.ver4.user.dao.DaoFactory;
import me.learn.springnote.ver4.user.dao.MessageDao;
import me.learn.springnote.ver4.user.domain.Message;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class MessageDaoTest {
    @Test
    @DisplayName("메세지 등록")
    void add_user() throws SQLException, ClassNotFoundException {
        //given
        MessageDao dao = new DaoFactory().messageDao();

        //when
        Message message = new Message();
        message.setId(1L);
        message.setContent("안녕하세요");

        dao.add(message);

        //then
        assertThat(message.getId()).isEqualTo(1L);
        dao.deleteAll();
    }

    @Test
    @DisplayName("메세지 조회")
    void select_user() throws SQLException, ClassNotFoundException {
        //given
        MessageDao dao = new DaoFactory().messageDao();

        Message message = new Message();
        message.setId(1L);
        message.setContent("안녕하세요");

        dao.add(message);

        //when
        Message findMessage = dao.get(1L);

        //then
        assertThat(findMessage).usingRecursiveComparison().isEqualTo(message);
        dao.deleteAll();
    }

}