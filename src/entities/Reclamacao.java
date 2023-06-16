package entities;

public class Reclamacao extends Manifestacao{

	private static final long serialVersionUID = 1L;

	public Reclamacao(Integer id_manifestacao, Integer id_pessoa, String texto) {
		super(id_manifestacao, id_pessoa, "Reclamacao", texto);
	}

}
