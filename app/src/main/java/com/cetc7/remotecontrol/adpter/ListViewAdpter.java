package com.cetc7.remotecontrol.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.cetc7.remotecontrol.GloableParams;
import com.cetc7.remotecontrol.R;
import java.util.ArrayList;

public class ListViewAdpter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> stringList;

    public ListViewAdpter(Context mContext,ArrayList<String> stringList){
        this.mContext = mContext;
        this.stringList = stringList;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return stringList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return stringList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder2 viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_listview,parent,false);
            viewHolder = new ViewHolder2();
            viewHolder.text = (TextView) convertView.findViewById(R.id.item_list_text);
            viewHolder.check = (TextView) convertView.findViewById(R.id.item_list_check);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder2) convertView.getTag();
        }
        viewHolder.text.setText(stringList.get(position));
        if (GloableParams.selectPosition == position){
            viewHolder.check.setVisibility(View.VISIBLE);
        }else {
            viewHolder.check.setVisibility(View.GONE);
        }
        return convertView;
    }
}

class ViewHolder2{
    TextView text;
    TextView check;
}
