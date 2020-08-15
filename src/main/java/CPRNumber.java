import java.rmi.RMISecurityManager;
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

    public CPRNumber() {
        rd = new Random();
        date = LocalDate.now();
        setCpr();
    }

    public void setCpr() {
        int daysback = rd.nextInt(36500);
        int year = 0;
        int month = 0;
        int day =  0;
        int thisYear = date.getYear();
        date = date.minusDays(daysback);
        int thatYear = date.getYear();
        int thatMonth = date.getMonthValue();
        int thatDay = date.getDayOfMonth();
        // 1940-08-24
        /*
        DateTimeFormatter df = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        //int datoToString = date.format("")
        //String datoToString = date.format(df);
        String datoToString = "1970-01-01";
        Pattern pattern = Pattern.compile("(\\d+)-(\\d+)-(\\d+)");
        Pattern numpattern = Pattern.compile("([0-9])(\\d)-(\\d)");
        Matcher matcher = pattern.matcher(datoToString);
        if (matcher.find()) {
            year = Integer.parseInt(matcher.group(1));
            month = Integer.parseInt(matcher.group(2));
            day = Integer.parseInt(matcher.group(3));
        }
        */
        String thatMonthString = String.valueOf(thatMonth);
        String thatDayString = String.valueOf(thatDay);
        if (thatYear > 1999) {
            thatYear = thatYear-2000;
        } else {
            thatYear = thatYear-1900;
        }
        String thatYearString = String.valueOf(thatYear);
        if (thatYear < 10) {
            thatYearString = "0"+thatYear;
        }
        if (thatMonth < 10) {
            thatMonthString = "0"+thatMonth;
        }
        if (thatDay < 10) {
            thatDayString = "0"+thatDay;
        }
        String birthdayString = thatDayString+thatMonthString+thatYearString;
        int birthdayInt = Integer.parseInt(String.format("%d%d%s",thatDay,thatMonth,thatYear));
        int magicNumber = rd.nextInt(998)+1;
        String magicNumberStr = "";
        if (magicNumber<10) {
            magicNumberStr = String.format("00%d",magicNumber);
        } else if(magicNumber< 100) {
            magicNumberStr = String.format("0%d",magicNumber);
        } else {
            magicNumberStr = String.format("%d",magicNumber);
        }
        //int sum = calcSum(birthdayInt);
        int sum = birthdayInt;
            while(sum > 9) {
                sum = calcSum(sum);
            }
        int rest = sum+magicNumber%11;
        int lastNum = 11-rest;
        cprnumber = birthdayString+" " + magicNumberStr+rest;
    }

    public int calcSum(int feed) {
        if (feed == 0) {
            return feed;
        } else {
            return (feed%10) + calcSum(feed/10);
        }
    }

}
