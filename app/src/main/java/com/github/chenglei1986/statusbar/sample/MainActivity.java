package com.github.chenglei1986.statusbar.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String[] TITLES = {"Light", "Dark", "Blue", "LayoutFullscreenSample1", "LayoutFullscreenSample2", "Drawable"};
    private static final int[] COLORS = {0xFFCCCCCC, 0xFF333333, 0xFF3F51B5};

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setAdapter(new MyAdapter());
        mListView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < 3) {
            int statusBarColor = COLORS[position];
            ColoredStatusBarActivity.actionColoredStatusBar(this, statusBarColor);
        } else if (3 == position) {
            LayoutFullscreenSample1.actionLayoutFullscreenSample1(this);
        } else if (4 == position) {
            LayoutFullscreenSample2.actionLayoutFullscreenSample2(this);
        } else if (5 == position) {
            DrawableStatusBarActivity.actionDrawableStatusBar(this);
        }
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Object getItem(int position) {
            return TITLES[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, parent, false);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.title.setText(TITLES[position]);
            return convertView;
        }
    }

    private static class ViewHolder {
        TextView title;
    }

}
