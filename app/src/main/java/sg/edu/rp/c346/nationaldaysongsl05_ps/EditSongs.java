package sg.edu.rp.c346.nationaldaysongsl05_ps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class EditSongs extends AppCompatActivity {

    TextView tvID;
    EditText etSongTitle, etSinger, etYear;
    Button btnUpdate,btnDelete,btnCancel;
    RadioGroup rgStars;
    RadioButton rb,rb1,rb2,rb3,rb4,rb5;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_songs);


        btnUpdate = (Button)findViewById(R.id.btnUpdates);
        btnDelete = findViewById(R.id.btnDeletes);
        btnCancel = findViewById(R.id.btnCancels);
        tvID = findViewById(R.id.tvID);
        etSongTitle = findViewById(R.id.etSongTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);
        int selectButtonId = rgStars.getCheckedRadioButtonId();
        rb = findViewById(selectButtonId);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        rb4 = findViewById(R.id.rb4);
        rb5 = findViewById(R.id.rb5);

        Intent i =  getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText(data.get_id()+"");
        etSongTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(data.getYear() + "");

        int noStars = data.getStars();

        if (noStars == 5){
            rb5.setChecked(true);
        }else if (noStars == 4){
            rb4.setChecked(true);
        }else if (noStars == 3){
            rb3.setChecked(true);
        }else if (noStars == 2){
            rb2.setChecked(true);
        }else{
            rb1.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(EditSongs.this);
                data.setTitle(etSongTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));
                data.setStars(getStars());
                db.updateSong(data);
                db.close();
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(EditSongs.this);
                db.deleteNote(data.get_id());
                db.close();
                Intent i = new Intent();
                setResult(RESULT_OK,i);
                finish();
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
