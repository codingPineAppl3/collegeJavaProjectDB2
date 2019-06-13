package de.hda.fbi.db2.stud;

//import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Generate random time stamp
 * @version 1.01
 * @author xiaominjin
 */
public class RandomDate {
    public void setCalendar() {
     //   String testStart = "2019.01.01.10.10.00";
     //   String testEnd = "2019.01.05.23.10.00";
     //   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.HH.mm.ss");
     //   LocalDateTime testfrom = LocalDateTime.parse(testStart, formatter);
     //   LocalDateTime testto = LocalDateTime.parse(testEnd, formatter);
     //   Timestamp timefrom = Timestamp.valueOf(testfrom);
     //   Timestamp timeto = Timestamp.valueOf(testto);
      //  System.out.println("Hello World");
        //     LoadController loadcontroller = new LoadController();
        //     loadcontroller.LoadCSV();
      //  System.out.println("Generate Data");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        GenerateData generatedata = new GenerateData();
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 1);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 2);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 3);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 4);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 5);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 6);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 7);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 8);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);

        cal.set(Calendar.MONTH, 9);
        cal.set(Calendar.YEAR, 2019);
        cal.set(Calendar.DATE, 1);
        generatedata.gendata(cal);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date2 = new Date();
        System.out.println(dateFormat2.format(date2));

      //  ViewStatistics view = new ViewStatistics();
        //    view.ShowPlayer(timefrom, timeto);
        //   view.ShowGame(3);
        //    view.ShowNumberOfGame();
        //    view.SelectedCategories();
        //    PlayGame playgame = new PlayGame();
        //    playgame.fillWithData();
        System.out.println("Generation finished");
    }
}
