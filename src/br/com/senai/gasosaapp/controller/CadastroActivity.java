package br.com.senai.gasosaapp.controller;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import br.com.senai.gasosaapp.R;
import br.com.senai.gasosaapp.dao.AbastecimentoDao;
import br.com.senai.gasosaapp.util.Utils;
import br.senai.gasosaapp.model.Abastecimento;

public class CadastroActivity extends Activity {
	
	private Abastecimento abastecimento;
	private AbastecimentoDao abastecimentoDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro);
		abastecimentoDao = new AbastecimentoDao(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.cadastro, menu);
		return true;
	}
	
	public void salvarRegistro(View view) {
		capturarDados();
		salvarAbastecimento();
	}
	
	private void salvarAbastecimento() {
		abastecimentoDao.salvar(this.abastecimento);
		ArrayList<Abastecimento> buscarTodos = abastecimentoDao.buscarTodos();
		for (Abastecimento abastecimento : buscarTodos) {
			mostrarMensagem(abastecimento.toString());
		}
	}
	
	private void capturarDados() {
		abastecimento = new Abastecimento();
		
		EditText etData = (EditText)findViewById(R.id.et_data);
		abastecimento.setData(Utils.stringToDate(etData.getText().toString()));
		
		EditText etValor = (EditText)findViewById(R.id.et_valor);
		abastecimento.setValor(Double.parseDouble(etValor.getText().toString()));
		
		EditText etLitros = (EditText)findViewById(R.id.et_litros);
		abastecimento.setListros(Double.parseDouble(etLitros.getText().toString()));
		
		EditText etKm = (EditText)findViewById(R.id.et_km);
		abastecimento.setKmRodados(Long.parseLong(etKm.getText().toString()));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_listagem) {
			Intent i = new Intent(this, ListagemActivity.class);
			startActivity(i);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void mostrarMensagem(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}
}
