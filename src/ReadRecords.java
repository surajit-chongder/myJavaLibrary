import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReadRecords {
    private final String fileName;

    public ReadRecords(String file) {
        this.fileName = file;
    }

    public List readGuest() throws IOException {
        List<ArrayList<String>> list = new ArrayList<>();
        String eachLine;
        BufferedReader readBuffer = new BufferedReader(new FileReader(this.fileName));
        while ((eachLine = readBuffer.readLine()) != null)
            list.add(guestList(eachLine));
        return list;
    }

    private ArrayList<String> guestList(String data) {
        ArrayList<String> eachData = new ArrayList<>();
            String[] splitData = data.split(",");
            Collections.addAll(eachData, splitData);
        return eachData;
    }
}
