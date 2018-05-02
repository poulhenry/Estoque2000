package estoque2000.com.br.estoque2000;

import android.widget.EditText;

import estoque2000.com.br.estoque2000.modelo.Estoque;

public class EstoqueHelper {

    private EditText campoNome;
    private EditText campoPreco;
    private EditText campoQuanti;
    private EditText campoCategoria;
    Estoque estoque;

    public EstoqueHelper(FormularioEstoque activity){
         campoNome = (EditText) activity.findViewById(R.id.estoque_nome);
         campoPreco = (EditText) activity.findViewById(R.id.estoque_preco);
         campoQuanti = (EditText) activity.findViewById(R.id.estoque_quant);
         campoCategoria = (EditText) activity.findViewById(R.id.estoque_categoria);
         estoque = new Estoque();

    }

    public Estoque adicionaItem() {
        estoque.setNome(campoNome.getText().toString());
        estoque.setPreco(Double.parseDouble(campoPreco.getText().toString()));
        estoque.setQuantidade(Integer.parseInt(campoQuanti.getText().toString()));
        estoque.setCategoria(campoCategoria.getText().toString());
        return estoque;

    }

    public void populaEstoque(Estoque estoque){
        campoNome.setText(estoque.getNome());
        campoPreco.setText(String.valueOf(estoque.getPreco().doubleValue()));
        campoQuanti.setText(String.valueOf(estoque.getQuantidade()));
        campoCategoria.setText(estoque.getCategoria());
        this.estoque = estoque;
    }
}
