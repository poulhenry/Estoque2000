package estoque2000.com.br.estoque2000;

import android.app.Activity;
import android.widget.EditText;

import estoque2000.com.br.estoque2000.modelo.Local;

public class LocalHelper  {

    private Long idLocal;
    private EditText localizacao;
    private EditText detalhes;
    private EditText quadrante;
    Local local;


    //Quando alguem chamar este metodo, ele ira pegar os valores dos campos preenchidos e guardar em uma variavel
    public LocalHelper(Activity activity){
        localizacao = (EditText) activity.findViewById(R.id.Local_Localizacao);
        detalhes = (EditText) activity.findViewById(R.id.Local_Detalhes);
        quadrante = (EditText) activity.findViewById(R.id.Local_Quadrante);
        local = new Local();
    }

    //Popula a classe modelo Local
    public Local pupolaModeloLocal(){

        local.setLocalizacao(localizacao.getText().toString());
        local.setDetalhes(detalhes.getText().toString());
        local.setQuadrante(quadrante.getText().toString());
        return local;
    }

    public void populaFormularioLocal(Local local){
        localizacao.setText(local.getLocalizacao().toString());
        detalhes.setText(local.getDetalhes().toString());
        quadrante.setText(local.getQuadrante().toString());
    }
}
