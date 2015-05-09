package br.com.senai.gasosaapp.controller;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import br.com.senai.gasosaapp.R;
import br.com.senai.gasosaapp.dao.AbastecimentoDao;
import br.senai.gasosaapp.model.Abastecimento;

public class ListagemActivity extends ListActivity {

	private ArrayList<String> listaStringAbastecimento;
	private ArrayList<Abastecimento> listaAbastecimento;
	
	private AbastecimentoDao abastecimentoDao;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		abastecimentoDao = new AbastecimentoDao(this);
		buscaTodosAbastecimentos();
		carregaLista();
	}
	
	private void buscaTodosAbastecimentos() {
		listaAbastecimento = abastecimentoDao.buscarTodos();
		listaStringAbastecimento = new ArrayList<String>();
		for (Abastecimento abastecimento : listaAbastecimento) {
			listaStringAbastecimento.add(abastecimento.toString());
		}
	}
	
	private void carregaLista() {
		ArrayAdapter<String> listaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaStringAbastecimento);
		this.setListAdapter(listaAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.listagem, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_cadastro) {
			Intent i = new Intent(this, CadastroActivity.class);
			startActivity(i);

			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
