package com.example.android.zfr;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

class MainAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private  String[] mItem;
    private int[] mImage;
    private String[] mCost;

    public MainAdapter(Context c, String[] mItem, int[] mImage, String[] mCost) {
        this.context=c;
        this.mItem=mItem;
        this.mImage = mImage;
        this.mCost = mCost;

    }

    @Override
    public int getCount() {
        return mItem.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null){
            inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(convertView==null){
            convertView = inflater.inflate(R.layout.row, null);
        }

        TextView itemName=convertView.findViewById(R.id.item_name);
        itemName.setText(mItem[position]);
        ImageView imageView = convertView.findViewById(R.id.d_image);
        imageView.setImageResource(mImage[position]);
        TextView cost1 = convertView.findViewById(R.id.cost_value);
        cost1.setText(mCost[position]);

        return convertView ;
    }
}


