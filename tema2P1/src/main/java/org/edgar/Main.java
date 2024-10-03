package org.edgar;
public class Main {
    public static void main(String[] args) {
        YoutuberService youtuberService = new YoutuberService();
        youtuberService.createYoutubers();

        System.out.println("Youtuber con más seguidores: " + youtuberService.youtuberMoreFollowers().name());
        System.out.println("Media de vídeos: " + youtuberService.averageVideos());

        System.out.println("Youtubers que empezaron en 2013:");
        youtuberService.youtubers2013().forEach(y -> System.out.println(y.name()));

        System.out.println("Top 3 Youtubers con mayores ingresos estimados:");
        youtuberService.youtubersMoreInCome().forEach(y -> System.out.println(y.name() + ": " + y.estimatedIncome()));

        System.out.println("Youtubers agrupados por año:");
        youtuberService.youtubersGroupByYear().forEach((year, youtubers) -> {
            System.out.println(year + ":");
            youtubers.forEach(y -> System.out.println("  " + y.name()));
        });
    }
}
