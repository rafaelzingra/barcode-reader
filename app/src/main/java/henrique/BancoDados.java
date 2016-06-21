package henrique;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by henrique.5in on 20/06/16.
 */
public class BancoDados extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "usuario.db";
    private static final String TABLE_NAME = "usuario";
    private static final String ID = "id";
    private static final String NAME = "nome";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "senha";
    SQLiteDatabase db;

    public BancoDados(Context context){
        super(context, DATABASE_NAME, null, VERSION);

    }

    private static final String CREATE = "create table if not exists usuario (id integer primary key not null, "+
            "nome text(50) not null, email varchar(50) not null, senha varchar(16) not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public void insereUsuario(Usuario user){
        db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();

        String query = "select * from usuario";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        valores.put(ID, count);
        valores.put(NAME, user.getNome());
        valores.put(EMAIL, user.getEmail());
        valores.put(PASSWORD, user.getSenha());

        db.insert(TABLE_NAME, null, valores);
        db.close();
    }

    public String buscaSenha(String email){
        db = this.getReadableDatabase();
        String query = "Select email, senha from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "Não encontrado";

        if (cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(email)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }
}
