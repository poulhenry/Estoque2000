package estoque2000.com.br.estoque2000;

import android.app.Activity;
import android.widget.EditText;

import estoque2000.com.br.estoque2000.modelo.Fornecedor;

public class FormularioHelper {

    private EditText campoEstado;
    private EditText campoBairro;
    private EditText campoLogradouro;
    private EditText campoComplemento;
    private EditText campoCep;
    private EditText campoEndereco;
    private EditText campoCeluar;
    private EditText campoTelefone;
    private EditText campoEmail;
    private EditText campoCnpj;
    private EditText campoNome;
    private EditText campoCidade;
    private EditText campoPais;
    private EditText campoNumero;
    Fornecedor fornecedor;


    //Construtor, a partir do momento que alguem instanciar essa classe a mesma ja tera
    //todos os valores dos campos armazenadas nas variaveis
    public FormularioHelper(Activity activity){
         campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
         campoCnpj = (EditText) activity.findViewById(R.id.formulario_cnpj);
         campoEmail = (EditText) activity.findViewById(R.id.formulario_email);
         campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
         campoCeluar = (EditText) activity.findViewById(R.id.formulario_celular);
         campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
         campoCep = (EditText) activity.findViewById(R.id.formulario_cep);
         campoComplemento = (EditText) activity.findViewById(R.id.formulario_complemento);
         campoLogradouro = (EditText) activity.findViewById(R.id.formulario_tipoLogradouro);
         campoBairro = (EditText) activity.findViewById(R.id.formulario_bairro);
         campoEstado = (EditText) activity.findViewById(R.id.formulario_estado);
         campoCidade = (EditText) activity.findViewById(R.id.formulario_cidade);
         campoPais = (EditText) activity.findViewById(R.id.formulario_pais);
         campoNumero = (EditText) activity.findViewById(R.id.formulario_numero);
         fornecedor = new Fornecedor();

}


    //Pupola uma instancia pro modelo fornecedor
    public Fornecedor pegaAluno() {

        fornecedor.setNome(campoNome.getText().toString());
        fornecedor.setCnpj(campoCnpj.getText().toString());
        fornecedor.setEmail(campoEmail.getText().toString());
        fornecedor.setTelefone(campoTelefone.getText().toString());
        fornecedor.setCelular(campoCeluar.getText().toString());
        fornecedor.setEndereco(campoEndereco.getText().toString());
        fornecedor.setCep(campoCep.getText().toString());
        fornecedor.setCompplemento(campoComplemento.getText().toString());
        fornecedor.setTipo_logradouro(campoLogradouro.getText().toString());
        fornecedor.setBairro(campoBairro.getText().toString());
        fornecedor.setEstado(campoEstado.getText().toString());
        fornecedor.setCidade(campoCidade.getText().toString());
        fornecedor.setPais(campoPais.getText().toString());
        fornecedor.setNumero(campoNumero.getText().toString());

        return fornecedor;
    }
    //Este meto funciona pra popular a activity, geralmente usado pra transferir dados entre activitys
    public void populaFornecedor(Fornecedor fornecedor) {
        campoNome.setText(fornecedor.getNome());
        campoCnpj.setText(fornecedor.getCnpj());
        campoEmail.setText(fornecedor.getEmail());
        campoTelefone.setText(fornecedor.getTelefone());
        campoCeluar.setText(fornecedor.getCelular());
        campoEndereco.setText(fornecedor.getEndereco());
        campoCep.setText(fornecedor.getCep());
        campoComplemento.setText(fornecedor.getCompplemento());
        campoLogradouro.setText(fornecedor.getTipo_logradouro());
        campoBairro.setText(fornecedor.getBairro());
        campoEstado.setText(fornecedor.getEstado());
        campoCidade.setText(fornecedor.getCidade());
        campoPais.setText(fornecedor.getPais());
        campoNumero.setText(fornecedor.getNumero());
        this.fornecedor = fornecedor;
    }
}
