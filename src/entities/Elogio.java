package entities;

public class Elogio extends Manifestacao {

	private static final long serialVersionUID = 1L;

	public Elogio(Integer id_manifestacao, Integer id_pessoa, String texto) {
		super(id_manifestacao, id_pessoa, "Elogio", texto);
	}
	

}
