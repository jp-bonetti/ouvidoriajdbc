package entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import db.DB;

public class Pessoa implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	private String email;
	private String celular;
	
	public Pessoa(Integer id, String nome, String email, String celular) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(id, other.id);
	}
	
	public void inserirPessoa(Connection conn) {

		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO PESSOA (NOME,EMAIL,CELULAR) VALUES (?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setString(1, this.nome);
			ps.setString(2, this.email);
			ps.setString(3, this.celular);
			
			int linhasAfetadas = ps.executeUpdate();
			
			if (linhasAfetadas > 0) {
				ResultSet generatedKeys = ps.getGeneratedKeys();
				if (generatedKeys.next()) {
					int idUsuario = generatedKeys.getInt(1);
                    setId(idUsuario);
                }
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
		
		
}
	

