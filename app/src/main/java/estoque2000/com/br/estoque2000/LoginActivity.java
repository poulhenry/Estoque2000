package estoque2000.com.br.estoque2000;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText Usuario;
    EditText Senha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Button btn_Autentificar = findViewById(R.id.Login_Autentificar);
        btn_Autentificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario = findViewById(R.id.Login_Nome);
                Senha = findViewById(R.id.Login_Senha);
            if(Usuario.getText().toString().equals("123") && Senha.getText().toString().equals("123")) {
                Intent vaiProMenu = new Intent(LoginActivity.this, MenuActivity.class);
                startActivity(vaiProMenu);
              } else {
                limpaLogin();
                Toast.makeText(getApplicationContext(), "Usuario ou Senha incorreta", Toast.LENGTH_SHORT).show();
            }
            }
        });

    }

    public void limpaLogin(){
        Usuario.setText("");
        Senha.setText("");
    }
}
