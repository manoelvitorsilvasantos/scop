package br.com.ifba.Enum;

public class Pets
{
	private int codPet;
	private String nomePet;
	
	public Pets(int codPet, String nomePet){
		this.codPet = codPet;
		this.nomePet = nomePet;
	}
	
	public int getCodPet(){
		return this.codPet;
	}
	
	public String getNomePet(){
		return this.nomePet;
	}
	
	@Override
	public String toString(){
		return this.nomePet;
	}
}
