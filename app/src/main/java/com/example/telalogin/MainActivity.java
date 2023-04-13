package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase bancoDados;
    EditText txtLogin, txtSenha;
    Button btnConta, btnEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        btnConta = (Button) findViewById(R.id.btnCadastro);
        btnEntrar = (Button) findViewById(R.id.btnEntrar);

        btnConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abrirTelaCadastro();
            }
        });

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar();
            }
        });

        criarBancoDados();
        //inserirDados();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

    public void criarBancoDados(){
        try {
            bancoDados = openOrCreateDatabase("yourlogin", MODE_PRIVATE, null);
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS logine(" +
                    "   login VARCHAR PRIMARY KEY" +
                    " , senha VARCHAR)");
            //bancoDados.execSQL("DELETE FROM animal");
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirDados(){
        try{
            bancoDados = openOrCreateDatabase("yourlogin", MODE_PRIVATE, null);
            String sql = "INSERT INTO logine (login,senha) VALUES (?,?)";
            SQLiteStatement stmt = bancoDados.compileStatement(sql);

            stmt.bindString(1,"jes");
            stmt.bindString(2,"123");
            stmt.executeInsert();

            bancoDados.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void verificar(){
        String valueLogin = txtLogin.getText().toString();
        String valueSenha = txtSenha.getText().toString();

        try {
            bancoDados = openOrCreateDatabase("yourlogin", MODE_PRIVATE, null);
            Cursor meuCursor = bancoDados.rawQuery("SELECT login, senha FROM logine WHERE login = '" + valueLogin + "' AND senha = '" + valueSenha + "'", null);
            meuCursor.moveToFirst();

            if(meuCursor.getCount() > 0){
                abrirTelaPrinc();
            } else {
                Toast.makeText(this,"Login ou Senha incorretos!", Toast.LENGTH_SHORT).show();
            }
            bancoDados.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void abrirTelaCadastro(){
        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);
    }

    public void abrirTelaPrinc(){
        Intent intent = new Intent(this,PrincActivity.class);
        intent.putExtra("login", txtLogin.getText().toString());
        startActivity(intent);
    }
}