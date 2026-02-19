package com.dam2.dao;

import java.util.List;
import com.dam2.model.Heroe;

public interface MundoDao {
    public void reclutarHeroe(Heroe heroe);
    public List<Heroe> mostraHeroesPorGremio(String nombreGremio);
    public void subirNivelGremio(int gremioId, int nivelesSubir);
    public void disolverGremio(int gremioId);
}