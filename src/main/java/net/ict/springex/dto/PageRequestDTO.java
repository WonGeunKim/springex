package net.ict.springex.dto;

// 페이지처리는 현재페이지 번호(page), 한페이지당 데이터 수(size) 기본적으로 필요

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

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

    private String[] types;
    private String keyword;
    private boolean finished;
    private LocalDate from;
    private LocalDate to;

    public boolean checkType(String type){
        if (types == null || types.length == 0){
            return false;
        }
        return Arrays.stream(types).anyMatch(type::equals);
    }


    // 다시 돌아갔을 때 정보 그대로 저장
    // 화면에서 모든 링크의 수정을 방지
    public String getLink(){
        StringBuilder builder = new StringBuilder();

        builder.append("page=" + this.page);
        builder.append("&size=" + this.size);

        if (finished){
            builder.append("&finished=on");
        }
        if (types != null && types.length > 0){
            for (int i = 0; i < types.length; i++){
                builder.append("&types=" + types[i]);
            }
        }
        if (keyword != null){
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        if(from != null){
            builder.append("&from=" + from.toString());
        }
        if(to != null){
            builder.append("&to=" + to.toString());
        }

        return builder.toString();

    }


}



