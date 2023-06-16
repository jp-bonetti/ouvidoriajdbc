package application;

import java.sql.Connection;
import java.util.Locale;
import java.util.Scanner;

import db.DB;
import entities.Elogio;
import entities.Manifestacao;
import entities.Pessoa;
import entities.Reclamacao;
import entities.Sugestao;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = DB.getConnection();

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		String marcadores = "======================================================";
		
		System.out.println(marcadores);
		System.out.println("|          Bem vindo a Ouvidoria Unifacisa!          |");
		System.out.println(marcadores);
		System.out.println();
		
		System.out.print("Informe o seu nome: ");
		String nome = sc.nextLine();
		System.out.print("Informe o seu e-mail: ");
		String email = sc.nextLine();
		System.out.print("Informe o seu número de celular: ");
		String celular = sc.nextLine();
		
		Pessoa pessoa = new Pessoa(null, nome, email, celular);
		pessoa.inserirPessoa(conn);
		
		int menu = 0;
		
		while (menu != 3) {
			
			System.out.println();
			System.out.println(marcadores);
			System.out.println("           Informe qual opcao voce deseja: ");
			System.out.println(marcadores);
			System.out.println();
			System.out.println("1 - Inserir manifestacao | 2 - Listar manifestacao | 3 - Sair");
			System.out.println();
			System.out.print("Sua opcao: ");
			menu = sc.nextInt();
			
			switch(menu) {
			
			case 1:{
				
				System.out.println();
				System.out.println("Você escolheu a opcao inserir manifestacao");
				System.out.println();
				System.out.println(marcadores);
				System.out.println("Informe qual tipo de manifestacao voce deseja inserir: ");
				System.out.println(marcadores);
				System.out.println();
				System.out.println("1 - Reclamacao | 2 - Elogio | 3 - Sugestao");
				System.out.println();
				System.out.print("Sua opcao: ");
				int tipo = sc.nextInt();
				
				switch(tipo) {
				case 1:{
					System.out.println();
					System.out.println("Voce escolheu a opcao inserir reclamacao");
					System.out.println();
					System.out.print("Informe a sua reclamacao: ");
					sc.nextLine();
					String texto = sc.nextLine();
					Reclamacao reclamacao = new Reclamacao(null,pessoa.getId(),texto);
					reclamacao.inserirManifestacao(conn,pessoa.getId());
					System.out.println();
					System.out.println("Reclamacao cadastrada com sucesso!");
				}
				break;
				case 2:{
					System.out.println();
					System.out.println("Voce escolheu a opcao inserir elogio");
					System.out.println();
					System.out.print("Informe o seu elogio: ");
					sc.nextLine();
					String texto = sc.nextLine();
					Elogio elogio = new Elogio(null,pessoa.getId(),texto);
					elogio.inserirManifestacao(conn,pessoa.getId());
					System.out.println();
					System.out.println("Elogio cadastrado com sucesso!");
				}
				break;
				case 3:{
					System.out.println();
					System.out.println("Voce escolheu a opcao inserir sugestao");
					System.out.println();
					System.out.print("Informe a sua sugestao: ");
					sc.nextLine();
					String texto = sc.nextLine();
					Sugestao sugestao = new Sugestao(null,pessoa.getId(),texto);
					sugestao.inserirManifestacao(conn,pessoa.getId());
					System.out.println();
					System.out.println("Sugestao cadastrada com sucesso!");
				}
				break;
				}
			}
			break;
			
			case 2: {
				System.out.println();
				System.out.println("Voce escolheu a opcao listar manifestacoes: ");
				System.out.println();
				
				Manifestacao manifestacao = new Manifestacao();
				manifestacao.listarManifestacoes(conn);
				
			}
			break;
			}	
		}
		
		System.out.println();
		System.out.println(marcadores);
		System.out.println("  Muito obrigado por usar nossa ouvidoria! Ate breve.");
		System.out.println(marcadores);
		
		
		DB.closeConnection();
		sc.close();
		
		
		

	}

}
