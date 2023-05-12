package com.cetc7.remotecontrol.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.*;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ListView;
import android.widget.TextView;
import com.cetc7.remotecontrol.Constants;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import com.cetc7.remotecontrol.adpter.ListViewAdpter;
import com.cetc7.remotecontrol.common.Utils;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class ListViewActivity extends Activity implements OnClickListener, OnItemClickListener {
    private TextView txt_title,txt_left,txt_right;
    private ListView listview;
    private int rid;
    private ListViewAdpter listViewAdpter;
    private ArrayList<String> list_string;
    private int list_item;
    private String list_item_string;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        initView();
        setListener();
    }

    private void setListener() {
        txt_left.setOnClickListener(this);
        txt_right.setOnClickListener(this);
        listview.setOnItemClickListener(this);
    }

    private void initView() {
        txt_title = (TextView) findViewById(R.id.txt_title);
        String Name = getIntent().getStringExtra(Constants.NAME);
        rid = getIntent().getExtras().getInt("rid");
        list_string = getIntent().getExtras().getStringArrayList("list_string");
        txt_title.setText(Name);
        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_left.setText("取消");
        txt_left.setVisibility(View.VISIBLE);
        txt_right = (TextView) findViewById(R.id.txt_right);
        txt_right.setText("确定");
        txt_right.setVisibility(View.VISIBLE);
        listview = (ListView) findViewById(R.id.listview);
        listViewAdpter = new ListViewAdpter(ListViewActivity.this,list_string);
        listview.setAdapter(listViewAdpter);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.txt_left:
                Utils.finish(ListViewActivity.this);
                break;
            case R.id.txt_right:
                Intent intent = new Intent();
                intent.putExtra("list_item",list_item);
                intent.putExtra("list_item_string",list_item_string);
                setResult(rid,intent);
                Utils.finish(ListViewActivity.this);
                break;
            default:
                break;
        }
    }

    /**
     * Callback method to be invoked when an item in this AdapterView has
     * been clicked.
     * <p>
     * Implementers can call getItemAtPosition(position) if they need
     * to access the data associated with the selected item.
     *
     * @param parent   The AdapterView where the click happened.
     * @param view     The view within the AdapterView that was clicked (this
     *                 will be a view provided by the adapter)
     * @param position The position of the view in the adapter.
     * @param id       The row id of the item that was clicked.
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GloableParams.selectPosition = position;
        listViewAdpter.notifyDataSetChanged();
        switch (rid){
            case R.id.re_work_mode:
                list_item_string = list_string.get(position);
                if (position<8){
                    list_item = position+1;
                } else {
                    list_item = 248+position;
                }
                break;
            case R.id.re_modset_workmode:
            case R.id.re_demset_workmode:
            case R.id.re_antimodset_workmode:
                list_item_string = list_string.get(position);
                list_item = (byte) position;
                break;
            case R.id.re_modset_sendrate:
                if (position>=0){
                    list_item_string = list_string.get(position);
                    if (position<=6){
                        list_item = position;
                    } else if (position <= 34){
                        list_item = position + 9;
                    } else if (position <= 63){
                        list_item = position + 29;
                    } else if (position <= 68){
                        list_item = position + 32;
                    } else if (position <= 78){
                        list_item = position + 45;
                    } else if (position <=107){
                        list_item = position + 49;
                    }
                }
                break;
            case R.id.re_demset_recvrate:
                if (position>=0){
                    list_item_string = list_string.get(position);
                    if (position<=3){
                        list_item = position;
                    } else if (position <= 22){
                        list_item = position + 12;
                    } else if (position <= 29){
                        list_item = position + 25;
                    } else if (position <= 39){
                        list_item = position + 34;
                    } else if (position <= 68){
                        list_item = position + 88;
                    }
                }
                break;
            case R.id.re_antidemset_rate:
                if (position>=0){
                    list_item_string = list_string.get(position);
                    if (position<=20){
                        list_item = position+128;
                    } else if (position <= 29){
                        list_item = position + 171;
                    } else if (position <= 31){
                        list_item = position + 178;
                    }
                }
                break;
            default:
                break;
        }
    }
}
