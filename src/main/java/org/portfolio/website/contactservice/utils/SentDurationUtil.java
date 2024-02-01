package org.portfolio.website.contactservice.utils;

public class SentDurationUtil {

    public static String formatMailSentDuration(long duration) {
        long seconds = duration / 1000;
        long minutes = seconds / 60;
        seconds %= 60;

        if (minutes > 0) {
            return String.format("%dmin %dsec", minutes, seconds);
        } else {
            return String.format("%dsec", seconds);
        }
    }
}
