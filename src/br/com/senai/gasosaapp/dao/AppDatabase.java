package br.com.senai.gasosaapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppDatabase {
	
	private final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "gasosaapp.db";
	
	public static final String TABELA_ABASTECIMENTO  = "abastecimento";

	public static final String COLUNA_ID 	   	= "_id";
	public static final String COLUNA_DATA    	= "data";
	public static final String COLUNA_VALOR    	= "valor";
	public static final String COLUNA_LITROS    = "litros";
	public static final String COLUNA_KMRODADOS = "kmrodados";
	
	private static final String TABLE_CREATE = "create table " + TABELA_ABASTECIMENTO + "( " 
			+ COLUNA_ID    	  	+ " integer primary key autoincrement, " 
			+ COLUNA_DATA 	  	+ " numeric not null, "
			+ COLUNA_VALOR 	  	+ " real not null, "
			+ COLUNA_LITROS 	+ " real not null, "
			+ COLUNA_KMRODADOS 	+ " integer not null "
			+ ");";
	
	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;

	public AppDatabase(Context context) {
		this.dbHelper = new DatabaseHelper(context);
		this.database = dbHelper.getWritableDatabase();
	}

	public SQLiteDatabase getDatabase() {
		return database;
	}
	
	public void closeConnection() {
		dbHelper.close();
	}
	
	class DatabaseHelper extends SQLiteOpenHelper {

		public DatabaseHelper(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			Log.w("APP DATABASE", "Atualizando banco de dados da versão " + oldVersion + 
					" para versão " + newVersion + ". Todos os dados serão perdidos");
			db.execSQL("DROP TABLE IF EXISTS " + TABELA_ABASTECIMENTO);
			onCreate(db);
		}
	}
}
