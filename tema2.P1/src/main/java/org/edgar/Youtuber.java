package org.edgar;

import java.time.LocalDate;

public record Youtuber (String name, LocalDate dateVideo, int numberVideo, int numberFollower) {

    public double estimatedIncome(){
        return (((double) (numberFollower * numberVideo) / 2) * 0.002);
    }
}