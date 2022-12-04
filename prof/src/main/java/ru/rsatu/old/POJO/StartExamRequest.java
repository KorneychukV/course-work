package ru.rsatu.old.POJO;

import lombok.Data;

@Data
public class StartExamRequest {

    Long programId;
    String userId;
    Boolean isFinal;

}
