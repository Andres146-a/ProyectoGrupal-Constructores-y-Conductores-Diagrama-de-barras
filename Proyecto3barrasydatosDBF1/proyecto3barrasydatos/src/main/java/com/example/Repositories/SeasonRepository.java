package com.example.Repositories;

import com.example.Models.Season;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeasonRepository {

    private static final String URL = "jdbc:postgresql://localhost:5432/proyectof1db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Admi1234";

    public List<Season> getAllSeasons() {
        List<Season> seasons = new ArrayList<>();
        String query = "SELECT year FROM seasons";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                seasons.add(new Season(rs.getInt("year")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return seasons;
    }
}
