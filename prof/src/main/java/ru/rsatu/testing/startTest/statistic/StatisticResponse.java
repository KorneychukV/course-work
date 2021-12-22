package ru.rsatu.testing.startTest.statistic;

import ru.rsatu.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class StatisticResponse extends BaseResponse {

    List<Statistic> statistics = new ArrayList<>();

    public StatisticResponse() {
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }
}
