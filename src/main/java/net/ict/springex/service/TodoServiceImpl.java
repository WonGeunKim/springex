package net.ict.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

// 데이터베이스를 처리하는 TodoMapper 와 DTO 와 VO 변환을 처리하는 ModelMapper 주입

// root-context.xml 에 컴포넌트 스캔 등록

@Service
@Log4j2
@RequiredArgsConstructor    // 생성자 주입이 필요한 객체의 타입을 final로 고정하고 어노테이션을 이용하여 생성자를 생성하는 방식 사용
public class TodoServiceImpl implements TodoService{

    private final TodoMapper todoMapper;

    private final ModelMapper modelMapper;

    @Override
    public void register(TodoDTO todoDTO) {
        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        log.info(todoVO);
        todoMapper.insert(todoVO);

    }


}
