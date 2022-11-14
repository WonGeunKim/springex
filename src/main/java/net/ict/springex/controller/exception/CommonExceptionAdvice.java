package net.ict.springex.controller.exception;


import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

// 예외처리
@ControllerAdvice
@Log4j2
public class CommonExceptionAdvice {

    // try /catch 가 없어짐

    // number 에 관련된 익셉션 처리
    // 익셉션이 발생하면 전용 핸들러
//    @ResponseBody   // 해당 에러가 발생 했을 때 ResponseBody 안에서
//    @ExceptionHandler(NumberFormatException.class)  // 핸들러로 지정 , 해당 익셉션이 지정이 되었다는 의미
//    public String exceptNumber(NumberFormatException numberFormatException) {
//        // 제대로 된 숫자가 들어오지 않으면 익셉션
//
//        // 에러를 처리
//        log.error("---------------------");
//        log.error(numberFormatException.getMessage());
//
//        return "NUMBER FORMAT EXCEPTION";
//    }

    // 범용적 예외처리 방식
    // 예외 처리의 최상위 타입인 Exception 타입을 처리하도록 구성 exceptCommon(Exception exception)
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public String exceptCommon(Exception exception) {

        log.error("-----------error-----------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>" + exception.getMessage() + "</li>");
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>" + stackTraceElement + "</li>");
        });
        buffer.append("</ul>");

        return buffer.toString();
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)    // 해당되는 페이지가 없다는 익셉션 발생했을 때
    public String notFound(){

        return "Custom404";
        // jsp 페이지와 이름 똑같이 지정
    }


}
