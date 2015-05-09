package br.com.senai.gasosaapp.dao;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import br.com.senai.gasosaapp.util.Utils;
import br.senai.gasosaapp.model.Abastecimento;

public class AbastecimentoDao {
	
	private SQLiteDatabase database;
	private AppDatabase dbHelper;
	private String[] colunas = { AppDatabase.COLUNA_ID,
								 AppDatabase.COLUNA_DATA, 
								 AppDatabase.COLUNA_VALOR,
								 AppDatabase.COLUNA_LITROS,
								 AppDatabase.COLUNA_KMRODADOS };

	public AbastecimentoDao(Context context) {
		dbHelper = new AppDatabase(context);
		database = dbHelper.getDatabase();
	}

	public void open() throws SQLException {
		database = dbHelper.getDatabase();
	}

	public void close() {
		dbHelper.closeConnection();
	}

	public Abastecimento salvar(Abastecimento a) {
		Abastecimento novoAbastecimento = null;
		ContentValues values = new ContentValues();
		
		values.put(AppDatabase.COLUNA_DATA, Utils.dateToString(a.getData(), "dd/MM/yyyy"));
		values.put(AppDatabase.COLUNA_VALOR, a.getValor());
		values.put(AppDatabase.COLUNA_LITROS, a.getListros());
		values.put(AppDatabase.COLUNA_KMRODADOS, a.getKmRodados());

		long insertId = database.insert(AppDatabase.TABELA_ABASTECIMENTO, null, values);
		Cursor cursor = database.query(AppDatabase.TABELA_ABASTECIMENTO, colunas,
				AppDatabase.COLUNA_ID + " = " + insertId, null, null, null, null);

		if (cursor.moveToFirst()) {
			novoAbastecimento = cursorToFrase(cursor);
		}
		cursor.close();
		return novoAbastecimento;
	}

	public ArrayList<Abastecimento> buscarTodos() {
		Abastecimento a = null;
		ArrayList<Abastecimento> listaAbastecimentos = new ArrayList<Abastecimento>();
		Cursor cursor = database.query(AppDatabase.TABELA_ABASTECIMENTO, colunas, null, null, null, null, null);

		if (cursor.moveToFirst()) {
			while (!cursor.isAfterLast()) {
				a = cursorToFrase(cursor);
				listaAbastecimentos.add(a);
				cursor.moveToNext();
			}
		}
		cursor.close();
		return listaAbastecimentos;
	}

	public Integer count() {
		Integer count = 0;
		String query = "select count(*) from " + AppDatabase.TABELA_ABASTECIMENTO + ";";
		Cursor cursor = database.rawQuery(query, null);

		if (cursor.moveToFirst()) {
			count = cursor.getInt(0);
		}
		cursor.close();
		return count;
	}

	private Abastecimento cursorToFrase(Cursor cursor) {
		Abastecimento a = new Abastecimento();
		a.setId(cursor.getLong(0));
		a.setData(Utils.stringToDate(cursor.getString(1)));
		a.setValor(cursor.getDouble(2));
		a.setListros(cursor.getDouble(3));
		a.setKmRodados(cursor.getLong(4));
		return a;
	}
}
