package net.ict.springex.domain;


// MyBatis와 스프링을 이용한 영속처리
// 1. VO 선언
// 2. Mapper 인터페이스 개발
// 3. XML 개발
// 4. 테스트 코드

import lombok.*;

import java.time.LocalDate;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoVO {

    private Long tno;
    private String title;
    private LocalDate dueDate;
    private boolean finished;
    private String writer;

}
