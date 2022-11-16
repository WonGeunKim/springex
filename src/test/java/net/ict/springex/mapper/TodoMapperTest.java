package net.ict.springex.mapper;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.domain.TodoVO;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.PageResponseDTO;
import net.ict.springex.dto.TodoDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoMapperTest {

    @Autowired(required = false)
    private TodoMapper todoMapper;

    @Test
    public void testGetTime(){
        log.info(todoMapper.getTime());
    }


    @Test
    public void testInsert(){
        TodoVO todoVO = TodoVO.builder()
                .title("Spring Test")
                .dueDate(LocalDate.of(2022, 11, 14))
                .writer("ict05")
                .build();

        todoMapper.insert(todoVO);
    }

    @Test
    public void testSelectAll(){
        List<TodoVO> voList = todoMapper.selectAll();
        voList.forEach(vo -> log.info(vo));
    }

    @Test
    public void testDelete(){
        Long tno = 5l;
        todoMapper.delete(tno);
    }

    @Test
    public void testUpdate(){
        TodoVO todoVO = TodoVO.builder()
                .title("타이틀 수정2")
                .dueDate(LocalDate.of(2022, 11,18))
                .finished(true)
                .tno(4l)
                .build();

        todoMapper.update(todoVO);
    }

    @Test
    public void testSelectOne(){
        Long tno = 4l;
        TodoVO todoVO = todoMapper.selectOne(tno);
        log.info(todoVO);
    }

    @Test
    public void testSelectList(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));
    }


    @Test
    public void testSelectSearch(){
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .types(new String[] {"t", "w"})
                .keyword("스프링")
                .finished(true)
                .from(LocalDate.of(2022, 11, 10))
                .to(LocalDate.of(2022,11,30))
                .build();
        List<TodoVO> voList = todoMapper.selectList(pageRequestDTO);
        voList.forEach(vo -> log.info(vo));

    }

}