package io.testim.custom;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import io.appium.android.apis.Shakespeare;

public class TimeAgoList extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create the list fragment and add it as our sole content.
        if (getFragmentManager().findFragmentById(android.R.id.content) == null) {
            ArrayListFragment list = new ArrayListFragment();
            getFragmentManager().beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    public static class ArrayListFragment extends ListFragment {

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setListAdapter(new AgoArrayAdapter<String>(getActivity(),
                    android.R.layout.simple_list_item_2, android.R.id.text1, Shakespeare.TITLES));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }

    private static class AgoArrayAdapter<T> extends ArrayAdapter<T>{
        public AgoArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull T[] objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView text2 = (TextView) view.findViewById(android.R.id.text2);

            text2.setText("1 day ago");
            return view;
        }
    }
}