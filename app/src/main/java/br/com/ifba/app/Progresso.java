package br.com.ifba.app;
import android.os.*;
import android.widget.*;
import android.content.*;

public class Progresso extends MenuGeral
{
	private Handler handler = new Handler();
	private ProgressBar barra;
	private int status_progresso = 0;
	private Context contexto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.progresso);
		barra = findViewById(R.id.barra_progresso);
		this.contexto = this;
		
		new Thread(new Runnable(){
				@Override
				public void run()
				{
					// TODO: Implement this method
					while(status_progresso < 100){
						status_progresso+= 10;
						handler.post(new Runnable(){

								@Override
								public void run()
								{
									// TODO: Implement this method
									barra.setProgress(status_progresso);
								}
								
							
						});
						
						try{
							Thread.sleep(1000);
							finish();
							Intent recebeEnvia = new Intent(contexto, Lista.class);
							recebeEnvia.putExtra("nome", getIntent().getExtras().getString("nome"));
							startActivity(recebeEnvia);
						
						}catch(Exception e){ 
							e.printStackTrace();
						}
						
					}
				}
		}){
			
		}.start();
	}
	
	
}
