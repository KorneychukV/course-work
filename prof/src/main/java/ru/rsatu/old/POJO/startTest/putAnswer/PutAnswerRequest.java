package ru.rsatu.old.POJO.startTest.putAnswer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PutAnswerRequest {

    private Long tryAnswerId;
    private Long answerId;
}
