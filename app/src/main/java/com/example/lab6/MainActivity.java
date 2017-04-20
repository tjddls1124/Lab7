package com.example.lab6;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.zip.Inflater;

import static android.R.attr.data;
import static android.R.attr.isDefault;


public class MainActivity extends AppCompatActivity {
    static int count=0;
    CheckBox cb;
    ListView listView;

    ArrayList<Matzip> matzip_list = new ArrayList<>();
    MatzipAdapter adapter;

    int REQUEST_MSG_CODE = 1;
    int REQUEST_MSG_CODE2= 2;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListView();





    }

    public void onClick(View v){
        if(v.getId() == R.id.btnAddMatzip) {
            Intent intent = new Intent(this, Main2Activity.class);
            Matzip matzip = new Matzip("", "", 0, "", "", "", "", "");

            intent.putExtra("msg_matzip", matzip);

            startActivityForResult(intent, REQUEST_MSG_CODE);
        }
        else if(v.getId() == R.id.btnName) {
            Comparator<Matzip> dataAsc = new Comparator<Matzip>() {
                @Override
                public int compare(Matzip o1, Matzip o2) {
                    return o1.name.compareTo(o2.name);
                }
            };

            Collections.sort(matzip_list,dataAsc);
            adapter.notifyDataSetChanged();
        }
        else if(v.getId() == R.id. btnKind) {
            Comparator<Matzip> dataKindAsc = new Comparator<Matzip>() {
                @Override
                public int compare(Matzip o1, Matzip o2) {
                    return o1.menu_kind > o2.menu_kind ? 1 : -1 ;
                }
            };
            Collections.sort(matzip_list,dataKindAsc);
            adapter.notifyDataSetChanged();
        }

        else if(v.getId()==R.id.btnChoose) {

            LinearLayout list_layout =(LinearLayout) View.inflate(MainActivity.this ,R.layout.list_layout,null);
            Button button = (Button)findViewById(R.id.btnChoose);
            button.setText("삭제");
        }
    }



    public void setListView(){
        final Intent intent = new Intent(this, Main3Activity.class);

        listView = (ListView)findViewById(R.id.listview);
        adapter = new MatzipAdapter(matzip_list,this);

//        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LinearLayout list_view = (LinearLayout) inflater.inflate(R.layout.list_layout,null);
        adapter.setVisible(null);
//        cb =  (CheckBox) list_view.findViewById(R.id.checkBox);
//        cb.setVisibility(View.VISIBLE);
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                intent.putExtra( "msg_matzip_data", matzip_list.get(position) );
                startActivityForResult(intent,REQUEST_MSG_CODE2);

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                ListView listView = (ListView) parent;
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("삭제확인");
                dlg.setIcon(R.mipmap.ic_launcher);
                dlg.setMessage("등록된 맛집정보를 삭제하시겠습니까?");
                dlg.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        matzip_list.remove(position);
                        adapter.notifyDataSetChanged();
                        count--;
                    }
                });
                dlg.setPositiveButton("취소", null);
                dlg.show();
                return true;
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_MSG_CODE)
        {
            if(resultCode == RESULT_OK){
                Matzip m;
                m = data.getParcelableExtra("remakemsg");
                tv = (TextView)findViewById(R.id.tv);

                matzip_list.add(count,m);
                int size = matzip_list.size();
                tv.setText("맛집 리스트(" +size+"개)");
                count++;

                adapter.notifyDataSetChanged();
            }

        }
    }
}