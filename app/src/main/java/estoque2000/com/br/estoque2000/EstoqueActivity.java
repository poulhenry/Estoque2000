package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import estoque2000.com.br.estoque2000.dao.EstoqueDao;
import estoque2000.com.br.estoque2000.modelo.Estoque;

public class EstoqueActivity extends AppCompatActivity {

    private ListView listaEstoque;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoque);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Estoque");

        listaEstoque = findViewById(R.id.lista_estoque);

        Button botaoAdd = findViewById(R.id.estoque_add);
        botaoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiFormEstoque = new Intent(EstoqueActivity.this, FormularioEstoque.class);
                startActivity(vaiFormEstoque);

            }
        });

        registerForContextMenu(listaEstoque);

    }
    // metodo abaixo atualiza a lista quando for salvo um novo produto!
    private void carregaLista() {
        EstoqueDao dao = new EstoqueDao(this);
        List<Estoque> estoques = dao.buscaItem();
        dao.close();

        ArrayAdapter<Estoque> adapter = new ArrayAdapter<Estoque>(this,
                android.R.layout.simple_list_item_1, estoques);
        listaEstoque.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem editar = menu.add("Editar");
        MenuItem deletar = menu.add("Excluir");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Estoque estoque = (Estoque) listaEstoque.getItemAtPosition(info.position);

                EstoqueDao dao = new EstoqueDao(EstoqueActivity.this);
                dao.deleta(estoque);
                dao.close();
                Toast.makeText(EstoqueActivity.this, "Produto Excluido", Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });

        listaEstoque = findViewById(R.id.lista_estoque);
        editar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Estoque estoque = (Estoque) listaEstoque.getItemAtPosition(info.position);

                Intent intentVaiForm = new Intent(EstoqueActivity.this, FormularioEstoque.class);
                intentVaiForm.putExtra("estoque", estoque);
                startActivity(intentVaiForm);
                return false;
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
