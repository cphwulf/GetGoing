import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CPRNumber {
    LocalDate date;
    int magicNumber;
    Random rd;
    String cprnumber;
    int[] multipliers = {4, 3, 2, 7, 6, 5, 4, 3, 2};

    public CPRNumber() {
        rd = new Random();
        date = LocalDate.now();
        setCpr();
    }

    public void setCpr() {
        // find et random tal vi kan bruge til at regne bagud
        int daysback = rd.nextInt(36500);
        int year = 0;
        int month = 0;
        int day =  0;
        int thisYear = date.getYear();

        // find fødselsdagen 2020-08-24
        date = date.minusDays(daysback);

        // fra Date til int
        int thatYear = date.getYear();
        int thatMonth = date.getMonthValue();
        int thatDay = date.getDayOfMonth();
        // 1940-08-24

        // fra intparts til Stringpsarts
        String thatMonthString = String.valueOf(thatMonth);
        String thatDayString = String.valueOf(thatDay);
        if (thatYear > 1999) {
            thatYear = thatYear-2000;
            // 2008-2000=8
        } else {
            thatYear = thatYear-1900;
            //1964-1900=64
        }
        String thatYearString = String.valueOf(thatYear);

        // fixe "0" karakteren
        if (thatYear < 10) {
            thatYearString = "0"+thatYear;
        }
        if (thatMonth < 10) {
            thatMonthString = "0"+thatMonth;
        }
        if (thatDay < 10) {
            thatDayString = "0"+thatDay;
        }

        // fra almString til cprDatoString 1940-08-24 -> 240840
        String birthdayString = thatDayString+thatMonthString+thatYearString;

        //
        int birthdayInt = Integer.parseInt(String.format("%d%d%s",thatDay,thatMonth,thatYear));
        int magicNumber = rd.nextInt(998)+1;
        int lastNumber = 0;

        String magicNumberStr = "";
        if (magicNumber<10) {
            magicNumberStr = String.format("00%d",magicNumber);
        } else if(magicNumber< 100) {
            magicNumberStr = String.format("0%d",magicNumber);
        } else {
            magicNumberStr = String.format("%d",magicNumber);
        }
      int sum = calcSum(birthdayString+magicNumberStr);
        /*
        int sum = birthdayInt;
            while(sum > 9) {
                sum = calcSum(sum);
            }
            */
        int rest = sum+magicNumber%11;
        int lastNum = 11-rest;
        cprnumber = birthdayString+" " + magicNumberStr+rest;
    }

    public int calcSum(String feed) {
        int retVal = 0;
        for(int i=0;i < multipliers.length;i++) {
            retVal += multipliers[i]*(int)(feed.charAt(i));
        }
        /*
        if (feed == 0) {
            return feed;
        } else {
            return (feed%10) + calcSum(feed/10);
        }

         */
        return retVal;
    }

}
