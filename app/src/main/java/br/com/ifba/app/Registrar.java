package br.com.ifba.app;
import android.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import br.com.ifba.data.*;
import br.com.ifba.funcoes.*;
import android.support.v7.app.*;
import android.content.*;
import br.com.jansenfelipe.androidmask.*;
import br.com.ifba.Enum.*;
import android.database.*;
import java.util.*;
import br.mvictor.lib.*;


public class Registrar extends AppCompatActivity implements MascaraConst
{

	private EditText user, email, emailRep, senha, senhaRep, cel1, cel2;
	private Button reg;
	private LoginEncapsulamento encrip = new LoginEncapsulamento();
	private DatabaseHelper myData;
	private String[] listaCidade;
	private Cidades cidadeSel;
	private Pets petsSel;
	private String Nome = null;
	private String Email = null, EmailRep = null;
	private String Senha = null, SenhaRep = null;
	private String celular1 = null, celular2 = null;
	private Spinner spin, spinPet;
	private Alerta func = new Alerta();
	private Validador val = new Validador();
	private Context contexto;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrar);
	
		this.myData = new DatabaseHelper(this);
		this.contexto = this;
		//List <String> lista = new ArrayList<String>();
		//lista.add("Home");
		//lista.add("Teste");
		ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getCidades());
		ArrayAdapter adaptador2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getPets());
		user= findViewById(R.id.et_user);
		senha = findViewById(R.id.et_pass);
		senhaRep = findViewById(R.id.et_passReg);
		email = findViewById(R.id.et_mail);
		emailRep = findViewById(R.id.et_mailReg);
		cel1 = findViewById(R.id.et_cel1);
		cel2 = findViewById(R.id.et_cel2);
		reg = findViewById(R.id.btnReg);
		//cel1.addTextChangedListener();
		//cel2.addTextChangedListener(Mask.insert("(##)-#####-####", cel2));
		
		cel1.addTextChangedListener(new MaskEditTextChangedListener(TEL_BR, cel1));
		cel2.addTextChangedListener(new MaskEditTextChangedListener(TEL_BR, cel2));
		
		spin = findViewById(R.id.spin);
		spinPet = findViewById(R.id.spinAnimal);
		adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin.setAdapter(adaptador);
		spinPet.setAdapter(adaptador2);
		spin.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					// TODO: Implement this method
					cidadeSel = ((Cidades)spin.getSelectedItem());
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}
		});
		
		spinPet.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{
					// TODO: Implement this method
					petsSel = ((Pets)spinPet.getSelectedItem());
					
				}
				
				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					// TODO: Implement this method
				}
				
			
		});
		reg.setOnClickListener(new Button.OnClickListener(){


				@Override
				public void onClick(View p1)
				{
					
					// TODO: Implement this method
					if(val.getIsEmpty(user)){
						func.alertaDialogo(contexto, "Aviso", "Campo nome vazio", "Sair");
					}
					else if(val.getIsEmpty(email)){
						func.alertaDialogo(contexto, "Aviso", "Campo Email vazio", "Sair");
					}
					else if(val.getIsEmpty(emailRep)){
						func.alertaDialogo(contexto, "Aviso", "Campo Confirmacão de Email  vazio", "Sair");
					}
					else if(val.getIsEmpty(senha)){
						func.alertaDialogo(contexto, "Aviso", "Campo Senha vazio", "Sair");
					}
					else if(val.getIsEmpty(senha)){
						func.alertaDialogo(contexto,  "Aviso", "Campo Confirmação de Senha vazio", "Sair");
					}
					else if(val.getIsEmpty(cel1)){
						func.alertaDialogo(contexto, "Aviso", "Campo Fone principal vazio", "Sair");
					}
					else if(val.getIsEmpty(cel2)){
						func.alertaDialogo(contexto, "Aviso", "Campo Fone secudário vazio", "Sair");
					}
					else{
						
						if(!val.getEditIsEquals(email, emailRep)){
							func.alertaDialogo(contexto, "Aviso", "E-mails não coencide!", "Sair");
						}
						else if(!val.getEditIsEquals(senha, senhaRep)){
							func.alertaDialogo(contexto, "Aviso", "Senhas são diferentes", "Sair");
						}
						else{
							if(myData.getUsuario(user)){
								func.alertaDialogo(contexto, "Aviso", "Usuário ja cadastrado", "Sair");
							}
							else{
								
								if(!val.getIsValidEmail(email)){
									func.alertaDialogo(contexto, "Aviso", "Email invalido, Digite um Email Válido", "Sair");
								}
								else if(!val.getIsValidEmail(emailRep)){
									func.alertaDialogo(contexto, "Aviso", "Email invalido, Digite um Email Válido", "Sair");
								}
								else{
									
									Email = email.getText().toString();
									EmailRep = emailRep.getText().toString();
									Senha = senha.getText().toString();
									SenhaRep = senhaRep.getText().toString();
									Nome = user.getText().toString();
									celular1 = cel1.getText().toString();
									celular2 = cel2.getText().toString();
									
									encrip = new LoginEncapsulamento();
									encrip.setUsername(val.getEditTextToString(user));
									


									Informacoes info = new Informacoes(encrip.getUsername(), Email, EmailRep, Senha, SenhaRep, celular1, celular2);
									info.setCidade(cidadeSel.getNomeCity());
									info.setPet(petsSel.getNomePet());
									boolean est = myData.insere(info);

									String texto = (est == true)?"Cadastro realizado com sucesso...":"ERROR";
									Toast.makeText(getApplicationContext(), texto, Toast.LENGTH_LONG).show();
									user.setText("");
									email.setText("");
									emailRep.setText("");
									senha.setText("");
									senhaRep.setText("");
									cel1.setText("");
									cel2.setText("");
								}
							}
						}
					}
					
				}
				
				
				
			
		});
	
		
		
		
	}
	public ArrayList<Pets> getPets(){
		ArrayList<Pets> pets = new ArrayList<Pets>();
		Cursor c = myData.getAnimal();
		if(c!= null && c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String animal = c.getString(1);
				Pets pet = new Pets(id, animal);
				pets.add(pet);
			}while(c.moveToNext());
		}
		return pets;
	}
	public ArrayList<Cidades> getCidades(){
		ArrayList<Cidades> cidades = new ArrayList<Cidades>();
		Cursor c = myData.getCidade();
		if(c!= null && c.moveToFirst()){
			do{
				int id = c.getInt(0);
				String cidade = c.getString(1);
				Cidades city = new Cidades(id, cidade);
				cidades.add(city);
			}while(c.moveToNext());
		}
		return cidades;
	}
	
	
	
	}
