package br.com.ifba.app;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.app.AlertDialog.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import java.util.*;
import br.mvictor.lib.*;
import br.com.ifba.data.DatabaseHelper;
import br.com.ifba.Enum.*;
import android.database.*;



public class MainActivity extends MenuGeral
{
	private AutoCompleteTextView user;
	private EditText pass;
	private Button log;
	private TextView recuperar, registrar;
	private Context contexto;
	private DatabaseHelper myDat;
	private Alerta func = new Alerta();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		contexto = this;
		myDat = new DatabaseHelper(this);

		//Entradas
		this.user = findViewById(R.id.txtUsername);
		this.pass = findViewById(R.id.txtPassword);
	
		//Labels
		this.registrar = findViewById(R.id.lblRegistrar);
		this.recuperar = findViewById(R.id.lblEsquecer);
		
		//botao 
		this.log = findViewById(R.id.btnLogin);
		
		StringBuilder editText = new StringBuilder();

		for(String value: getResources().getStringArray(R.array.lista_nomes)) {
			editText.append(value).append(",");
		}
		
		ArrayAdapter adapter = new ArrayAdapter(this.contexto, android.R.layout.simple_list_item_1, getPessoas());
		this.user.setAdapter(adapter);
		this.user.setThreshold(1);//start searching from 1 character
		this.user.setAdapter(adapter);

		
		this.log.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				
				
				String nome = user.getText().toString();
				String senha = pass.getText().toString();
				
				
				if(user.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.aviso_vazio), Toast.LENGTH_LONG).show();
			
				}
				else if(myDat.getVerificarUser(nome) == true &&  myDat.getVerificarPass(senha) == true){
					func.alertaDialogo(contexto, getResources().getString(R.string.login_alertaDialogoTitulo), getResources().getString(R.string.login_alertaDialogTexto), "Sair");
					finish();
					Intent logar = new Intent(getApplicationContext(), Progresso.class);
					logar.putExtra("nome", nome);
					startActivity(logar);
				}
				else{
					Toast.makeText(getApplicationContext(), getResources().getString(R.string.aviso_invalid), Toast.LENGTH_LONG).show();
				}
			
			}

			private String verificar(String nome)
			{
				// TODO: Implement this method
				String[] res = getResources().getStringArray(R.array.lista_nomes);
				for(int i = 0; i<res.length; i++){
					if(res[i].equals(nome)){
						return nome;
					}
				}
				return null;
			}


			
		});
		
		this.recuperar.setOnClickListener(new TextView.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(contexto, Recuperar.class));
			}
		});
		
		this.registrar.setOnClickListener(new TextView.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					startActivity(new Intent(contexto, Registrar.class));
				}
		});
	
    }
	
	
	public ArrayList<Pessoas> getPessoas(){
		ArrayList<Pessoas> pessoa = new ArrayList<Pessoas>();
		Cursor c = myDat.getDados();
		if(c!= null && c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String pess = c.getString(1);
				Pessoas peop = new Pessoas(id, pess);
				pessoa.add(peop);
			}while(c.moveToNext());
		}
		return pessoa;
	}
	
}
