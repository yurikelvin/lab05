package sp2fy.musiteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import exception.ValidacaoException;
import sp2fy.album.Album;
import sp2fy.album.ArtistaComparator;
import sp2fy.album.DuracaoTotalComparator;
import sp2fy.album.NumeroDeFaixasComparator;
import sp2fy.playlists.Playlist;
import validacao.Validacao;

public class Musiteca {
	
	private HashSet<Album> meusAlbunsFavoritos;
	private ArrayList<Album> meusAlbuns;
	
	private HashMap<String, Playlist> minhasPlaylists;
	
	private Validacao minhaValidacao;
	
	public Musiteca() {
		
		meusAlbunsFavoritos = new HashSet<>();
		meusAlbuns = new ArrayList<>();
		minhasPlaylists = new HashMap<>();
		
		minhaValidacao = new Validacao();
	}
	
	public boolean adicionaAlbum(Album novoAlbum) {
		try {
			minhaValidacao.validaObjeto(novoAlbum, "Album nao pode ser nulo");
		} catch(Exception e) {
			return false;
		}
		meusAlbuns.add(novoAlbum);
		return true;
	}
	
	public boolean adicionaAlbumFavorito(Album novoAlbum) {
		try {
			minhaValidacao.validaObjeto(novoAlbum, "Album nao pode ser nulo");
		} catch(Exception e) {
			return false;
		}
		meusAlbunsFavoritos.add(novoAlbum);
		return true;
	}
	
	public boolean removeAlbum(String titulo) throws ValidacaoException {
		minhaValidacao.validaTitulo(titulo, "Nome do album invalido");
		if( pesquisaAlbum(titulo) ) {
			Iterator<Album> it = meusAlbuns.iterator();

			while(it.hasNext()) {

				Album a = it.next();
				if(a.getTitulo().equals(titulo)){
					it.remove();
					return true;
				}
			}
		} 
		return false;
	}
	
	public boolean removeAlbumFavorito(String titulo) throws ValidacaoException {
		minhaValidacao.validaTitulo(titulo, "Nome do album invalido");
		if( pesquisaAlbumFavorito(titulo) ) {
			Iterator<Album> it = meusAlbunsFavoritos.iterator();

			while(it.hasNext()) {

				Album a = it.next();
				if(a.getTitulo().equals(titulo)){
					it.remove();
					return true;
				}
			}
		} 
		return false;
	}

	public boolean pesquisaAlbum(String nome) throws ValidacaoException{
		minhaValidacao.validaTitulo(nome, "Nome do album invalido");
		
		Iterator<Album> it = meusAlbuns.iterator();
		while(it.hasNext()) {
			Album a = it.next();
			if(a.getTitulo().equals(nome)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean pesquisaAlbumFavorito(String nome) throws ValidacaoException{
		minhaValidacao.validaTitulo(nome, "Nome do album invalido");
		
		Iterator<Album> it = meusAlbunsFavoritos.iterator();
		while(it.hasNext()) {
			Album a = it.next();
			if(a.getTitulo().equals(nome)) {
				return true;
			}
		}
		
		return false;
	}
	
	public Album getAlbum(String nomeAlbum) throws ValidacaoException{
		try {
			minhaValidacao.validaTitulo(nomeAlbum, "Nome de album invalido");
		} catch(Exception e) {
			return null;
		}
		Iterator<Album> it = meusAlbuns.iterator();
		while(it.hasNext()) {
			Album album = it.next();
			if (album.getTitulo().equals(nomeAlbum)) {
				return album;
			}
		} 
		return null;
		
	}
	
	public boolean adicionaPlaylist(String nomePlaylist, String nomeAlbum, int faixa) throws Exception{
		minhaValidacao.validaTitulo(nomePlaylist, "Nome playlist invalido");
		minhaValidacao.validaTitulo(nomeAlbum, "Nome album invalido");
		minhaValidacao.validaNumero(faixa, "Faixa invalida");
	
		if(minhasPlaylists.containsKey(nomePlaylist)) {
			if (pesquisaAlbum(nomeAlbum)) {
				Playlist adicionarMusicaPlaylist = minhasPlaylists.get(nomePlaylist);
				adicionarMusicaPlaylist.adicionaMusica(getAlbum(nomeAlbum).getMusica(faixa));
				return true;
			} else {
				throw new Exception("Album nao pertence ao Perfil especificado");
			}
			
		} else {
			minhasPlaylists.put(nomePlaylist, new Playlist(nomePlaylist));
			if(pesquisaAlbum(nomeAlbum)) {
				Playlist adicionarMusicaPlaylist = minhasPlaylists.get(nomePlaylist);
				adicionarMusicaPlaylist.adicionaMusica(getAlbum(nomeAlbum).getMusica(faixa));
				return true;
			} else {
				throw new Exception("Album nao pertence ao Perfil especificado");
			}
		}


	}

	public boolean removePlaylist(String nomePlaylist) throws ValidacaoException{
		minhaValidacao.validaTitulo(nomePlaylist, "Nome de playlist invalido");
		
		if(minhasPlaylists.remove(nomePlaylist) != null) {
			return false;
		} return true;
		
	}
	
	public void ordenaAlbumWithYear() {
		Collections.sort(meusAlbuns);
	}
	
	public void ordenaAlbumWithArtist() {
		ArtistaComparator comparator = new ArtistaComparator();
		Collections.sort(meusAlbuns, comparator);
	}
	
	public void ordenaAlbumWithDuration() {
		DuracaoTotalComparator comparator = new DuracaoTotalComparator();
		Collections.sort(meusAlbuns, comparator);
	}
	
	public void ordenaAlbumWithTrackNumbers() {
		NumeroDeFaixasComparator comparator = new NumeroDeFaixasComparator();
		Collections.sort(meusAlbuns, comparator);
	}


}
