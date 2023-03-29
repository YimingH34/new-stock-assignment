import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.lang.Math;
import java.text.DecimalFormat;

public class test {
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public static void main(String[] args) throws FileNotFoundException {
        // Date,Open,High,Low,Close,Adj Close,Volume
        ArrayList<Float> prices = new ArrayList<>();
        ArrayList<LocalDate> dates = new ArrayList<>(); // 01.03.2022 - 01.03.2023
        // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Scanner reader = new Scanner(new File("C:\\Users\\Yiming\\IdeaProjects\\untitled\\src\\V.csv")); // open scanner, only issue is that you have to change file location everytime which is quite annoying
        reader.useDelimiter("\n"); // set delimiters
        while (reader.hasNext()){ // read file line by line
            String[] line = reader.next().split(","); // write each line
            LocalDate date = LocalDate.parse(line[0], formatter); // create temp date variable
            dates.add(date); // add date to arr
            float price = Float.parseFloat(line[4].substring(1, line[4].length() - 2)); // read close price
            prices.add(price); // add price to arr
        }
        reader.close();

        Scanner input = new Scanner(System.in);
            System.out.flush();

            System.out.println("Enter buy date between 2022-03-10 and 2023-03-10 (yyyy-MM-dd)(Weekdays only: ");
            String temp = input.nextLine();

            LocalDate start = LocalDate.parse(temp, formatter);
            System.out.println("Enter sell date between 2022-03-10 and 2023-03-10 (yyyy-MM-dd) (Weekdays only): ");
            temp = input.nextLine();

            LocalDate end = LocalDate.parse(temp, formatter);
            if (end.isBefore(start) ||
                    end.isBefore(LocalDate.parse("2022-03-10", formatter)) ||
                    end.isAfter(LocalDate.parse("2023-03-10", formatter)) ||
                    start.isBefore(LocalDate.parse("2022-03-10", formatter)) ||
                    start.isAfter(LocalDate.parse("2023-03-10", formatter))
            ) return;

            int start1 = dates.indexOf(start);
            int end1 = dates.indexOf(end);

            float result = prices.get(end1) - prices.get(start1);
            df.format(result);
            if (result>0){
                System.out.printf("The stock has went up by " + result + " dollars.");
            } else if (result < 0) {
                System.out.printf("The stock has went down by " + Math.abs(result) + " dollars.");
            }
            else{
                System.out.print("The stock price did not change." );
            }
        }
    }





