package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import estoque2000.com.br.estoque2000.dao.FornecedorDao;
import estoque2000.com.br.estoque2000.dao.LocalDao;
import estoque2000.com.br.estoque2000.modelo.Fornecedor;
import estoque2000.com.br.estoque2000.modelo.Local;

public class LocalActivity extends AppCompatActivity {

     ListView listaLocal;
     LocalDao dao = new LocalDao(LocalActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Local");

      listaLocal = findViewById(R.id.Lista_local);

     listaLocal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
               Local local = (Local) listaLocal.getItemAtPosition(position);
               Intent vaiProFormLocal = new Intent(LocalActivity.this, FormularioLocalActivity.class);
               vaiProFormLocal.putExtra("Local", local);
               startActivity(vaiProFormLocal);
           }
       });


        Button btn_novoLocal = findViewById(R.id.Local_Novo);
        btn_novoLocal.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiLocalNovo = new Intent(LocalActivity.this, FormularioLocalActivity.class);
                startActivity(vaiLocalNovo);
            }
        });

        registerForContextMenu(listaLocal);

    }

    public void carregaLista(){
        LocalDao dao = new LocalDao(this);
        List<Local> locais = dao.buscarLocais();
        dao.close();
        ArrayAdapter<Local> adapter = new ArrayAdapter<Local>(this, android.R.layout.simple_list_item_1, locais);
        listaLocal.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        carregaLista();
        super.onResume();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem deletar =  menu.add("DELETAR");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Local l = (Local) listaLocal.getItemAtPosition(info.position);
                dao.deletar(l);
                dao.close();
                carregaLista();
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
