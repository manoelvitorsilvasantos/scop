package br.com.ifba.Enum;

public class Cidades
{
	private String nomeCity;
	private int codCity;
	
	public Cidades(int codCity, String nomeCity){
		this.codCity = codCity;
		this.nomeCity = nomeCity;
	}
	
	public int getCodCity(){
		return this.codCity;
	}
	
	
	public String getNomeCity(){
		return this.nomeCity;
	}

	@Override
	public String toString()
	{
		// TODO: Implement this method
		return nomeCity;
	}
	

}
