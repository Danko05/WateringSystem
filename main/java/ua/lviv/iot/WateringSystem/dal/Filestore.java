package ua.lviv.iot.WateringSystem.dal;

import ua.lviv.iot.WateringSystem.model.Model;
import ua.lviv.iot.WateringSystem.utils.DateToday;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public abstract class Filestore<T extends Model> {
    public static final String RESULT_FOLDER = "src/main/resources/files";

    public List<T> readRecords() throws IOException {
        String fileName = RESULT_FOLDER + "/" + getRecordName() + "-" + DateToday.getDateToday();
        if (Files.exists(Paths.get(fileName))) {
            return readRecordsFrom(new File(fileName));
        }
        return new LinkedList<>();
    }


    protected List<T> readRecordsFrom(File CSV) throws IOException {
        List<T> result = new LinkedList<>();
        Scanner scanner = new Scanner(CSV, StandardCharsets.UTF_8);
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        } else {
            return result;
        }
        while (scanner.hasNextLine()) {

            List<String> values = Arrays.stream(scanner.nextLine().split(", ")).toList();
            result.add(convert(values));
        }
        return result;
    }

    public void saveRecords(List<T> records) throws IOException {
        String date = DateToday.getDateToday();
        File file = new File(RESULT_FOLDER + "/" + getRecordName() + "-" + date + ".csv");
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(records.get(0).getHeaders() + "\n");
            for (T record : records) {
                writer.write(record.toCSV() + "\n");
            }


        }

    }

    protected abstract String getRecordName();

    protected abstract T convert(List<String> values);


}
