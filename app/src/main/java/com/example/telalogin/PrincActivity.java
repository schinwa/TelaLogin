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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PrincActivity extends AppCompatActivity {
    public SQLiteDatabase bancoDados;
    Button btnExcluir;
    public ArrayList<Integer> arrayIds;
    public Integer idSelecionado;
    RatingBar avaliacao;
    Button ok;
    TextView textView9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_princ);

        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        textView9 = (TextView) findViewById(R.id.textView9);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaExcluir();
            }
        });
        avaliacao = (RatingBar) findViewById(R.id.avaliacao);
        ok = (Button) findViewById(R.id.ok);

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



    public void confirmaExcluir() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(PrincActivity.this);
        msgBox.setTitle("Excluir");
        msgBox.setIcon(android.R.drawable.ic_menu_delete);
        msgBox.setMessage("Você realmente deseja excluir essa conta?");
        msgBox.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deletar();
                abrirTelaLogin();
            }
        });
        msgBox.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        msgBox.show();
    }

    public void deletar(){
        Intent intent = getIntent();
        try{
            bancoDados = openOrCreateDatabase("yourlogin", MODE_PRIVATE, null);
            String sql = "DELETE FROM logine WHERE login =?";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);
            stmt.bindString(1, intent.getStringExtra("login"));
            stmt.executeUpdateDelete();
            bancoDados.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void abrirTelaLogin(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}

