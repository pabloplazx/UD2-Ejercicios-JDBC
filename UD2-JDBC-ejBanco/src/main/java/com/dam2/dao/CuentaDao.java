package com.dam2.dao;

public interface CuentaDao {
    void retirarDinero(Integer id, Double cantidad);
    void ingresarDinero(Integer id, Double cantidad);
}