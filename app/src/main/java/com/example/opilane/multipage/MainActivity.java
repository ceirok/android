package com.example.opilane.multipage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<King> kings = (new Kings()).getKings();
    private List<String> lines = new ArrayList<>();
    private ListView listView, multiListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lstKings);
        multiListView = findViewById(R.id.lstKings2);

        multiListView.setAdapter(new ArrayAdapter<King>
                (this,android.R.layout.simple_list_item_multiple_choice, kings));
        multiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                update(kings.get(position));
            }
        });
    }
    public void update(King king) {
        String line = toString(king);
        if(!lines.remove(line)) lines.add(line);
        listView.setAdapter(new ArrayAdapter<String>
                (MainActivity.this, android.R.layout.simple_list_item_1, lines));
    }
    public String toString(King king) {
        if(king.getFrom() != 0 && king.getTo() != 9999) {
            return String.format("%s: %d - %d", king.getName(), king.getFrom(), king.getTo());
        }
        if(king.getFrom() != 0) {
            return String.format("%s: %d -", king.getName(), king.getFrom());
        }
        if(king.getTo() != 9999) {
            return String.format("%s: - %d", king.getName(), king.getTo());
        }
        return king.getName();
    }

    public void next(View view) {
        Intent newtPage = new Intent(MainActivity.this, SpinnerKings.class);
    }
}
