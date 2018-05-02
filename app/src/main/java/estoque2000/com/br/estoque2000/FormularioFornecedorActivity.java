package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import estoque2000.com.br.estoque2000.dao.FornecedorDao;
import estoque2000.com.br.estoque2000.modelo.Fornecedor;

public class FormularioFornecedorActivity extends AppCompatActivity {

    private FormularioHelper helper;
    private FornecedorDao dao = new FornecedorDao(this);
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_fornecedor);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Novo Fornecedor");

        helper = new FormularioHelper(this);

        Intent intent = getIntent();
        Fornecedor fornecedor = (Fornecedor) intent.getSerializableExtra("Fornecedor");
        if(fornecedor != null){
           helper.populaFornecedor(fornecedor);
        }

        Button botaoSalvar = (Button) findViewById(R.id.formulario_salvar);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fornecedor fornecedor = helper.pegaAluno();
                if(fornecedor.getId() != null){
                    dao.atualizar(fornecedor);
                } else {
                    dao.cadastraFornecedor(fornecedor);
                }
                dao.close();
                finish();
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
