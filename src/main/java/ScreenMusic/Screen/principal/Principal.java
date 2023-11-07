package ScreenMusic.Screen.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import ScreenMusic.Screen.model.Artista;
import ScreenMusic.Screen.model.Musica;
import ScreenMusic.Screen.repository.ArtistaRepository;
import ScreenMusic.Screen.service.ConsultaChatGPT;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ArtistaRepository repository;

    public Principal(ArtistaRepository repository){
        this.repository = repository;
    }
    
    public void menuPrincipal(){

        int valor = 0;
        do {
        System.out.println("----- Menu Principal -----");
        System.out.println("[1] - Cadastrar Artista / Banda / Dupla");
        System.out.println("[2] - Cadastrar Musica");
        System.out.println("[3] - Listagem Artistas");
        System.out.println("[4] - Listagem Musicas");
        System.out.println("[5] - Pesquisar sobre o artista");
        System.out.println("[0] - Sair");
        System.out.print("Digite escolha: ");
        valor = teclado.nextInt();
        teclado.nextLine();
        switch (valor) {
            case 1:
                cadastrarArtista();
                break;
            case 2:
                cadastrarMusica();
                break;
            case 3:
                listagemArtistas();
                break;
            case 4:
                listagemMusicas();
                break;
            case 5:
                pesquisaArtistas();
                break;
        }
    } while(valor != 0);
    }


    
    private void cadastrarArtista() {
        String tipo = "";
        String nomeArtista;
        System.out.println("Tipos: ");
        System.out.println("[1] - Solo\n[2] - Banda\n[3] - Dupla");
        System.out.print("Escolha: ");
        var tipoEscolha = teclado.nextInt();
        teclado.nextLine();
        switch (tipoEscolha) {
            case 1:
            tipo = "SOLO";
            break;
            case 2:
            tipo = "BANDA";
            break;
            case 3:
            tipo = "DUPLA";
            break;
        }
        System.out.print("Nome: ");
        nomeArtista = teclado.nextLine();
        Artista artista = new Artista(nomeArtista, tipo);
        repository.save(artista);
    }
    private void cadastrarMusica(){
        listagemArtistas();
        System.out.println("Digite o codigo do Artista");
        var codigo = teclado.nextInt();
        teclado.nextLine();
        Optional<Artista> artista = repository.buscarArtista(codigo);
        if (artista.isPresent()){
            System.out.println("----------------------------");
            var artistaBuscado = artista.get();
            List<Musica> musicas = new ArrayList<>();
            
            System.out.println("Digite o nome dá Musica: ");
            String nomeMusica = teclado.nextLine();
            
            Musica musica = new Musica(nomeMusica, artistaBuscado);
            musicas.add(musica);
            
            artistaBuscado.setMusicas(musicas);
            repository.save(artistaBuscado);
            
        } else if( artista.isEmpty()){
            System.out.println("Artista não encontrado");
        }
    }
    
    private void listagemArtistas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach( a -> System.out.println(a.getId() + "-" + a.getNome()));
    }
    
    private void listagemMusicas(){
        listagemArtistas();
        System.out.print("Codigo artista: ");
        var codigo = teclado.nextInt();
        teclado.nextLine();
        Optional<Artista> artistaMusicas = repository.buscarArtista(codigo);
        if (artistaMusicas.isPresent()){
            var artistaBuscado = artistaMusicas.get();
            System.out.println(artistaBuscado.getNome());
            artistaBuscado.getMusicas().forEach(System.out::println);
        } else if (artistaMusicas.isEmpty()){
            System.out.println("Artista não encontrado");
        }
        
    }
    
    private void pesquisaArtistas() {
        System.out.print("Nome do Artista / Banda / Dupla: ");
        var nome = teclado.nextLine();
        var resultado = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resultado);
    }
}
