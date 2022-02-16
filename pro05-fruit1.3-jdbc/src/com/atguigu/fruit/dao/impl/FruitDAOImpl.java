package com.atguigu.fruit.dao.impl;

import com.atguigu.fruit.dao.FruitDAO;
import com.atguigu.fruit.dao.base.BaseDAO;
import com.atguigu.fruit.pojo.Fruit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        String sql = "select * from t_fruit";
        return executeQuery(sql);
    }

    @Override
    public boolean addFruit(Fruit fruit) {
        // insert语句返回的是自增列的值，而不是影响行数
        String sql = "insert into t_fruit values(0,?,?,?,?)";
        return executeUpdate(sql, fruit.getFname(), fruit.getPrice(), fruit.getFcount(), fruit.getRemark()) > 0;
    }

    @Override
    public boolean updateFruit(Fruit fruit) {
        String sql = "update t_fruit set fcount = ? where fid = ?";
        return executeUpdate(sql, fruit.getFcount(), fruit.getFid()) > 0;
    }

    @Override
    public Fruit getFruitByFname(String fname) {
        String sql = "select * from t_fruit where fname like ?";
        return load(sql, fname);
    }

    @Override
    public boolean delFruit(String fname) {
        String sql = "delete from t_fruit where fname like ?";
        return executeUpdate(sql, fname) > 0;
    }
}
