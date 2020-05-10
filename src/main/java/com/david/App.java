package com.david;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
        String dateTimeString = System.getProperty("datetime");
        String timeString= System.getProperty("time");
        String shift =  System.getProperty("shift");
        String input=null;
        if (dateTimeString==null && timeString==null){
            LocalDateTime target = LocalDateTime.now().withHour(9).withMinute(0).withSecond(0);
            input = target.format(dateTimeFormat);
        } else if (dateTimeString!=null){
            input = dateTimeString;
        } else if (timeString!=null){
             input = LocalDateTime.now().format(dateFormat)+" "+timeString;
        }

        LocalDateTime target = LocalDateTime.parse(input,dateTimeFormat);
        if (shift!=null){
            target=target.plusSeconds(Long.valueOf(shift));
        }
        System.out.println("Target endtime is "+target.toString());
        File file = new File("counter.txt");
        System.out.println("file would be created counter.txt. Please don't close the terminal");
        LocalDateTime now = LocalDateTime.now();
        while(target.isAfter(now)){
            now = LocalDateTime.now();
            long sec = now.until(target, ChronoUnit.SECONDS);
            FileUtils.write(file,toMin(sec)+":"+toSec(sec),"UTF-8");
            try {
                Thread.sleep(1000);
            } catch (Exception e){}
        }
        System.out.println("Countdown completed...");



    }

    private static String toMin(long sec) {
        long min = sec/60;
        if (String.valueOf(min).length()==1) {
            return "0"+String.valueOf(min);
        }
        return String.valueOf(min);
    }

    private static String toSec(long sec){
        long output = (sec%60);
        if (String.valueOf(output).length()==1) {
            return "0"+String.valueOf(output);
        }
        return String.valueOf(output);
    }
}
