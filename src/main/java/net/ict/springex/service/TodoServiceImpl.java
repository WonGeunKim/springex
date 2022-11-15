package net.ict.springex.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<TodoDTO> getAll() {

        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))  // dto는 모델매퍼를 통해서 vo로 바뀜
                .collect(Collectors.toList());  // 전체 테이블 묶음을 리스트로 묶어준다

        // VO에 한 row씩 담은 것 -> 하나의 테이블로 묶어줘야함 => TodoVO를 TodoDTO로 바꾸고 collect 메소드를 통해서 하나의 테이블처럼 변환

        // List<TodoVO>를 List<TodoDTO>로 변환하는 작업을 stream을 이요하여 각 TodoVO는 map()을 통해서 TodoDTO로 바꾸고
        // collect()을 이용하여 List<TodoDTO>로 묶어준다

        return dtoList;
    }

    @Override
    public void remove(Long tno) {
        todoMapper.delete(tno);
    }

    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);
        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void modify(TodoDTO todoDTO) {

        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);
        todoMapper.update(todoVO);
    }

}
