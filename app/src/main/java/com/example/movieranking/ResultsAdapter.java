package com.example.movieranking;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class ResultsAdapter extends BaseAdapter {
    public ArrayList<HashMap> list;
    Activity activity;

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
