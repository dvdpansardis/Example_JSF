package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();

	private Integer autorId;
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public void carregarAutorPorId(){
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public List<Autor> getAutores() {
		System.out.println("Buscando autores");
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public RedirectView gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(autor.getId() == null)
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		else
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		
		autor = new Autor();

		return new RedirectView("livro");
	}
	
	public void remover(Autor autor){
		System.out.println("Removendo autor");
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public void atualizar(Autor autor){
		System.out.println("Atualizando autor");
		this.autor = autor;
	}
}
