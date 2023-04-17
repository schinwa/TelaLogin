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

public class HomeActivity extends AppCompatActivity {
    public SQLiteDatabase bancoDados;
    Button btnExcluir;
    public ArrayList<Integer> arrayIds;
    public Integer idSelecionado;
    TextView textView9;
    Button BtnIrac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnExcluir = (Button) findViewById(R.id.btnExcluir);
        textView9 = (TextView) findViewById(R.id.textView9);

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmaExcluir();
            }
        });


    }

    public void confirmaExcluir() {
        AlertDialog.Builder msgBox = new AlertDialog.Builder(HomeActivity.this);
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

    public void abrirTelaAc(){
        BtnIrac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaAc();
            }
        });
    }
}
