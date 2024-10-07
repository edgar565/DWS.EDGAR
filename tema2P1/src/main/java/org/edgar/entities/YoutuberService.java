package org.edgar.entities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class  YoutuberService {
    private List<Youtuber> youtubers;

    public void createYoutubers() {
        Path path = Path.of("tema2P1/src/main/resources/youtubers.csv");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try (Stream<String> lines = Files.lines(path)) {
            youtubers = lines
                    .skip(1)
                    .map(line -> line.split(","))
                    .map(data -> {
                        LocalDate dateVideo = LocalDate.parse(data[1]);
                        return new Youtuber(
                                data[0],
                                dateVideo,
                                Integer.parseInt(data[2]),
                                Integer.parseInt(data[3]));
                    })
                    .filter(youtuber -> youtuber != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Youtuber youtuberMoreFollowers() {
        return youtubers.stream().sorted(Comparator.comparingInt(Youtuber::numberFollower).reversed()).toList().get(0);
        /*return youtubers.stream()
                .max(Comparator.comparingInt(Youtuber::numberFollower))
                .stream().findFirst().orElse(null);*/
    }
    public double averageVideos() {
        return youtubers.stream().mapToInt(Youtuber::numberVideo).average().getAsDouble();
    }
    public List<Youtuber> youtubers2013() {
        return youtubers.stream()
                .filter(youtuber -> youtuber.dateVideo().getYear() == 2013)
                .collect(Collectors.toList());
    }
    public List<Youtuber> youtubersMoreInCome() {
        return youtubers.stream().sorted(Comparator.comparingDouble(Youtuber::estimatedIncome).reversed()).limit(3).collect(Collectors.toList());
    }
    public Map<Integer, List<Youtuber>> youtubersGroupByYear(){
        return youtubers.stream().collect(Collectors.groupingBy(youtuber -> youtuber.dateVideo().getYear()));
    }
}