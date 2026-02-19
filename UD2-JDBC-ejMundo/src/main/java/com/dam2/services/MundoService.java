package com.dam2.services;

import java.util.List;
import com.dam2.dao.MundoDao;
import com.dam2.model.Heroe;

public class MundoService {
    
    private MundoDao mundoDao;

    public MundoService(MundoDao mundoDao) {
        this.mundoDao = mundoDao;
    }
    
    public void reclutarHeroe(Heroe heroe) {
        mundoDao.reclutarHeroe(heroe);
    }
    
    public List<Heroe> mostrarHeroesGremio(String nombreGremio) {
        return mundoDao.mostraHeroesPorGremio(nombreGremio);
    }
    
    public void subirNiverGremio(int gremioId, int nivelesSubir) {
        mundoDao.subirNivelGremio(gremioId, nivelesSubir);
    }
    
    public void disolverGremio(int gremioId) {
        mundoDao.disolverGremio(gremioId);
    }
}