package br.com.ifba.app;
import android.support.v7.app.*;
import android.os.*;
import android.widget.*;

public class Recuperar extends MenuGeral
{
	private EditText email;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recuperar);
		email = findViewById(R.id.txtEmailRecup);
		//MaskEditTextChangedListener maskCpf = new MaskEditTextChangedListener("###.###.###-##", user);
		//user.addTextChangedListener(maskCpf);
	
		
	}
	
	
}
