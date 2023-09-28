package rainDataHW;
import java.util.ArrayList;
import java.text.DecimalFormat;
public class rainData {

    private String month_;
    private Integer year_;
    private Double rainfall_;

    public rainData() {
        month_ = "NULL";
        year_ = 0000;
        rainfall_ = 0.0;

    }
    public rainData(String s, Integer i, Double d) {
        month_ = s;
        year_ = i;
        rainfall_ = d;

    }
    public String getMonth() {
        return month_;
    }
    public int getYear() {
        return year_;
    }
    public Double getRainfall() {
        return rainfall_;
    }
    public static void printArray(ArrayList<rainData> a1){
        for(int i = 0; i < a1.size(); i++) {
            System.out.print(a1.get(i).getMonth()+" ");
            System.out.print(a1.get(i).getYear()+" ");
            System.out.print(a1.get(i).getRainfall()+"\n");
        }
    }
    public static String totalRainfall(ArrayList<rainData> a1) {
        double totalRain = 0;
        double count=0;
        for (int i = 0; i < a1.size(); i++) {
            totalRain += a1.get(i).getRainfall();
            count++;
        }
        return new DecimalFormat("#.##").format(totalRain/count);
    }
    public static String monthlyRainfall(ArrayList<rainData> a1, int index){
        double totalRain=0;
        double count=0;
        for (int i = index; i < a1.size(); i += 12) {
            totalRain += a1.get(i).getRainfall();
            count++;
        }
        return new DecimalFormat("#.##").format(totalRain/count);
    }

}




