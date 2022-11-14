package net.ict.springex.mapper;

// XML 파일 만들 때 이름 똑같이 만들어야함

import net.ict.springex.domain.TodoVO;

public interface TodoMapper {

    // xml 안에 select 문 id 동일하게
    String getTime();

    void insert(TodoVO todoVO);

}


