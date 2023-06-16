package entities;

public class Sugestao extends Manifestacao{

	private static final long serialVersionUID = 1L;

	public Sugestao(Integer id_manifestacao, Integer id_pessoa, String texto) {
		super(id_manifestacao, id_pessoa, "Sugestao", texto);
	}

}
