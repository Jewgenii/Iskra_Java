/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Naimesql;
import model.JsonToPagination;

/**
 *
 * @author u27brvz14
 */
public class NaimesqlDAO extends DAO {

    private PreparedStatement preparedStatement = null;

    @Override
    public List<Object> select(Object... obj) {
        List<Object> naimesql = new ArrayList<>();
        Map<String, String> map = this.getParams(obj);
        // must have filters list

        try {
            String query
                    = new StringBuilder("select (osd_c||osd_r) as osd,naim,nizv from clippersql.naimesql")
                            .append(" limit ")
                            .append(" ? ")
                            .append(" offset ")
                            .append(" ? ")
                            .toString();

            preparedStatement = connection.prepareStatement(query);

            int limit = Integer.parseInt(map.get("limit"));
            int offset = Integer.parseInt(map.get("offset"));

            preparedStatement.setInt(1, limit);
            preparedStatement.setInt(2, offset < 0 ? 0 : offset);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Naimesql n = new Naimesql();

                n.setNaim(rs.getString("naim"));
                n.setNizv(rs.getString("nizv"));
                n.setOsd(rs.getString("osd"));
                naimesql.add(n);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return naimesql;
    }

    public List<String> getDistinctNaim(String term) {
        List<String> lst = new ArrayList<>(10);
        String query
                = new StringBuilder("select distinct naim from clippersql.naimesql where lower(naim) like lower(?) order by naim limit 10")
                        .toString();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, term + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("naim"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lst;
    }

    public List<String> getDistinctOSD(String term) {
        List<String> lst = new ArrayList<>(10);
        String query
                = new StringBuilder("select distinct (osd_c||osd_r) as osd from clippersql.naimesql where lower(osd_c||osd_r) like lower(?) order by (osd_c||osd_r) limit 10")
                        .toString();
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, term + "%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                lst.add(rs.getString("osd"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return lst;
    }
}