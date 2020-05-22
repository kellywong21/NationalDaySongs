package sg.edu.rp.c346.nationaldaysongsl05_ps;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SongList extends AppCompatActivity {
    Button btnFiveStars;
    ListView lvSongList;
    ArrayList<Song> al = new ArrayList<>();
    ArrayAdapter aa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);
        btnFiveStars = findViewById(R.id.btnFiveStars);
        lvSongList = findViewById(R.id.lvSongList);

        DBHelper db = new DBHelper(SongList.this);
        ArrayList<Song> data = db.getAllSongs();
        db.close();
        al.addAll(data);
        aa = new SongArrayAdapter(SongList.this,R.layout.row,al);
        lvSongList.setAdapter(aa);

        lvSongList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               Song target = al.get(position);
               Intent i = new Intent(SongList.this, EditSongs.class);
               i.putExtra("data",target);
               startActivityForResult(i,9);
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            aa.notifyDataSetChanged();
        }
    }
}
