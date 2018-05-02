package estoque2000.com.br.estoque2000.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import estoque2000.com.br.estoque2000.modelo.Local;

public class LocalDao extends SQLiteOpenHelper{
    public LocalDao(Context context) {
        super(context, "Local2000", null,3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String sql = ("Create table Local(idlocal integer primary key, localizacao Text," +
               "detalhes Text, quadrante Text)");
       db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = ("DROP TABLE IF EXISTS Local2000");
        db.execSQL(sql);
    }

    public void CadastrarLocal(Local local){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("localizacao", local.getLocalizacao());
        dados.put("detalhes", local.getDetalhes());
        dados.put("quadrante", local.getQuadrante());
        db.insert("Local",null, dados);
    }

    public void UpdateLocal(Local local){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        dados.put("localizacao", local.getLocalizacao());
        dados.put("detalhes", local.getDetalhes());
        dados.put("quadrante", local.getQuadrante());
        String[] params = {local.getIdlocal().toString()};
        db.update("Local",dados,"idlocal = ?", params);
    }

    public List<Local> buscarLocais() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Select * from Local";
        Cursor c = db.rawQuery(sql, null);
        List<Local> locais = new ArrayList<Local>();
        while (c.moveToNext()){
          Local local = new Local();
          local.setIdlocal(c.getLong(c.getColumnIndex("idlocal")));
          local.setLocalizacao(c.getString(c.getColumnIndex("localizacao")));
          local.setDetalhes(c.getString(c.getColumnIndex("detalhes")));
          local.setQuadrante(c.getString(c.getColumnIndex("quadrante")));
          locais.add(local);
        }
        return locais;
    }

    public void deletar(Local local){
        SQLiteDatabase db = getWritableDatabase();
        String[] params = {local.getIdlocal().toString()};
        db.delete("Local", "idlocal = ?", params);
    }
}
