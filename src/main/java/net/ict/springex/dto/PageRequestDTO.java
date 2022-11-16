package net.ict.springex.dto;

// 페이지처리는 현재페이지 번호(page), 한페이지당 데이터 수(size) 기본적으로 필요

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default
    @Min(value = 1)
    @Positive
    private int page = 1;

    @Builder.Default
    @Min(value = 10)
    @Max(value = 100)
    @Positive
    private int size =10;

    public int getSkip(){
        return (page-1) * 10;
    }

//    private String link;
//    private String getLink(){
//        if(link == null){
//            StringBuilder builder = new StringBuilder();
//            builder.append("page=" + this.page);
//            builder.append("&size=" + this.size);
//            link = builder.toString();
//        }
//        return link;
//    }

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;




}
