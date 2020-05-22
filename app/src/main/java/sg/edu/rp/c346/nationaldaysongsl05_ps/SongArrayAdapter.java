package sg.edu.rp.c346.nationaldaysongsl05_ps;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SongArrayAdapter extends ArrayAdapter<Song> {
    TextView tvYear,tvTitle,tvSinger;
    ImageView iv1,iv2,iv3,iv4,iv5;
    Context context;
    ArrayList<Song> songs;
    int resources;
    public SongArrayAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resources = resource;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resources,parent,false);

        Song song = songs.get(position);
        iv1 = rowView.findViewById(R.id.iv1);
        iv2 = rowView.findViewById(R.id.iv2);
        iv3 = rowView.findViewById(R.id.iv3);
        iv4 = rowView.findViewById(R.id.iv4);
        iv5 = rowView.findViewById(R.id.iv5);
        tvYear = rowView.findViewById(R.id.tvYears);
        tvSinger = rowView.findViewById(R.id.tvSingers);
        tvTitle = rowView.findViewById(R.id.tvTitles);

        tvYear.setText(song.getYear() + " ");
        tvTitle.setText(song.getTitle());
        tvSinger.setText(song.getSingers());

        if (song.getStars() == 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (song.getStars() == 4){
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if(song.getStars() == 3){
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else if (song.getStars() == 2){
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }else{
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        }
        return rowView;

    }

}
