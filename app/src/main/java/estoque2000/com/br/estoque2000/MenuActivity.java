package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button btn_Fornecedor;
    Button btn_Local;
    Button btn_Categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_Fornecedor = findViewById(R.id.Fornecedor);
        btn_Local = findViewById(R.id.Locais);
        btn_Categoria = findViewById(R.id.Categoria);

        btn_Fornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProFornecedor = new Intent(MenuActivity.this, FornecedorActivity.class);
                startActivity(vaiProFornecedor);
            }
        });

        btn_Local.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProLocal = new Intent(MenuActivity.this, LocalActivity.class);
                startActivity(vaiProLocal);
            }
        });

   /*     btn_Categoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vaiProCategoria = new Intent(MenuActivity.this, CategoriaActivity.class);
            }
        }); */
    }
}
