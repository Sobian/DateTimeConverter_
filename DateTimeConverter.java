import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeConverter extends JPanel {

    JTextField timeH;
    JTextField timeM;
    JTextField dateTimeTxtField;
    JTextField shiftYears;
    JTextField shiftMonths;
    JTextField shiftDays;

    JLabel timeFields;
    JLabel dateFields;

    JButton timeIncrementButton;
    JButton timeDiffButton;
    JButton dateConvertButton;
    JButton dateTimeIncrDiffButton;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 400);
    }

    public DateTimeConverter() {
        setLayout(null);
        textFields();
        buttons();
        labels();
        timeIncrementActionListener();
        timeDifferenceActionListener();
        cDateActionListener();
        dateTimeActionListener();

    }

    // GUI methods -------------------------

    public void textFields() {
        timeH = new JTextField();
        timeH.setBounds(100, 60, 50, 20);
        add(timeH);

        timeM = new JTextField();
        timeM.setBounds(150, 60, 50, 20);
        add(timeM);

        dateTimeTxtField = new JTextField();
        dateTimeTxtField.setBounds(300, 60, 100, 20);
        add(dateTimeTxtField);

        shiftYears = new JTextField();
        shiftYears.setBounds(300, 100, 40, 20);
        add(shiftYears);

        shiftMonths = new JTextField();
        shiftMonths.setBounds(340, 100, 30, 20);
        add(shiftMonths);

        shiftDays = new JTextField();
        shiftDays.setBounds(370, 100, 30, 20);
        add(shiftDays);
    }

    public void buttons() {
        timeIncrementButton = new JButton("count increment");
        timeIncrementButton.setBounds(100, 200, 120, 20);
        add(timeIncrementButton);

        timeDiffButton = new JButton("count difference");
        timeDiffButton.setBounds(100, 230, 120, 20);
        add(timeDiffButton);

        dateConvertButton = new JButton("count days");
        dateConvertButton.setBounds(320, 200, 100, 20);
        add(dateConvertButton);

        dateTimeIncrDiffButton = new JButton("count dateTime");
        dateTimeIncrDiffButton.setBounds(300, 300, 120, 20);
        add(dateTimeIncrDiffButton);
    }

    public void labels() {
        timeFields = new JLabel("provide time in hh mm format");
        timeFields.setBounds(70, 30, 250, 20);
        add(timeFields);

        dateFields = new JLabel("provide date in yyyy-mm-dd format");
        dateFields.setBounds(300, 30, 250, 20);
        add(dateFields);
    }

    public void timeIncrementActionListener() {
        timeIncrementButton.addActionListener(e -> System.out.println(timeIncrement()));
    }

    public void timeDifferenceActionListener() {
        timeDiffButton.addActionListener(e -> System.out.println(timeDifference()));
    }

    public void cDateActionListener() {
        dateConvertButton.addActionListener(e -> System.out.println(countDate()));
    }

    public void dateTimeActionListener() {
        dateTimeIncrDiffButton.addActionListener(e -> System.out.println("increment: "+countDateTimeIncr()+"\n"+
                "difference: "+countDateTimeDiff()));
    }


    // TimeConversion methods -------------------------


    public String readDateFromUser() {

        String dateStr = dateTimeTxtField.getText();
        System.out.println("input readDate: " + dateStr);
        return dateStr;
    }

    public LocalTime timeDifference() {
        LocalTime currentTime = LocalTime.now();
        long diffHours = Long.parseLong(timeH.getText());
        long diffMins = Long.parseLong(timeM.getText());
        return currentTime.minusHours(diffHours).minusMinutes(diffMins);
    }

    public LocalTime timeIncrement() {
        LocalTime currentTime = LocalTime.now();
        long incHours = Long.parseLong(timeH.getText());
        long incMins = Long.parseLong(timeM.getText());
        return currentTime.plusHours(incHours).plusMinutes(incMins);
    }

    public LocalDate countDate() {
        String tempDate = readDateFromUser();
        LocalDate userDate = LocalDate.parse(tempDate, DateTimeFormatter.ISO_LOCAL_DATE);
        long longYears, longMonths, longDays;

        if (shiftYears.getText().equals(""))
            longYears = 0;
        else
            longYears = Long.parseLong(shiftYears.getText());

        if (shiftMonths.getText().equals(""))
            longMonths = 0;
        else
            longMonths = Long.parseLong(shiftMonths.getText());

        if (shiftDays.getText().equals(""))
            longDays = 0;
        else
            longDays = Long.parseLong(shiftDays.getText());

        return userDate.minusYears(longYears).minusMonths(longMonths).minusDays(longDays);
    }

    public LocalDateTime countDateTimeIncr() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        long years, months, days, hours, mins;

        years = Long.parseLong(shiftYears.getText());
        months = Long.parseLong(shiftMonths.getText());
        days = Long.parseLong(shiftDays.getText());
        hours = Long.parseLong(timeH.getText());
        mins = Long.parseLong(timeM.getText());

        return currentDateTime.plusYears(years).plusMonths(months).plusDays(days).plusHours(hours).plusMinutes(mins);
    }

    public LocalDateTime countDateTimeDiff(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        long years, months, days, hours, mins;

        years = Long.parseLong(shiftYears.getText());
        months = Long.parseLong(shiftMonths.getText());
        days = Long.parseLong(shiftDays.getText());
        hours = Long.parseLong(timeH.getText());
        mins = Long.parseLong(timeM.getText());

        return currentDateTime.minusYears(years).minusMonths(months).minusDays(days).minusHours(hours).minusMinutes(mins);
    }


    public static void main(String[] args) {

        JFrame frame = new JFrame();
        DateTimeConverter okno = new DateTimeConverter();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(okno);
        frame.setVisible(true);
        frame.pack();

    }
}
