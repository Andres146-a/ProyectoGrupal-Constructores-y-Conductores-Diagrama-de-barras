package com.example.Repositories;



import com.example.DataBaseConnection;
import com.example.Models.ConstructorResult;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConstructorResultRepository {

    public List<ConstructorResult> getConstructorResultsByYear(int year) {
        List<ConstructorResult> results = new ArrayList<>();

        String sql = "SELECT\n"
                    + "    r.year,\n"
                    + "    co.name,\n"
                    + "    COUNT(CASE WHEN res.position = 1 THEN 1 END) AS wins,\n"
                    + "    SUM(res.points) AS total_points,\n"
                    + "    RANK() OVER (PARTITION BY r.year ORDER BY SUM(res.points) DESC) AS season_rank\n"
                    + "FROM\n"
                    + "    results res\n"
                    + "JOIN\n"
                    + "    races r ON res.race_id = r.race_id\n"
                    + "JOIN\n"
                    + "    constructors co ON res.constructor_id = co.constructor_id\n"
                    + "WHERE r.year = ?\n"
                    + "GROUP BY\n"
                    + "    r.year, co.constructor_id, co.name\n"
                    + "ORDER BY\n"
                    + "    r.year, season_rank;";

        try (Connection conn = DataBaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, year);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ConstructorResult result = new ConstructorResult();
                result.setName(rs.getString("name"));
                result.setWins(rs.getInt("wins"));
                result.setTotalPoints(rs.getFloat("total_points"));
                result.setSeasonRank(rs.getInt("season_rank"));
                results.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }
}
