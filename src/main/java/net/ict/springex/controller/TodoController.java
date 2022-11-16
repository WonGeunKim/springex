package net.ict.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.ict.springex.dto.PageRequestDTO;
import net.ict.springex.dto.TodoDTO;
import net.ict.springex.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    // 서비스를 컨트롤러와 연결
    // 서비스에 register 메소드가 있기 때문에
    private final TodoService todoService;

    /**/
    // 최종 경로는 /todo/list가 된다
    @RequestMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult , Model model){

//        log.info("todo list.......");
        log.info(pageRequestDTO);

        if (bindingResult.hasErrors()){
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));

        // todoService.getAll() 로 "dtoList"에 담는다
//        model.addAttribute("dtoList", todoService.getAll());
        // model에는 "dtoList" 이름으로 목록데이터가 담겨있다

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
    public String registerPost(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        // @Valid - TodoDTO는 검증 대상이다 - 어노테이션 지정해놓은 것을 검사한다

        log.info("POST todo register.........");

        // bindingResult에 에러가 있다면 (DTO에 어노테이션 한것에 에러가 있다면)
        if(bindingResult.hasErrors()){
            log.info("has error.......");

            // 한번 수행하고 날아가는 값
            // 잘못된 결과를 에드플래시에 담아서 리다이렉트로 처리하겠다
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            /// BindingResult : Validator를 상속받는 클래스에서 객체값을 검증하는 방식

            return "redirect:/todo/register";
        }

        log.info(todoDTO);

        // db에 들어감
        todoService.register(todoDTO);

        return "redirect:/todo/list";
    }
    // => 자동 형변환을 지원한다
    // post 방식으로 받아야 하는 것은 dto가 구현되어 있어야 한다


    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes){
        log.info("----Remove-----");
        log.info("tno : " + tno);
        todoService.remove(tno);
        return "redirect:/todo/list";
    }

    // get 방식의 여러개의 경로를 배열 형식으로 지정 가능
   @GetMapping({"/read", "/modify"})
    public void read(PageRequestDTO pageRequestDTO ,Long tno, Model model){
        TodoDTO todoDTO = todoService.getOne(tno);
        log.info(todoDTO);
        model.addAttribute("dto", todoDTO);
   }

   @PostMapping("/modify")
    public String update(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("has error.....");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info(todoDTO);
        todoService.modify(todoDTO);
        return "redirect:/todo/list";
   }




}
