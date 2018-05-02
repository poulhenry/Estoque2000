package estoque2000.com.br.estoque2000.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import estoque2000.com.br.estoque2000.modelo.Estoque;

public class EstoqueDao extends SQLiteOpenHelper {

    public EstoqueDao(Context context) {
        super(context, "Estoque2000", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Itens (id INTEGER PRIMARY KEY, nome TEXT, " +
                "preco REAL, quantidade INTEGER, categoria TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Itens";
        db.execSQL(sql);
        onCreate(db);

    }

    public void insereItem(Estoque estoque){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues dados = extraiDadosEstoque(estoque);

        db.insert("Itens",null, dados);
    }

    @NonNull
    private ContentValues extraiDadosEstoque(Estoque estoque) {
        ContentValues dados = new ContentValues();
        dados.put("nome", estoque.getNome());
        dados.put("preco", estoque.getPreco());
        dados.put("quantidade", estoque.getQuantidade());
        dados.put("categoria", estoque.getCategoria());

        return dados;
    }

    public List<Estoque> buscaItem() {
        String sql = "SELECT * FROM Itens;";
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql,null);

        List<Estoque> estoques = new ArrayList<Estoque>();
        while (c.moveToNext()){
            Estoque estoque = new Estoque();
            estoque.setId(c.getLong(c.getColumnIndex("id")));
            estoque.setNome(c.getString(c.getColumnIndex("nome")));
            estoque.setPreco(c.getDouble(c.getColumnIndex("preco")));
            estoque.setQuantidade(c.getInt(c.getColumnIndex("quantidade")));
            estoque.setCategoria(c.getString(c.getColumnIndex("categoria")));

            estoques.add(estoque);
        }
        c.close();
        return estoques;
    }

    public void deleta(Estoque estoque) {
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {String.valueOf(estoque.getId())};
        db.delete("Itens", "id = ?", params);

    }

    public void altera(Estoque estoque) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = extraiDadosEstoque(estoque);
        String[] params = {String.valueOf(estoque.getId())};
        db.update("Itens",dados, "id = ?", params);

    }
}
