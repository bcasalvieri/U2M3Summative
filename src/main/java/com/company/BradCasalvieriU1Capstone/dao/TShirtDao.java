package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.TShirt;

import java.util.List;

public interface TShirtDao {
    TShirt addTShirt(TShirt tShirt);
    TShirt getTShirtById(int id);
    List<TShirt> getAllTShirts();
    void updateTShirt(TShirt tShirt);
    void deleteTShirt(int id);
    List<TShirt> getTShirtsByColor(String color);
    List<TShirt> getTShirtsBySize(String size);
}
