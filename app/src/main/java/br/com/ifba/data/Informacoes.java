package br.com.ifba.data;
import java.io.*;
import br.com.ifba.Enum.*;

public class Informacoes
{
	private int id;
	private String nome;
	private String email;
	private String emailRep;
	private String senha;
	private String senha2;
	private String cel1;
	private String cel2;
	private String cidade;
	private String pet;
	
	public Informacoes(String nome, String email, String emailRep , String senha, String senha2, String cel1, String cel2){
		this.nome = nome;
		this.email = email;
		this.emailRep = emailRep;
		this.senha = senha;
		this.senha2 = senha2;
		this.cel1 = cel1;
		this.cel2 = cel2;
	}
	
	public void setCidade(String cidade){
		this.cidade = cidade;
	}
	
	public void setPet(String pet){
		this.pet = pet;
	}
	public String getCidade(){
		return this.cidade;
	}
	public String getPet(){
		return this.pet;
	}
	public String getNome(){
		return this.nome;
	}
	public String getEmail(){
		return this.email;
	}
	public String getEmailRep(){
		return this.emailRep;
	}
	public String getSenha(){
		return this.senha;
	}
	public String getSenhaRep(){
		return this.senha2;
	}
	public String getCelPri(){
		return this.cel1;
	}
	public String getCelSec(){
		return this.cel2;
	}
	
	
	
	
	
	
}
