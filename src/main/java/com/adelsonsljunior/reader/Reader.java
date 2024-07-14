package com.adelsonsljunior.reader;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.entities.HygieneProduct;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reader {

    public static Clothing readClothing() {

        String path = "csv/donate_clothing.csv";

        Clothing clothing = null;

        try {

            java.io.Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, format);

            for (CSVRecord csvRecord : parser) {
                String description = csvRecord.get(0);
                String gender = csvRecord.get(1);
                String size = csvRecord.get(2);
                clothing = new Clothing(description, gender, size);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return clothing;

    }

    public static HygieneProduct readHygieneProduct() {

        String path = "csv/donate_hygiene_product.csv";

        HygieneProduct hygieneProduct = null;

        try {

            java.io.Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, format);

            for (CSVRecord csvRecord : parser) {
                String description = csvRecord.get(0);
                String type = csvRecord.get(1);
                hygieneProduct = new HygieneProduct(description, type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hygieneProduct;

    }

    public static Food readFood() {

        String path = "csv/donate_food.csv";

        Food food = null;

        try {

            java.io.Reader reader = Files.newBufferedReader(Paths.get(path));
            CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, format);

            for (CSVRecord csvRecord : parser) {
                String description = csvRecord.get(0);
                String measure = csvRecord.get(1);
                int quantity = Integer.parseInt(csvRecord.get(2));
                String validityString = csvRecord.get(3);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate validity = LocalDate.parse(validityString, formatter);

                food = new Food(description, quantity, measure, validity);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return food;

    }


}
