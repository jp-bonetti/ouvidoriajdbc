package entities;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import db.DB;

public class Manifestacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id_manifestacao;
	private Integer id_pessoa;
	private String tipo;
	private String texto;
	
	public Manifestacao() {
		
	}
	
	public Manifestacao(Integer id_manifestacao, Integer id_pessoa, String tipo, String texto) {
		this.id_manifestacao = id_manifestacao;
		this.id_pessoa = id_pessoa;
		this.tipo = tipo;
		this.texto = texto;
	}

	public Integer getId_manifestacao() {
		return id_manifestacao;
	}

	public void setId_manifestacao(Integer id_manifestacao) {
		this.id_manifestacao = id_manifestacao;
	}

	public Integer getId_pessoa() {
		return id_pessoa;
	}

	public void setId_pessoa(Integer id_pessoa) {
		this.id_pessoa = id_pessoa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_pessoa, texto, tipo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manifestacao other = (Manifestacao) obj;
		return Objects.equals(id_pessoa, other.id_pessoa) && Objects.equals(texto, other.texto)
				&& Objects.equals(tipo, other.tipo);
	}

	public void inserirManifestacao(Connection conn, int id) {

		try {
			
			PreparedStatement ps = conn.prepareStatement("INSERT INTO MANIFESTACAO (TEXTO,TIPO,ID_PESSOA) VALUES (?,?,?)");
			
			ps.setString(1, getTexto());
			ps.setString(2, getTipo());
			ps.setInt(3, id);
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void deletarManifestacao(Connection conn, int id){
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM MANIFESTACAO WHERE ID_MANIFESTACAO = ?");
			
			ps.setInt(1, id);
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deletarTodasManifestacaoes(Connection conn) {
		
		try {
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM MANIFESTACAO");
			
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void listarManifestacoes(Connection conn) {
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			conn = DB.getConnection();
	
			st = conn.createStatement();
			
			rs = st.executeQuery("SELECT manifestacao.*,pessoa.Nome as Nome\r\n"
					+ "FROM manifestacao INNER JOIN pessoa\r\n"
					+ "ON manifestacao.ID_PESSOA = pessoa.ID\r\n"
					+ "ORDER BY ID");
			
			if (!rs.isBeforeFirst()) {
                System.out.println("Nenhuma manifestacao cadastrada!");
            }
			
			while(rs.next()) {
				String obj = "ID: " + rs.getInt("ID_MANIFESTACAO") + " | Nome: " +rs.getString("NOME") + " | Tipo : " + rs.getString("TIPO") +" | Descricao: " + rs.getString("TEXTO");
				System.out.println(obj);
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
}
