package com.adelsonsljunior.infra.adapters.repositories;

import com.adelsonsljunior.core.domain.entities.Clothing;
import com.adelsonsljunior.core.domain.ports.repositories.IClothingRepository;
import com.adelsonsljunior.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClothingRepository implements IClothingRepository {
    @Override
    public void create(Clothing clothing, int centerId) {
        try {
            Connection conn = Postgres.getConnection();
            String sql = "INSERT INTO clothes(description, gender, size) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, clothing.getDescription());
            pst.setString(2, clothing.getGender());
            pst.setString(3, clothing.getSize());
            pst.execute();

            ResultSet lastClothing = pst.getResultSet();
            int clothingId = 0;
            if (lastClothing.next()) {
                clothingId = lastClothing.getInt("id");
            }

            sql = "INSERT INTO clothes_stock(center_id, clothing_id) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, centerId);
            pst.setInt(2, clothingId);
            pst.execute();

            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Clothing> findAllByCenterId(int centerId) {
        List<Clothing> clothes = new ArrayList<>();

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT c.id, c.description, c.gender, c.size
                    FROM clothes AS c
                    INNER JOIN clothes_stock AS cs ON c.id = cs.clothing_id
                    WHERE cs.center_id = ?
                    """;
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                Clothing clothing = new Clothing();
                clothing.setId(rs.getLong("id"));
                clothing.setDescription(rs.getString("description"));
                clothing.setGender(rs.getString("gender"));
                clothing.setSize(rs.getString("size"));
                clothes.add(clothing);
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clothes;
    }

    @Override
    public int countByCenterId(int centerId) {

        int count = 0;

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT COUNT(*) AS total_clothes
                    FROM clothes_stock
                    WHERE center_id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total_clothes");
            }

            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public Clothing findById(Long clothingId) {
        Clothing clothing = null;
        try {

            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT *
                    FROM clothes
                    WHERE id = ?""";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, clothingId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                clothing = new Clothing();
                clothing.setId(rs.getLong("id"));
                clothing.setDescription(rs.getString("description"));
                clothing.setGender(rs.getString("gender"));
                clothing.setSize(rs.getString("size"));
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return clothing;
    }

    @Override
    public void update(Clothing clothing, Long clothingId) {
        try {

            Connection conn = Postgres.getConnection();
            String sql = """
                    UPDATE clothes
                    SET description = ?, gender = ?, size = ?
                    WHERE id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, clothing.getDescription());
            pst.setString(2, clothing.getGender());
            pst.setString(3, clothing.getSize());
            pst.setLong(4, clothingId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long clothingId) {

        try {

            Connection conn = Postgres.getConnection();
            String sql = "DELETE FROM clothes_stock WHERE clothing_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, clothingId);
            pst.execute();

            sql = "DELETE FROM clothes WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, clothingId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
