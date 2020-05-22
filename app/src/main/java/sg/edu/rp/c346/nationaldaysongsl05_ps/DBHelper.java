package sg.edu.rp.c346.nationaldaysongsl05_ps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Songs.db";
    private static final int DATABASE_VERSION = 2;
    private static final String TABLE_SONG= "song";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_TITLE = "song_title";
    private static final String COLUMN_SINGER = "song_singer";
    private static final String COLUMN_YEAR = "song_year";
    private static final String COLUMN_STARS = "stars";


    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createNoteTableSql = "CREATE TABLE " + TABLE_SONG + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_TITLE + " TEXT,"
                + COLUMN_SINGER + " TEXT,"
                + COLUMN_YEAR + " INTEGER,"
                + COLUMN_STARS + " INTEGER )";
        db.execSQL(createNoteTableSql);


    }
    public int updateSong(Song data){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,data.getTitle());
        values.put(COLUMN_SINGER,data.getSingers());
        values.put(COLUMN_YEAR,data.getYear());
        values.put(COLUMN_STARS,data.getStars());
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(data.get_id())};
        int result = db.update(TABLE_SONG,values,condition,args);
        db.close();
        return result;
    }

    public long insertSongs(String title, String singer, int year, int stars ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_SINGER,singer);
        values.put(COLUMN_YEAR,year);
        values.put(COLUMN_STARS,stars);
        long result = db.insert(TABLE_SONG,null,values);
        db.close();
        if (result == -1){
            Log.d("DBHelper","Insert failed");
        }
        return result;
    }

    public int deleteNote(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String condition = COLUMN_ID + "= ?";
        String[] args = {String.valueOf(id)};
        int result = db.delete(TABLE_SONG, condition, args);
        db.close();
        return result;
    }


    public ArrayList<Song> getAllSongs(){
        ArrayList<Song> songs = new ArrayList<Song>();

        String selectQuery = "SELECT " + COLUMN_ID + ","
                + COLUMN_TITLE + ","
                + COLUMN_SINGER + ","
                + COLUMN_YEAR + ","
                + COLUMN_STARS + " FROM " + TABLE_SONG;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String title = cursor.getString(1);
                String singer = cursor.getString(2);
                int year = cursor.getInt(3);
                int stars = cursor.getInt(4);
                Song song = new Song(id,title,singer,year,stars);
                songs.add(song);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return songs;

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE " + TABLE_SONG + " ADD COLUMN module_name TEXT ");
    }
}
