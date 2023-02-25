package me.learn.springnote.ver4.user.dao;

/**
 * 객체 생성방법을 결정하고 생성된 객체를 돌려주는 역할인 팩토리 생성.
 *         - 주의 : 디자인 패턴에서 말하는 추상 팩토리 패턴과 팩토리 메소드 패턴과는 다르다.
 *  남아있는 문제점 : 다른 기능을 하는 Dao가 추가 되었을때,
 *                 ConnectionMaker를 구현하는 클래스의 인스턴스 생성 코드가 중복되게 된다.
 *  해결 방법 : 오브젝트 팩토리의 활용
 */
public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker();
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
