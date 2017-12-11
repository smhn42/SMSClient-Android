package mhd3v.filteredsms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mahad on 11/27/2017.
 */

public class Tab1Fragment extends Fragment {


    ArrayList<sms> smsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tab1_fragment, container, false);

        ListView knownList = (ListView) view.findViewById(R.id.knownList);

        MainActivity activity = (MainActivity) getActivity();

        smsList = activity.getKnownSms();

//        Log.d("ASD",smsList.get(0).userMessages.get(0));

        knownList.setAdapter(new customAdapter());

        knownList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ArrayList<String> senderMessages = new ArrayList<String>();
                ArrayList<String> senderTime = new ArrayList<String>();


                ArrayList<String> userMessages = new ArrayList<String>();
                ArrayList<String> userTime = new ArrayList<String>();

                for(int j=0; j < smsList.get(position).messages.size(); j++){
                        senderMessages.add(smsList.get(position).messages.get(j).messageBody);
                        senderTime.add(smsList.get(position).messages.get(j).time);
                }

                for(int j=0; j < smsList.get(position).userMessages.size(); j++){
                    userMessages.add(smsList.get(position).userMessages.get(j).messageBody);
                    userTime.add(smsList.get(position).userMessages.get(j).time);

                }

                Intent intent = new Intent(getActivity(), CoversationActivity.class);
                //Log.d("alo",senderList.get(position) );
                intent.putExtra("sender", smsList.get(position).sender);
                intent.putExtra("senderMessages", senderMessages);
                intent.putExtra("senderTime", senderTime);
                intent.putExtra("userMessages", userMessages);
                intent.putExtra("userTime", userTime);


                startActivity(intent);



            }
        });

        return view;
    }


    class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return smsList.size(); //set array adapter next six days from today
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
//
            view = getActivity().getLayoutInflater().inflate(R.layout.custom_list,null);

            TextView sender= view.findViewById(R.id.sender);

            sender.setText(smsList.get(i).sender);

            TextView time= view.findViewById(R.id.time);
            TextView text= view.findViewById(R.id.textbody);

            text.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum eget interdum enim, vitae elementum tortor. Vivamus elementum mauris in metus aliquam, sed placerat nibh pharetra.");

            ImageView contactPicture = view.findViewById(R.id.contactPicture);
            contactPicture.setImageResource(R.drawable.knownsender);

            return view;
        }
    }


}
