package com.tolyaolya.mygoals;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 111 on 31.07.2016.
 */
public class StatisticHandler {
    String period;
    String total;
    String done;
    String missing;
    String progress;

    StatisticHandler(String period, String total, String done, String missing, String progress) {
        this.period = period;
        this.total = total;
        this.done = done;
        this.missing=missing;
        this.progress=progress;
    }

}

