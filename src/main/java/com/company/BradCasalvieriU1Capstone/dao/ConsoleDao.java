package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.Console;

import java.util.List;

public interface ConsoleDao {
    Console addConsole(Console console);
    Console getConsoleById(int id);
    List<Console> getAllConsoles();
    void updateConsole(Console console);
    void deleteConsole(int id);
    List<Console> getConsolesByManufacturer(String manufacturer);
}
