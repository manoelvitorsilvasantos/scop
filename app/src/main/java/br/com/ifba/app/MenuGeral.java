package br.com.ifba.app;
import android.support.v7.app.*;
import android.view.*;
import android.content.*;
import br.com.ifba.data.*;
import br.mvictor.lib.*;



public class MenuGeral extends AppCompatActivity
{

	
	Context contexto;
	private Alerta func;
	
	public MenuGeral(){
		super();
		this.func = new Alerta();
		this.contexto = this;
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

				func.alertaDialogo(this.contexto, "Sobre", "Curso de Ads 3° - POO", "sair");
				break;
			case R.id.contato:
				func.alertaDialogo(this.contexto, "Contato", "(74)-99972-9815", "Fechar");
				break;
		}
		// TODO: Implement this method
		return super.onOptionsItemSelected(item);
	}
	
	
	
}
