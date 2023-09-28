import java.io.*;
import java.util.Scanner;
import java.io.FileWriter;
import rainDataHW.rainData;
import java.util.ArrayList;


import static rainDataHW.rainData.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String title = null;
        ArrayList<rainData> a = new ArrayList<rainData>();

//====================READ IN FILE=====================//
        File file = new File("rainfall_data.txt");
        Scanner sc = new Scanner(file);

/* While there continues to be text, it continues through this loop. It stores each line as three strings,
splitting at a space, tab, or enter. This then stores the data in the rainData class. */
        while (sc.hasNext()) {
            String[] line = sc.nextLine().split("\\s+");

            if (line.length == 1) {
                title = line[0];
            }
            if (line.length > 2) {
                String s1 = line[0];
                int n1 = Integer.parseInt(line[1]);
                double d1 = Double.parseDouble(line[2]);
                a.add(new rainData(s1, n1, d1));
            }
        }
     
//====================WRITE OUT FILE=====================//
            FileWriter filewrite = new FileWriter("rainfall_results.txt");
            filewrite.write("The overall average rainfall amount in "+title+" is "+totalRainfall(a) + " inches.\n");
            for(int i=0; i<12; i++){
                filewrite.write("Average rainfall for "+ title +" in "+a.get(i).getMonth() +" is "+ monthlyRainfall(a, i) + " inches.\n");
            }
            filewrite.close();
        }
    }