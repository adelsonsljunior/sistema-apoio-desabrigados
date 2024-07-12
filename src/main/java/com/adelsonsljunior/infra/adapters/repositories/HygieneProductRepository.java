package com.adelsonsljunior.infra.adapters.repositories;

import com.adelsonsljunior.core.domain.entities.HygieneProduct;
import com.adelsonsljunior.core.domain.ports.repositories.IHygieneProductRepository;
import com.adelsonsljunior.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HygieneProductRepository implements IHygieneProductRepository {
    @Override
    public void create(HygieneProduct hygieneProduct, int centerId) {
        try {
            Connection conn = Postgres.getConnection();
            String sql = "INSERT INTO hygiene_products(description, type) VALUES (?, ?) RETURNING id";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, hygieneProduct.getDescription());
            pst.setString(2, hygieneProduct.getType());
            pst.execute();


            ResultSet rs = pst.getResultSet();
            int hygieneProductId = 0;
            if (rs.next()) {
                hygieneProductId = rs.getInt("id");
            }

            sql = "INSERT INTO hygiene_products_stock(center_id, hygiene_product_id) VALUES (?, ?)";
            pst = conn.prepareStatement(sql);

            pst.setInt(1, centerId);
            pst.setInt(2, hygieneProductId);
            pst.execute();

            rs.close();
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HygieneProduct findById(Long productId) {
        HygieneProduct hygieneProduct = null;

        try {

            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT *
                    FROM hygiene_products
                    WHERE id = ?""";

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, productId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                hygieneProduct = new HygieneProduct();
                hygieneProduct.setId(rs.getLong("id"));
                hygieneProduct.setDescription(rs.getString("description"));
                hygieneProduct.setType(rs.getString("type"));

            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hygieneProduct;
    }

    @Override
    public List<HygieneProduct> findAllByCenterId(int centerId) {

        List<HygieneProduct> hygieneProducts = new ArrayList<>();

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT hp.id, hp.description, hp.type
                    FROM hygiene_products AS hp
                    INNER JOIN hygiene_products_stock AS hps ON hp.id = hps.hygiene_product_id
                    WHERE hps.center_id = ?
                    """;
            PreparedStatement pst = conn.prepareCall(sql);

            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                HygieneProduct hygieneProduct = new HygieneProduct();
                hygieneProduct.setId(rs.getLong("id"));
                hygieneProduct.setDescription(rs.getString("description"));
                hygieneProduct.setType(rs.getString("type"));
                hygieneProducts.add(hygieneProduct);
            }

            rs.close();
            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return hygieneProducts;
    }

    @Override
    public void update(HygieneProduct hygieneProduct, Long productId) {

        try {

            Connection conn = Postgres.getConnection();
            String sql = """
                    UPDATE hygiene_products
                    SET description = ?, type = ?
                    WHERE id = ?
                    """;
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, hygieneProduct.getDescription());
            pst.setString(2, hygieneProduct.getType());
            pst.setLong(3, productId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long productId) {
        try {

            Connection conn = Postgres.getConnection();
            String sql = "DELETE FROM hygiene_products_stock WHERE hygiene_product_id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setLong(1, productId);
            pst.execute();

            sql = "DELETE FROM hygiene_products WHERE id = ?";
            pst = conn.prepareStatement(sql);
            pst.setLong(1, productId);
            pst.execute();

            pst.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int countByCenterId(int centerId) {

        int count = 0;

        try {
            Connection conn = Postgres.getConnection();
            String sql = """
                    SELECT COUNT(*) AS total_hygiene_products
                    FROM hygiene_products_stock
                    WHERE center_id = ?""";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, centerId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total_hygiene_products");
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
