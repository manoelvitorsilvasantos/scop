package br.com.ifba.Enum;

public class Pessoas
{
	private int id;
	private String nomes;
	
	public Pessoas(int id, String nomes)
	{
		this.id = id;
		this.nomes = nomes;
	}
	
	public int getId(){
		return this.id;
	}
	
	public String grtNomes(){
		return this.nomes;
	}
	
	@Override
	public String toString(){
		return this.nomes;
	}
}
