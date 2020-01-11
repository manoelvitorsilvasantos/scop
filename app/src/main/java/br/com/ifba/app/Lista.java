package br.com.ifba.app;
import android.support.v7.app.*;
import android.os.*;
import android.widget.*;
import android.content.*;
import android.view.*;
import br.com.ifba.data.*;
import android.database.*;
import br.com.ifba.Enum.*;
import java.util.*;

public class Lista extends AppCompatActivity
{

	//private ListView lista;
	//private ArrayAdapter<String> adaptador;
	private TextView txtnome, email;

	private DatabaseHelper myData;

	private Cursor curso;
	private String nome;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.perfil);
		myData = new DatabaseHelper(this);
		
		Cursor res  = myData.getDados();
		
		if(res.getCount() == 0){
			Alerta(this, "Aviso","Vazio");
		}
		
		StringBuilder texto = new StringBuilder();
		int cidade = new Random().nextInt(8);
		int pets = new Random().nextInt(1);
		while(res.moveToNext()){
			if(res.getString(1).equals(getIntent().getExtras().getString("nome"))){
				texto.append("Usuário: ").append(res.getString(1)).append("\n");
				texto.append("Email: ").append(res.getString(2)).append("\n");
				texto.append("Cel : ").append(res.getString(4)).append("\n");
				texto.append("Cel2: ").append(res.getString(5)).append("\n");
				texto.append("Cidade: ").append(res.getString(6)).append("\n");
				texto.append("Pet: ").append(res.getString(7)).append("\n");
			}
		}
		
		this.txtnome = findViewById(R.id.tagNome);
		//this.txtnome.setText("Nome: "+getIntent().getExtras().getString("nome"));
		this.txtnome.setText(texto.toString());
		/*
		this.lista = findViewById(R.id.lista);
		String[] listaDados = getResources().getStringArray(R.array.lista_nomes);
		this.adaptador = new ArrayAdapter<String>(this, R.layout.lista_item,listaDados);
		this.lista.setAdapter(this.adaptador);
		*/
	}

	@Override
	protected void onPause()
	{
		// TODO: Implement this method
		super.onPause();
		finish();
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.menu_opcoes, menu);
		// TODO: Implement this method
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.sobre:
				Alerta(this, "Sobre", "Curso de ADS 3° - POO");
				break;
			case R.id.contato:
				Alerta(this, "Contato", "(74) - 99972- 9815");
				break;
		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}


	private void Alerta(Context contexto, String titulo, String mensagem){
		AlertDialog.Builder a = new AlertDialog.Builder(contexto);
		a.setTitle(titulo);
		a.setMessage(mensagem);
		a.setNeutralButton(getResources().getString(R.string.btn_Sair), new DialogInterface.OnClickListener(){
				@Override
				public void onClick(DialogInterface dialog, int pos){
					closeContextMenu();
				}
			});
		a.create().show();
	}
	
	
	
}
