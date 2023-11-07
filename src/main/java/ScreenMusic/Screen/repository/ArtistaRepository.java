package ScreenMusic.Screen.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ScreenMusic.Screen.model.Artista;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
    
    @Query("SELECT a from Artista a where a.id = :codigo_id")
    Optional<Artista> buscarArtista(int codigo_id);
}
