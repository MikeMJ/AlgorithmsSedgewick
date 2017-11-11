import java.time.DateTimeException;



class SmartDate {
    private int month;
    private int day;
    private int year;

    public SmartDate(int year, int month, int day) {
        if (year < 0)
            throw new DateTimeException("Date not in correct format");
        else if (year == 0) this.year = 2000;
        else if (year < 10) this.year = Integer.parseInt(String.format("200%d", year));
        else if (year < 100) this.year = Integer.parseInt(String.format("20%d", year));
        else if (year < 1000)
            throw new DateTimeException("Date not in correct format");
        else this.year = year;
        if (month <= 0 || month > 12)
            throw new DateTimeException("Date not in correct format");
        else
            this.month = month;
        boolean isLeapYear = (year - 2000) % 4 == 0;
        if ((day < 0) ||
                (in(this.month, 1, 3, 5, 7, 8, 10, 12) && day > 31) ||
                (in(this.month, 4, 6, 9, 11) && day > 30) ||
                (this.month == 2 && ((isLeapYear && day > 29) || (!isLeapYear && day > 28))
                ))
            throw new DateTimeException("Date not in correct format");
        else
            this.day = day;
    }

    public String dayOfTheWeek() {
        int totalDays = ((this.year - 2000) * 365 + (this.year - 2000) / 4) + this.day+1;//+1 is for the leap year of 2000 itself
        if (this.month > 1) {
            for (int i = this.month - 1; i > 0; i--) {
                if (isLongMongh(i))
                    totalDays += 31;
                else if (isShortMonth(i))
                    totalDays += 30;
                else
                    totalDays += isLeapYear(this.year) ? 29 : 28;
            }
        }
        int dayOfWeek=(5+totalDays)%7;
        return dayOfWeekName(dayOfWeek);

    }

    private String dayOfWeekName(int dayOfWeek){
        switch (dayOfWeek){
            case 0:
                return "Sunday";
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            default:
                return null;
        }

    }

    private boolean isLeapYear(int year) {
        return (year - 2000) % 4 == 0;
    }

    private boolean isLongMongh(int month) {
        return in(month, 1, 3, 5, 7, 8, 10, 12);
    }

    private boolean isShortMonth(int month) {
        return in(month, 4, 6, 9, 11);
    }

    private <T> boolean in(T key, T... set) {
        for (T element : set) {
            if (key == element)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return String.format("%d.%d.%d", this.day, this.month, this.year);
    }
}