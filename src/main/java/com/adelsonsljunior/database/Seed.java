package com.adelsonsljunior.database;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.entities.HygieneProduct;
import com.adelsonsljunior.core.domain.ports.repositories.IClothingRepository;
import com.adelsonsljunior.core.domain.ports.repositories.IFoodRepository;
import com.adelsonsljunior.core.domain.ports.repositories.IHygieneProductRepository;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Seed {

    private final IClothingRepository clothingRepository;
    private final IHygieneProductRepository hygieneProductRepository;
    private final IFoodRepository foodRepository;

    public Seed(IClothingRepository clothingRepository, IHygieneProductRepository hygieneProductRepository, IFoodRepository foodRepository) {
        this.clothingRepository = clothingRepository;
        this.hygieneProductRepository = hygieneProductRepository;
        this.foodRepository = foodRepository;
    }

    private void seedCLothes() {
        String path = "csv/seed_clothes.csv";

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
                int centerId = Integer.parseInt(csvRecord.get(3));
                Clothing clothing = new Clothing(description, gender, size);
                clothingRepository.create(clothing, centerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void seedHygieneProducts() {

        String path = "csv/seed_hygiene_products.csv";

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
                int centerId = Integer.parseInt(csvRecord.get(2));
                HygieneProduct hygieneProduct = new HygieneProduct(description, type);
                hygieneProductRepository.create(hygieneProduct, centerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void seedFoods() {

        String path = "csv/seed_foods.csv";

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
                LocalDate validity = LocalDate.parse(validityString,formatter);
                int centerId = Integer.parseInt(csvRecord.get(4));

                Food food = new Food(description, quantity, measure, validity);

                foodRepository.create(food, centerId);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void up() {
        seedCLothes();
        seedHygieneProducts();
        seedFoods();
    }

}
