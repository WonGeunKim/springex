package net.ict.springex.dto;


import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

// 공식 : 객체 자료형은 파라미터로 처리하기 위해서는 반드시 객체로 생성되고 setXXX() 이용해서 처리해야 한다

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    // 빈 문자열은 절대 들어올 수 없다
    @NotEmpty
    private String title;

    // 현재보다 미래인지? - 과거가 들어오면 안된다
    @Future
    private LocalDate dueDate;

    private boolean finished;

    // 빈문자열도 안되고 널도 안된다
    @NotEmpty
    private String writer;  // 작성자를 의미

}
// 처리하는 컨트롤러 만들어야함