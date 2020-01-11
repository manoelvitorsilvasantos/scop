package br.com.ifba.data;

import org.droidpersistence.*;
import org.droidpersistence.annotation.*;
import android.support.annotation.*;


@Table(name="Pessoa")
public class Pessoa
{
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cod")
	private String cod;
	
	@PrimaryKey
	private int id;
	
	

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getNome()
	{
		return nome;
	}

	public void setCod(String cod)
	{
		this.cod = cod;
	}

	public String getCod()
	{
		return cod;
	}
	
	


	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return id;
	}
}
