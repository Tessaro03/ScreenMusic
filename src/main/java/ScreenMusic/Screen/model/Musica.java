package ScreenMusic.Screen.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nome, Artista artista) {
        this.nome = nome;
        this.artista = artista;
    }


    public Long getId() {
        return id;
    }
    
    
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
 
    
    
    public Artista getArtistas() {
        return artista;
    }
    
    
    public void setArtistas(Artista artistas) {
        this.artista = artistas;
    }
    
        @Override
        public String toString() {
            return  "Nome - " + nome;
        }
}
