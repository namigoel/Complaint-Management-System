package com.example.complaintregister;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass> {
    Context context;
     ArrayList<GetterSetter>a1;

    public MyAdapter(Context context, ArrayList<GetterSetter> a1) {
        this.context=context;
        this.a1=a1;
    }
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }



    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rowfile,viewGroup,false);
        return new MyClass(v,mListener);

    }

    @Override
    public void onBindViewHolder(@NonNull final MyClass myClass, int i) {

        GetterSetter g1=a1.get(i);
        myClass.dept.setText(g1.getDepartment());
        myClass.cat.setText(g1.getCategory());
        myClass.des.setText(g1.getDescription());
        myClass.email.setText(g1.getEmail());

    }

    @Override
    public int getItemCount() {
        return a1.size();
    }

    public class MyClass extends RecyclerView.ViewHolder{
        TextView dept,cat,des,email;

        public MyClass(View ItemView,final OnItemClickListener listener){
            super(ItemView);

            dept=ItemView.findViewById(R.id.id_dept);
            cat=ItemView.findViewById(R.id.id_cat);
            des=ItemView.findViewById(R.id.id_des);
            email=ItemView.findViewById(R.id.id_email);

            ItemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }

                }
            });



        }


    }

}
