package ru.rsatu.testing.startTest.statistic;

import ru.rsatu.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class StatisticResponse extends BaseResponse {

    List<Statistic> statistics = new ArrayList<>();
    Long countPage;

    public StatisticResponse() {
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistic> statistics) {
        this.statistics = statistics;
    }

    public Long getCountPage() {
        return countPage;
    }

    public void setCountPage(Long countPage) {
        this.countPage = countPage;
    }
}
