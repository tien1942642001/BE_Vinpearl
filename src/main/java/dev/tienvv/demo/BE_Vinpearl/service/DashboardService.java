package dev.tienvv.demo.BE_Vinpearl.service;

import dev.tienvv.demo.BE_Vinpearl.domain.request.BookingTourStatistic;

import java.util.List;
import java.util.Map;

public interface DashboardService {
    List<Map<String, Object>> getTotal(Long startTime, Long endTime);

    List<BookingTourStatistic> statistics();
}
