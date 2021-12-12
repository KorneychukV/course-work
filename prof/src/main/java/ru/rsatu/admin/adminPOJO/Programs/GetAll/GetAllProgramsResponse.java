package ru.rsatu.admin.adminPOJO.Programs.GetAll;

import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.StudyProgram;
import ru.rsatu.tables.StudySection;

import java.util.ArrayList;
import java.util.List;

public class GetAllProgramsResponse extends BaseResponse {

    private int contentSize;
    private List<StudyProgram> list = new ArrayList<>();

    public GetAllProgramsResponse() {
    }

    public GetAllProgramsResponse(int contentSize, List<StudyProgram> list) {
        this.contentSize = contentSize;
        this.list = list;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public List<StudyProgram> getList() {
        return list;
    }

    public void setList(List<StudyProgram> list) {
        this.list = list;
    }

}
