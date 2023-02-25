package me.learn.springnote.ver4.user.dao;

/**
 * IoC구조의 적용
 * IoC(제어의 역전) : 제어의 흐름을 사용하는 쪽이 아닌 외부에 권한을 위힘하는 것.
 *                  프레임 워크에는 이러한 제어의 역전을 가지고 있어야 한다.
 *                  따라서 애플리케이션 코드는 프레임워크가 짜놓은 틀에서 수동적으로 동작해야한다.
 *
 * 제어의 역전에서는 프레임워크 또는 컨테이너와 같이
 *                  애플리케이션 컴포넌트 생성과 관계설정,
 *                  사용, 생명주기 관리 등을 관장하는 존재가 필요하다.
 * DaoFactory는 오브젝트 수준의 가장 단순한 IoC 컨테이너라고 볼 수 있다.
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
