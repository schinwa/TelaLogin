package com.example.telalogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {
    EditText txtLogin, txtSenha, txtConfSenha;
    Button btnCadastrar;
    SQLiteDatabase bancoDados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        txtLogin = (EditText) findViewById(R.id.txtLogin);
        txtSenha = (EditText) findViewById(R.id.txtSenha);
        txtConfSenha = (EditText) findViewById(R.id.txtConfSenha);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar();
            }
        });
    }

    public void cadastrar(){
        if(!TextUtils.isEmpty(txtLogin.getText().toString()) || !TextUtils.isEmpty(txtSenha.getText().toString())){
            String valueConfSenha = txtConfSenha.getText().toString();
            String valueSenha = txtSenha.getText().toString();

            if(valueConfSenha.equals(valueSenha)) {
                try {
                    bancoDados = openOrCreateDatabase("yourlogin", MODE_PRIVATE, null);
                    String sql = "INSERT INTO logine (login, senha) VALUES (?,?)";
                    SQLiteStatement stmt = bancoDados.compileStatement(sql);
                    stmt.bindString(1, txtLogin.getText().toString());
                    stmt.bindString(2, txtSenha.getText().toString());
                    stmt.executeInsert();
                    bancoDados.close();
                    finish();
                    //abrirTelaLogin();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this,"senhas diferentes", Toast.LENGTH_SHORT).show();
            }
        } else {
        Toast.makeText(this,"Favor inserir o login e senha!", Toast.LENGTH_SHORT).show();
    }
    }

    public void abrirTelaLogin(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}