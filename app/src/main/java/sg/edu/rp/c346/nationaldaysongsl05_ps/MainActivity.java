package sg.edu.rp.c346.nationaldaysongsl05_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etSongTitle,etSinger,etYear;
    RadioGroup rgStars;
    RadioButton rb;
    Button btnInsert,btnShowList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int selectButtonId = rgStars.getCheckedRadioButtonId();
                rb = findViewById(selectButtonId);
                String title = etSongTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int  year = Integer.parseInt(etYear.getText().toString());
                int stars = Integer.parseInt(rb.getText().toString());
                etSongTitle.setText("");
                etSinger.setText("");
                etYear.setText("");
                DBHelper db = new DBHelper(MainActivity.this);
                long insertedID = db.insertSongs(title,singer,year,stars);
                db.close();
                if (insertedID != -1){
                    Toast.makeText(MainActivity.this,"Inserted Successfully",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Song Insert Failed",Toast.LENGTH_LONG).show();
                }


            }
        });
        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SongList.class);
                startActivity(intent);
            }
        });

    }
    private int getStars() {
        int stars = 1;
        switch (rgStars.getCheckedRadioButtonId()) {
            case R.id.rb1:
                stars = 1;
                break;
            case R.id.rb2:
                stars = 2;
                break;
            case R.id.rb3:
                stars = 3;
                break;
            case R.id.rb4:
                stars = 4;
                break;
            case R.id.rb5:
                stars = 5;
                break;
        }
        return stars;
    }
}
