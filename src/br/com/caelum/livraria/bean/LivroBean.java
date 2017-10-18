package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class LivroBean {

	private Livro livro = new Livro();
	private Integer autorId;
	private Integer livroId;

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public void carregarLivroPorId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}

	public List<Livro> getLivros() {
		return new DAO<Livro>(Livro.class).listaTodos();
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Livro getLivro() {
		return livro;
	}

	public List<Autor> getAutoresDoLivro() {
		return livro.getAutores();
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public RedirectView formAutor() {
		System.out.println("Chamando o formulário do autor");
		return new RedirectView("autor");
	}

	public void gravarAutor() {
		System.out.println("GRAVANDO AUTOR");
		System.out.println("Id do autor: " + autorId);
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		livro.adicionaAutor(autor);
		System.out.println("Nome do autor: " + autor.getNome());
	}

	public void gravar() {
		System.out.println("GRAVANDO LIVRO");
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			// throw new RuntimeException("Livro deve ter pelo menos um
			// Autor.");
			FacesContext.getCurrentInstance().addMessage("autor",
					new FacesMessage("Livro deve ter pelo menos um Autor."));
			return;
		}

		if (livro.getId() == null)
			new DAO<Livro>(Livro.class).adiciona(this.livro);
		else
			new DAO<Livro>(Livro.class).atualiza(this.livro);

		livro = new Livro();
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public void remover(Livro livro) {
		System.out.println("Removendo livro");
		new DAO<Livro>(Livro.class).remove(livro);
	}

	public void removerAutorDoLivro(Autor autor) {
		System.out.println("Removendo autor do livro");
		this.livro.removeAutor(autor);
	}

	public void atualizar(Livro livro) {
		System.out.println("Atualizando livro");
		this.livro = livro;
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent c, Object o) throws ValidatorException {
		String valor = o.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("Deveria começar com 1"));
		}
	}

}
