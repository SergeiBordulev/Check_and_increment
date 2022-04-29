import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * This program reads the last record from a text file.
 * Increments the value based on the difference between the last recording time and the current time.
 * 1 hour = 2 (kw for example)
 */

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        int loadedNumber;
        long wattPerHour = 2;

        FileReader fileReader = new FileReader("memory.log");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String s = bufferedReader.readLine();

        int delimiter = s.indexOf("|");
        int stringLength = s.length();

        loadedNumber = Integer.parseInt(s.substring(0, delimiter - 1));
        String loadedDate = s.substring(delimiter + 2, stringLength);
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        long timeUp = format.parse(loadedDate).getTime();
        long currentDate = System.currentTimeMillis();
        long diff = currentDate - timeUp;
        long diffAllHours = diff / (60 * 60 * 1000);

        if (diffAllHours < 1) {
            System.out.println(loadedNumber);
        } else {
            FileWriter fileWriter = new FileWriter("memory.log");
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            long resultNumber = loadedNumber + (diffAllHours * wattPerHour);
            System.out.println(resultNumber);

            bufferedWriter.write( resultNumber + " | " + format.format(currentDate));
            bufferedWriter.close();
        }

        bufferedReader.close();
    }
}
