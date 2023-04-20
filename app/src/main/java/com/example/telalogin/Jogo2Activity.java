package com.example.telalogin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Jogo2Activity extends AppCompatActivity {
    public SQLiteDatabase bancoDados;
    public ArrayList<Integer> arrayIds;
    public Integer idSelecionado;
    RatingBar avaliacao;
    Button ok;
    TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo2);

        avaliacao = (RatingBar) findViewById(R.id.avaliacao);
        ok = (Button) findViewById(R.id.ok);
        textView9 = (TextView) findViewById(R.id.textView9);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrar();
            }
        });



    }
    public void mostrar () {
        Float x = avaliacao.getRating();
        //Toast.makeText(this, "Estrelas: "+x.toString(), Toast.LENGTH_SHORT).show();
        textView9.setText("Estrelas: "+x.toString());

    }

}

