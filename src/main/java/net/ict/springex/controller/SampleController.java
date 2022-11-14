package net.ict.springex.controller;

import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.TodoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

// 모든 controller 클래스에 @Controller 어노테이션
// @Controller 로 등록한 클래스가 servlet-context.xml 이 스캔한다

@Controller
@Log4j2
public class SampleController {

    // get 방식으로 들어오는 요청을 처리하기 위해
    @GetMapping("/hello")
    public void hello(){

            // hello 경로 요청이 들어올 때 출력
            log.info("hello...");
        }

    @GetMapping("/ex1")
    public void ex1(String name, int age){
        log.info("ex1............");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    // parameter가 넘어오지 않을 경우 문제가 생기기 때문에 기본값을 지정해준다
    @GetMapping("/ex2")
    public void ex2(@RequestParam(name = "name", defaultValue = "AAA") String name, @RequestParam(name = "age", defaultValue = "20") int age){
        log.info("ex2........");
        log.info("name : " + name);
        log.info("age : " + age);
    }

    @GetMapping("/ex3")
    public void ex3(LocalDate dueDate){
        log.info("ex3.............");
        log.info("dueDate" + dueDate);
    }

    @GetMapping("/ex4")
    public void ex4(Model model){
        log.info("Model info.............");
        model.addAttribute("message", "Hello SpringMVC!");
    }

    /// Model을 생성할 필요x , 파라미터로 선언만 하면 알아서 만들어준다
    @GetMapping("/ex5")
    public void ex5(TodoDTO todoDTO, Model model){
        log.info("Model TodoDTO info.............");
        log.info(todoDTO);
        //model.addAttribute("dto", todoDTO);
    }

    @GetMapping("/ex6")
    public String ex6(RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("name", "ABC");
        redirectAttributes.addFlashAttribute("result", "success");

        // 스프링에서 리턴하는 방법
        // 문자열을 반환하라는 의미
        return "redirect:/ex7";
    }
    // ex6 파라미터 추가 - 키 값 추가
    // ex7 사용자가 볼 페이지 준비 - name result 값을 볼 페이지

    // ex7 으로 리다이렉트 시키는 것
    @GetMapping("/ex7")
    public void ex7(){
    }

    @GetMapping("/ex8")
    public void ex8(String p1, int p2){
        log.info("p1---" + p1);
        log.info("p2---" + p2);
    }




}
