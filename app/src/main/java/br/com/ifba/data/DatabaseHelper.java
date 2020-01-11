package br.com.ifba.data;


import android.database.sqlite.*;
import android.content.*;
import android.database.*;
import android.widget.*;
import android.util.*;
import android.content.res.*;

public class DatabaseHelper extends SQLiteOpenHelper
{
	public final static String DbName = "baseDados.db";
	public final static int DbVersion = 1;
	public final static String TBL_NAME = "infoUser";
	public final static String COL_ID = "id";
	public final static String COL_NOME = "NOME";
	public final static String COL_EMAIL = "EMAIL";
	public final static String COL_SENHA = "SENHA";
	public final static String COL_TELA = "CEL1";
	public final static String COL_TELB = "CEL2";
	public final static String COL_CITY = "CITY";
	public final static String COL_ANIMAL = "PET";
	private Context context;
	private long estado = 0;
	private boolean estate = false;


	public DatabaseHelper(Context contexto){
		super(contexto, DbName, null, DbVersion);
		this.context = contexto;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{


		String SQL_TBL_CITY = "CREATE TABLE IF NOT EXISTS tbl_city ( codCity INTEGER NOT NULL UNIQUE, nomeCity	INTEGER UNIQUE, PRIMARY KEY(codCity));";
		String SQL_TBL_ANIMAL = "CREATE TABLE IF NOT EXISTS tbl_animal(codAnimal INTEGER NOT NULL UNIQUE, tipoAnimal TEXT NOT NULL UNIQUE, PRIMARY KEY(codAnimal));";
		String SQL_TBL_INSERTS_CITY = "INSERT INTO tbl_city (codCity,nomeCity) VALUES (1,'America Dourado'), (2,'Barra do Mendes'),(3,'Barro Alto'),(4,'Cafarnaum'),(5,'Canarana'),(6,'Central'),(7,'Gentío do Ouro'),(8,'Ibipeba'),(9,'Ibititá'),(10,'Iraquara'),(11,'Irecê'),(12,'João Dourado'),(13,'Jussara'),(14,'Lapão'),(15,'Mulungu do Morro'),(16,'Presidente Dutra'),(17,'São Gabriel'),(18,'Souto Soares'),(19,'Uibaí');";
		String SQL_TBL_INSERTS_ANIMAL = "INSERT INTO tbl_animal (codAnimal,tipoAnimal) VALUES (1,'Cão'),(2,'Gato');";
		String SQL_TBL_USER = "CREATE TABLE IF NOT EXISTS "+TBL_NAME+"("
			+COL_ID+" integer primary key autoincrement,"
			+COL_NOME+" text,"
			+COL_EMAIL+" text,"
			+COL_SENHA+" text,"
			+COL_TELA+" text,"
			+COL_TELB+" text,"
			+COL_CITY+" text,"
			+COL_ANIMAL+" text"
			+");";
			
		
  
		db.execSQL(SQL_TBL_USER);

		db.execSQL(SQL_TBL_CITY);
		db.execSQL(SQL_TBL_ANIMAL);
		db.execSQL(SQL_TBL_INSERTS_CITY);
		db.execSQL(SQL_TBL_INSERTS_ANIMAL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int p2, int p3)
	{
		// TODO: Implement this method
		db.execSQL("DROP TABLE IF EXISTS "+TBL_NAME+";");
		onCreate(db);
	}

	@Override
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO: Implement this method
		super.onDowngrade(db, oldVersion, newVersion);

	}

	public boolean insere(Informacoes  info){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues valores = new ContentValues();
		valores.put(COL_NOME, info.getNome());
		valores.put(COL_EMAIL, info.getEmail());
		valores.put(COL_SENHA, info.getSenha());
		valores.put(COL_TELA, info.getCelPri());
		valores.put(COL_TELB, info.getCelSec());
		valores.put(COL_CITY,info.getCidade());
		valores.put(COL_ANIMAL, info.getPet());
		long estado = db.insert(TBL_NAME, null, valores);
		if(estado ==-1){
			return false;
		}
		else{ 
			return true;
		}
	}
	
	public boolean atualizar(Informacoes info){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues v = new ContentValues();
		return true;
	}

	public Cursor getDados(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor resultado = db.rawQuery("SELECT *FROM infoUser;", null);
		return resultado;
	}

	public Cursor getCidade(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT *FROM tbl_city;", null);
		return c;
	}
	
	public Cursor getAnimal(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery("SELECT *FROM tbl_animal;", null);
		return c;
	}
	
	public boolean getUsuario(EditText entrada){
		Cursor c = getDados();
		boolean estado  = (c.getCount() != 0)?true:false;
		boolean seuRetorno= false;
		if(estado == true){
			while(c.moveToNext()){
				if(c.getString(1).equals(entrada.getText().toString())){
					this.estate = true;
					seuRetorno = true;
				}
				
			}
			if(seuRetorno == true){
				this.estate = true;
			}
			else{
				this.estate = false;
			}
		}
		else{
			seuRetorno = false;
		}
		return estate;
	}

	public boolean getVerificarUser(String campo){
		Cursor cursorPass = getDados();
		this.estado = 0;
		if(cursorPass.getCount() != 0){
			while(cursorPass.moveToNext()){
				if(cursorPass.getString(1).equals(campo)){
					this.estado = 1;
				}
			}
			if(this.estado == 0){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
	}


	public boolean getVerificarPass(String campo){
		Cursor cursorPass = getDados();
		this.estado = 0;
		if(cursorPass.getCount() != 0){
			while(cursorPass.moveToNext()){
				if(cursorPass.getString(3).equals(campo)){
					this.estado = 1;
				}
			}
			if(this.estado == 0){
				return false;
			}
			else{
				return true;
			}
		}
		else{
			return false;
		}
		
	}

}
