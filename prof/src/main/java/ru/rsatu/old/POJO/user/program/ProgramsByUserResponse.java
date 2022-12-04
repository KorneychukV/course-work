package ru.rsatu.old.POJO.user.program;

import ru.rsatu.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class ProgramsByUserResponse extends BaseResponse {

    private List<StudyProgramByUser> program = new ArrayList<>();

    public ProgramsByUserResponse() {
    }

    public ProgramsByUserResponse(List<StudyProgramByUser> list) {
        this.program = list;
    }

    public List<StudyProgramByUser> getProgram() {
        return program;
    }

    public void setProgram(List<StudyProgramByUser> program) {
        this.program = program;
    }
}
