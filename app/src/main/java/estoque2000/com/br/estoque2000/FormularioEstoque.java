package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.channels.NonReadableChannelException;

import estoque2000.com.br.estoque2000.dao.EstoqueDao;
import estoque2000.com.br.estoque2000.modelo.Estoque;

import static android.R.id.home;

public class FormularioEstoque extends AppCompatActivity {

    private EstoqueHelper helper;
    private EstoqueDao dao = new EstoqueDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_estoque);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Novo Produto");

        helper = new EstoqueHelper(this);

        Intent intent = getIntent();
        Estoque estoque = (Estoque) intent.getSerializableExtra("estoque");
        if (estoque != null){
            helper.populaEstoque(estoque);
        }
    }

    // abaixo chamamos os icones na ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Abaixo temos o botão cancelar e o salvar na ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == home) {
            finish();
        }
        switch (item.getItemId()){
            case R.id.menu_formulario_local_ok:
                Estoque estoque = helper.adicionaItem();

                if (estoque.getId() != null){
                    dao.altera(estoque);
                }else {
                    dao.insereItem(estoque);
                }
                dao.close();

                Toast.makeText(FormularioEstoque.this, "Produto Salvo", Toast.LENGTH_SHORT).show();

                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}

