package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ListaPersonagemActivity extends AppCompatActivity {

     private final PersonagemDAO dao = new PersonagemDAO();

    //Overridade em cima do lista personagem
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_de_personagem );
        //Título
        setTitle( "Lista de Personagens" );
        criaPersonagens();
        botaoNovoPersonagem();

    }

    private void botaoNovoPersonagem() {
        //Mais findByID que eu já sei o que faz rs
        FloatingActionButton botaoNovoPersonagem = findViewById( R.id.floatingActionButton );
        botaoNovoPersonagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class) );
            }
        } );
    }

    private void criaPersonagens() {
        //Alguns personagens já prontos.
        dao.salva(new Personagem( "Ken", "1,80", "02041979" ));
        dao.salva(new Personagem( "Ryu", "1,90", "03051979" ));
    }

    //Isso faz com que o dados não sejam apagados quando voltar.
    @Override
    protected void onResume() {
        super.onResume();

        //Achar a listinha
        ListView listadePersonagens = findViewById( R.id.activity_main_lista_personagem );
        //Colocando todos os personagens numa lista
        List<Personagem> personagens = dao.todos();
        //Listando-as
        listadePersonagens.setAdapter( new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens ) );
        //Método pra quando clicar no personagem
        listadePersonagens.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = personagens.get(posicao);
                Log.i("posicao", "" + personagemEscolhido );
                Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
                vaiParaFormulario.putExtra( "personagem", personagemEscolhido );
                startActivity( vaiParaFormulario );
            }
        } );
    }
}

