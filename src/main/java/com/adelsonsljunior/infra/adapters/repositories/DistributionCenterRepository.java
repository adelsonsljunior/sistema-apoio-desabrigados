package com.adelsonsljunior.infra.adapters.repositories;

import com.adelsonsljunior.core.domain.DistributionCenter;
import com.adelsonsljunior.core.domain.ports.repositories.IDistributionCenterRepository;
import com.adelsonsljunior.database.Postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DistributionCenterRepository implements IDistributionCenterRepository {

    @Override
    public void create(DistributionCenter distributionCenter) {

        try {

            Connection conn = Postgres.getConnection();
            String sql = "INSERT INTO distribution_centers(name, postal_code, number) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, distributionCenter.getName());
            pst.setString(2, distributionCenter.getPostalCode());
            pst.setInt(3, distributionCenter.getNumber());
            pst.execute();

            conn.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<DistributionCenter> findAll() {

        List<DistributionCenter> distributionCenters = new ArrayList<>();

        try {
            Connection conn = Postgres.getConnection();
            String sql = "SELECT * FROM distribution_centers";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                DistributionCenter distributionCenter = new DistributionCenter();
                distributionCenter.setName(rs.getString("id"));
                distributionCenter.setName(rs.getString("name"));
                distributionCenter.setPostalCode(rs.getString("postal_code"));
                distributionCenter.setNumber(rs.getInt("number"));
                distributionCenters.add(distributionCenter);
            }
            conn.close();
            pst.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return distributionCenters;

    }
}
