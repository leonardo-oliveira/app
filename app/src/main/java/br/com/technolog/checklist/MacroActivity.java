package br.com.technolog.checklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import models.ImageAdapter;

public class MacroActivity extends AppCompatActivity {

    private GridView gridView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_macro);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                if (position == 0){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.inicioviagem)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 1){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.manobra)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 2){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.manutencao)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 3){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.carga)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 4){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.descarga)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 5){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.espera)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 6){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.abastecer)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 7){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.refeicao)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 8){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.descanso)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 9){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.ultrapassagem)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                }else if(position == 10){
                    new AlertDialog.Builder(MacroActivity.this)
                            .setIcon(R.drawable.ic_warning_amber_800_24dp)
                            .setTitle("Alerta!")
                            .setMessage(R.string.sair)
                            .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    final Intent intent1 = getIntent();
                                    Bundle valores = intent1.getExtras();
                                    Intent intent = new Intent(getApplicationContext(), MenuAplicacaoActivity.class);
                                    intent.putExtras(valores);
                                    startActivity(intent);  //O efeito ao ser pressionado do botão (no caso abre a activity)
                                }
                            })
                            .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .show();
                } else if(position == 11){
                    new AlertDialog.Builder(MacroActivity.this)
                        .setIcon(R.drawable.ic_warning_amber_800_24dp)
                        .setTitle("Alerta!")
                        .setMessage(R.string.motorista)
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(),"Configuração de macro ainda não realizada!",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }


            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                final Intent intent1 = getIntent();
                Bundle valores = intent1.getExtras();
                Intent intent = new Intent(getApplicationContext(), MenuAplicacaoActivity.class);
                intent.putExtras(valores);
                startActivity(intent);  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

}
