package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;

import estoque2000.com.br.estoque2000.dao.LocalDao;
import estoque2000.com.br.estoque2000.modelo.Local;

public class FormularioLocalActivity extends AppCompatActivity {


    Local localTransf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_local);

        final LocalHelper helper = new LocalHelper(this);
        final LocalDao dao = new LocalDao(this);

        Intent intent = getIntent();
        localTransf = (Local) intent.getSerializableExtra("Local");
        if(localTransf.getIdlocal().toString() != null) {
            helper.populaFormularioLocal(localTransf);
        }


        final Button btnSalvar =  findViewById(R.id.ButtonLocal_Novo);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Local local = helper.pupolaModeloLocal();
                if(localTransf.getIdlocal() != null){
                    local.setIdlocal(localTransf.getIdlocal());
                    dao.UpdateLocal(local);
                } else {
                    dao.CadastrarLocal(local);
                }
             finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario, menu);

        return super.onCreateOptionsMenu(menu);
    }
}

