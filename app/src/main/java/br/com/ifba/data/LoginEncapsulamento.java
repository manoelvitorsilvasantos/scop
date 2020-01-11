package br.com.ifba.data;

public class LoginEncapsulamento
{
	private String user, pass;
	
	public String getUsername(){
		return this.user;
	}
	
	public String getpassword(){
		return this.pass;
	}
	
	public void setUsername(String user){
		this.user = user;
	}
	
	public void setPassword(String pass){
		this.pass = pass;
	}
}
