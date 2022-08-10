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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Repository
public class SuggestionRepositoryImpl implements SuggestionRepository {

    @Override
    public List<Suggestion> searchCities(String q, double latitude, double longitude) {

        // check values from request
        if (!(latitude >= -90 && latitude <= 90) || !(longitude >= -180 && longitude <= 180)) {
            return Collections.emptyList();
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

                // FIRST SCORE
                globalScore = FuzzySearch.ratio(q, row[1]);

                // SECOND SCORE
                globalScore += calculateScoreL("lat", latitude, Double.parseDouble(row[4]));

                // THIRD SCORE
                globalScore += calculateScoreL("lon", longitude, Double.parseDouble(row[5]));

                // Set scale for decimal places
                BigDecimal bd = new BigDecimal(Double.toString((globalScore / 3) / 100));
                bd = bd.setScale(5, RoundingMode.HALF_UP);
                globalScore = bd.doubleValue();

                suggestions.add(new Suggestion(
                        row[1] + ", " + row[10] + ", " + row[8],
                        Double.parseDouble(row[4]),
                        Double.parseDouble(row[5]),
                        globalScore
                ));

            });

            // sort descending order
            return suggestions.stream()
                    .sorted(Comparator.comparingDouble(Suggestion::getScore).reversed())
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }


    }

    private double calculateScoreL(String type, double x_2, double x_4) {

        // Helper variables
        double h1, h2;
        double result = 0.0;

        // max values
        double x_1;
        double x_3;

        // process by type
        if (type.equals("lat")) {
            x_1 = -90.00;
            x_3 = 90.00;
        } else if (type.equals("lon")) {
            x_1 = -180.00;
            x_3 = 180.00;
        } else {
            return 0.0;
        }

        // distance between point and max-min values
        h1 = Math.abs(x_2 - (x_1));
        h2 = Math.abs(x_3 - (x_2));

        // calculate accuracy
        if (x_2 > x_4) {
            return (((Math.abs(x_4 - (x_1))) * 100) / h1);
        } else if (x_2 < x_4) {
            return (((Math.abs(x_3 - (x_4))) * 100) / h2);
        } else {
            return 100.00;
        }
    }


}
