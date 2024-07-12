package com.adelsonsljunior.infra.adapters.repositories;

import com.adelsonsljunior.core.domain.entities.Food;
import com.adelsonsljunior.core.domain.ports.repositories.IFoodRepository;
import com.adelsonsljunior.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodRepository implements IFoodRepository {
    @Override
    public void create(Food food, int centerId) {
        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    INSERT INTO foods (description, quantity, validity, unit_of_measurement)
                    VALUES (?, ?, ?, ?) RETURNING id""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, food.getDescription());
            pst.setInt(2, food.getQuantity());
            pst.setObject(3, food.getValidity(), Types.DATE);
            pst.setString(4, food.getUnitOfMeasurement());
            pst.execute();

            ResultSet rs = pst.getResultSet();
            int foodId = 0;
            if (rs.next()) {
                foodId = rs.getInt("id");
            }

            sql = "INSERT INTO foods_stock(center_id, food_id) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);
            pst.setInt(1, centerId);
            pst.setInt(2, foodId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Food food, Long foodId) {

        try {

            Connection conn = Postgres.getConnection();
            String sql = """
                    UPDATE foods
                    SET description = ?, quantity = ?, validity = ?, unit_of_measurement = ?
                    WHERE id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, food.getDescription());
            pst.setInt(2, food.getQuantity());
            pst.setObject(3, food.getValidity(), Types.DATE);
            pst.setString(4, food.getUnitOfMeasurement());
            pst.setLong(5, foodId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long foodId) {

        try {
            Connection conn = Postgres.getConnection();
            String sql = "DELETE FROM foods_stock WHERE food_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, foodId);
            pst.execute();

            sql = "DELETE FROM foods WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, foodId);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Food> findAllByCenterId(int centerId) {

        List<Food> foods = new ArrayList<>();

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT f.id, f.description, f.quantity, f.unit_of_measurement, f.validity
                    FROM foods f 
                    INNER JOIN foods_stock fs ON f.id = fs.food_id
                    WHERE fs.center_id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getLong("id"));
                food.setDescription(rs.getString("description"));
                food.setQuantity(rs.getInt("quantity"));
                food.setUnitOfMeasurement(rs.getString("unit_of_measurement"));
                food.setValidity(rs.getObject("validity", LocalDate.class));
                foods.add(food);
            }

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return foods;
    }

    @Override
    public Food findById(Long id) {

        Food food = null;

        try {
            Connection conn = Postgres.getConnection();
            String sql = "SELECT * FROM foods WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                food = new Food();
                food.setId(rs.getLong("id"));
                food.setDescription(rs.getString("description"));
                food.setQuantity(rs.getInt("quantity"));
                food.setUnitOfMeasurement(rs.getString("unit_of_measurement"));
                food.setValidity(rs.getObject("validity", LocalDate.class));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return food;
    }

    @Override
    public int countByCenterId(int centerId) {

        int count = 0;

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT COUNT(*) AS total_foods
                    FROM foods_stock
                    WHERE center_id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total_foods");
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
