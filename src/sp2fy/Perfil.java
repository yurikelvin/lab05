package sp2fy;

/** 
 * Código desenvolvido para o lab05 de LP2-computacao@ufcg 2016.2
 * Yuri Silva - 116110057
 */


import exception.ValidacaoException;
import validacao.Validacao;

public class Perfil {

	private Musiteca minhaMusiteca;
	private String nomeUsuario;
	private Validacao minhaValidacao;
	
	public Perfil(String nomeUsuario) throws ValidacaoException{
		this.minhaValidacao = new Validacao();
		minhaValidacao.validaTitulo(nomeUsuario, "Nome de usuario invalido");
		
		
		this.nomeUsuario = nomeUsuario;
		this.minhaMusiteca = new Musiteca();
	}
	
	public String getNomeUsuario() {
		return this.nomeUsuario;
	}

	public boolean adicionaAlbum(Album album) throws Exception {
		return minhaMusiteca.adicionaAlbum(album);
	}
	
	public boolean adicionaAlbumFavorito(Album album) throws Exception{
		return minhaMusiteca.adicionaAlbumFavorito(album);
	}
	
	public boolean adicionaPlaylist(String nomePlaylist, String nomeAlbum, int faixa) throws Exception{
		return minhaMusiteca.adicionaPlaylist(nomePlaylist, nomeAlbum, faixa);
	}
	
	public boolean removeAlbum(String titulo) throws Exception{
		return minhaMusiteca.removeAlbum(titulo);
	}
	
	public boolean removeAlbumFavorito(String titulo) throws Exception {
		return minhaMusiteca.removeAlbumFavorito(titulo);
	}
	
	public boolean removePlaylist(String titulo) throws Exception {
		return minhaMusiteca.removePlaylist(titulo);
	}
	
	public boolean pesquisaAlbum(String nome) throws Exception {
		return minhaMusiteca.pesquisaAlbum(nome);
	}
	
	public boolean pesquisaAlbumFavorito(String nome) throws Exception {
		return minhaMusiteca.pesquisaAlbumFavorito(nome);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.getNomeUsuario() == null) ? 0 : this.getNomeUsuario().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		if (this.getNomeUsuario() == null) {
			if (other.getNomeUsuario() != null)
				return false;
		} else if (!this.getNomeUsuario().equals(other.getNomeUsuario()))
			return false;
		return true;
	}
	
	@Override
	public String toString() { // baseado no nome e na toString de musiteca
		return "Usuario: " + this.getNomeUsuario() + System.lineSeparator() + minhaMusiteca + "Fim do perfil de " + this.getNomeUsuario();
	}

}
