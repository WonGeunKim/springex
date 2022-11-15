package net.ict.springex.service;

import net.ict.springex.dto.TodoDTO;

import java.util.List;

public interface TodoService {

    void register(TodoDTO todoDTO);

    List<TodoDTO> getAll();

    void remove(Long tno);

    TodoDTO getOne(Long tno);

    void modify(TodoDTO todoDTO);
}
