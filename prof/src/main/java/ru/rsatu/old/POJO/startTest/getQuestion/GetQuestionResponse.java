package ru.rsatu.old.POJO.startTest.getQuestion;

import lombok.*;
import ru.rsatu.common.BaseResponse;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class GetQuestionResponse extends BaseResponse {

    private CurrTestRespones test;
    private Boolean isComplete;
}
