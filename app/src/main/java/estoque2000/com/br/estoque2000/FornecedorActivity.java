package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import estoque2000.com.br.estoque2000.dao.FornecedorDao;
import estoque2000.com.br.estoque2000.modelo.Fornecedor;


public class FornecedorActivity extends AppCompatActivity {

    private ListView listaFornecedores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fornecedor);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Fornecedor");

        listaFornecedores = findViewById(R.id.lista_fornecedores);
        listaFornecedores.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Fornecedor fornecedor = (Fornecedor) listaFornecedores.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(FornecedorActivity.this, FormularioFornecedorActivity.class);
                intentVaiProFormulario.putExtra("Fornecedor", fornecedor);
                startActivity(intentVaiProFormulario);

            }
        });

        Button novoAluno = (Button) findViewById(R.id.fornecedor_NovoAluno);
        novoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentVaiProFormulario = new Intent(FornecedorActivity.this, FormularioFornecedorActivity.class);
                startActivity(intentVaiProFormulario);
            }
        });

      registerForContextMenu(listaFornecedores);

    }

    private void carregaLista() {
        FornecedorDao dao = new FornecedorDao(this);
        List<Fornecedor> fornecedores = dao.buscaFornecedores();
        dao.close();
        ArrayAdapter<Fornecedor> adapter = new ArrayAdapter<Fornecedor>(this, android.R.layout.simple_list_item_1, fornecedores);
        listaFornecedores.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
       MenuItem deletar =  menu.add("DELETAR");
       deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
           @Override
           public boolean onMenuItemClick(MenuItem item) {
               AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
               Fornecedor f = (Fornecedor) listaFornecedores.getItemAtPosition(info.position);
               FornecedorDao dao = new FornecedorDao(FornecedorActivity.this);
               dao.deleta(f);
               dao.close();
               carregaLista();
               return false;
           }
       });
    }
}
