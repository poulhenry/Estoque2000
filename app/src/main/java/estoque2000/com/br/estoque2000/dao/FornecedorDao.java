package estoque2000.com.br.estoque2000.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import estoque2000.com.br.estoque2000.modelo.Fornecedor;

public class FornecedorDao extends SQLiteOpenHelper{

    public FornecedorDao(Context context)
    {
        super(context, "Estoque2000", null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table fornecedores(id integer primary key, nome text not null," +
                "cnpj text not null, email text not null, telefone text not null, celular text not null," +
                "endereco text not null, cep text not null, complemento text not null, tipo_logradouro text not null," +
                "bairro text not null, estado text not null, cidade text not null, pais text not null, numero text not null);";
    db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "drop table if exists fornecedores";
        db.execSQL(sql);
    }

    public void cadastraFornecedor(Fornecedor fornecedor){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = extraiDadosFornecedor(fornecedor);
        db.insert("fornecedores", null, dados);
    }

    @NonNull
    private ContentValues extraiDadosFornecedor(Fornecedor fornecedor) {
        ContentValues dados = new ContentValues();
        dados.put("nome", fornecedor.getNome());
        dados.put("cnpj", fornecedor.getCnpj());
        dados.put("email", fornecedor.getEmail());
        dados.put("telefone", fornecedor.getTelefone());
        dados.put("celular", fornecedor.getCelular());
        dados.put("endereco", fornecedor.getEndereco());
        dados.put("cep", fornecedor.getCep());
        dados.put("complemento", fornecedor.getCompplemento());
        dados.put("tipo_logradouro", fornecedor.getTipo_logradouro());
        dados.put("bairro", fornecedor.getBairro());
        dados.put("estado", fornecedor.getEstado());
        dados.put("cidade", fornecedor.getCidade());
        dados.put("pais", fornecedor.getPais());
        dados.put("numero", fornecedor.getNumero());
        return dados;
    }

    public List<Fornecedor> buscaFornecedores() {
       String sql = "select * from fornecedores";
       SQLiteDatabase db = getWritableDatabase();
       Cursor c = db.rawQuery(sql, null);
       List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
       while(c.moveToNext()){
           Fornecedor f = new Fornecedor();
           f.setId(c.getLong(c.getColumnIndex("id")));
           f.setNome(c.getString(c.getColumnIndex("nome")));
           f.setCnpj(c.getString(c.getColumnIndex("cnpj")));
           f.setEmail(c.getString(c.getColumnIndex("email")));
           f.setTelefone(c.getString(c.getColumnIndex("telefone")));
           f.setCelular(c.getString(c.getColumnIndex("celular")));
           f.setEndereco(c.getString(c.getColumnIndex("endereco")));
           f.setCep(c.getString(c.getColumnIndex("cep")));
           f.setCompplemento(c.getString(c.getColumnIndex("complemento")));
           f.setTipo_logradouro(c.getString(c.getColumnIndex("tipo_logradouro")));
           f.setBairro(c.getString(c.getColumnIndex("bairro")));
           f.setEstado(c.getString(c.getColumnIndex("estado")));
           f.setCidade(c.getString(c.getColumnIndex("cidade")));
           f.setPais(c.getString(c.getColumnIndex("pais")));
           f.setNumero(c.getString(c.getColumnIndex("numero")));
           fornecedores.add(f);
       }
        c.close();
        return fornecedores;
    }

    public void deleta(Fornecedor f) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {f.getId().toString()};
        db.delete("fornecedores", "id = ?", params);
    }

    public void atualizar(Fornecedor fornecedor) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = extraiDadosFornecedor(fornecedor);
        String[] params = {fornecedor.getId().toString()};
        db.update("fornecedores",dados, "id = ?", params);
    }
}
