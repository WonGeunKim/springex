package net.ict.springex.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {

    // 빈으로 등록해야 MyBatis가 변형시켜서 등록해준다

    // mapper 인터페이스
    // MyBatis를 통해서 수행해야 하는 기능을 매퍼 인터페이스로 작성
    // 어노테이션 혹은 xml 로 sql 작성

    // @Select 을 이용해서 쿼리문 작성할 수 있다 (세미콜론x)
    @Select("select now()")     // mapper인터페이스
    String getTime();
    // 쿼리문을 요청할 수 있는 스텁을 만든 것


}
