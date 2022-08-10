package com.brian.gendraapi.repository;

import com.brian.gendraapi.model.Suggestion;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.springframework.stereotype.Repository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Repository
public class SuggestionRepositoryImpl implements SuggestionRepository {

    @Override
    public List<Suggestion> searchCities(String q, double latitude, double longitude) throws FileNotFoundException {


        if (!(latitude >= -90 && latitude <= 90) || !(longitude >= -180 && longitude <= 180)) {
            List<Suggestion> empty = Collections.emptyList();
            return empty;
        }

        // URl TSV
        String url = "src/main/resources/static/cities_canada-usa.tsv";

        // Define parsers
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);

        Pattern pat = Pattern.compile("^" + q + ".*");
        try {

            // list objects filtered
            List<Suggestion> suggestions = new ArrayList<>();

            // retrieve data from tsv
            List<String[]> allRows = parser.parseAll(new FileReader(url));

            // filter by query string
            allRows.stream().filter((row) -> {
                Matcher mat = pat.matcher(row[1]);
                return mat.matches();
            }).forEach(row -> {
                double globalScore = 0.0;
                StringBuilder name = new StringBuilder();
                name.append(row[1]).append(", ").append(row[10]).append(", ").append(row[8]);


                // FIRST SCORE
                globalScore = FuzzySearch.ratio(q, row[1]);


                // parsing data coords from tsv
                double latitudeTSV = Double.parseDouble(row[4]);
                double longitudeTSV = Double.parseDouble(row[5]);

                // Helper variables
                double x_1, x_3, h1, h2;


                // LONGITUDE RATING SCORES
                x_1 = -180.00;
                x_3 = 180.00;

                h1 = Math.abs(longitude - (x_1));
                h2 = Math.abs(x_3 - (longitude));

                if (longitude > longitudeTSV) {
                    globalScore += (((Math.abs(longitudeTSV - (x_1))) * 100) / h1);
                } else if (longitude < longitudeTSV) {
                    globalScore += (((Math.abs(x_3 - (longitudeTSV))) * 100) / h2);
                } else {
                    globalScore += 100;
                }

                // LATITUDE RATING SCORES
                x_1 = -90.00;
                x_3 = 90.00;

                h1 = Math.abs(latitude - (x_1));
                h2 = Math.abs(x_3 - (latitude));

                if (latitude > latitudeTSV) {
                    globalScore += (((Math.abs(latitudeTSV - (x_1))) * 100) / h1);
                } else if (latitude < latitudeTSV) {
                    globalScore += (((Math.abs(x_3 - (latitudeTSV))) * 100) / h2);
                } else {
                    globalScore += 100;
                }


                // Set scale for decimal places
                BigDecimal bd = new BigDecimal(Double.toString((globalScore / 3) / 100));
                bd = bd.setScale(5, RoundingMode.HALF_UP);
                globalScore = bd.doubleValue();

                suggestions.add(new Suggestion(
                        name.toString(),
                        Double.parseDouble(row[4]),
                        Double.parseDouble(row[5]),
                        globalScore
                ));
            });

            return suggestions.stream()
                    .sorted(Comparator.comparingDouble(Suggestion::getScore).reversed())
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }
}
