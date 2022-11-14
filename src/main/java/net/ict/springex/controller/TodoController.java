package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/todo")
@Log4j2
public class TodoController {

    // 최종 경로는 /todo/list가 된다
    @RequestMapping("/list")
    public void list(Model model){
        log.info("todo list.......");
    }

    // get 방식의 register
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public void register(){
        log.info("todo register.......");
    }

//    @GetMapping("/register")
//    public void registerGet(){
//        log.info("Get todo register.....");
//    }


    // '/todo/register' 매핑을 이용해서 POST 방식으로 처리하는 메소드 TodoDTO 를 파라미터로 적용
    @PostMapping("/register")
    public String registerPost(TodoDTO todoDTO, RedirectAttributes redirectAttributes){
        log.info("POST todo register.........");

        log.info(todoDTO);

        return "redirect:/todo/list";
    }
    // => 자동 형변환을 지원한다
    // post 방식으로 받아야 하는 것은 dto가 구현되어 있어야 한다


}
