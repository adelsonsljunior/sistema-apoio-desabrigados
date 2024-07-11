package com.adelsonsljunior.reader;

import com.adelsonsljunior.core.domain.entities.DistributionCenter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;


import java.nio.file.Files;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {

    private static final String SAMPLE_CSV_FILE_PATH = "database/seed.csv";

    public static List<DistributionCenter> readerDistributionCenters() {

        List<DistributionCenter> distributionCenters = new ArrayList<>();

        try {

            java.io.Reader reader = Files.newBufferedReader(Paths.get(SAMPLE_CSV_FILE_PATH));
            CSVFormat format = CSVFormat.Builder.create(CSVFormat.DEFAULT)
                    .setDelimiter(';')
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .build();
            CSVParser parser = new CSVParser(reader, format);

            for (CSVRecord csvRecord : parser) {
                String name = csvRecord.get(0);
                String number = csvRecord.get(1);
                String postalCode = csvRecord.get(2);
                DistributionCenter distributionCenter = new DistributionCenter(name, number, postalCode);
                distributionCenters.add(distributionCenter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return distributionCenters;
    }

}
