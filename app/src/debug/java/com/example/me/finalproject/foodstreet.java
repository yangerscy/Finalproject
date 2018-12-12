package com.example.me.finalproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class foodstreet extends AppCompatActivity
{
    Button foodmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstreet);


        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1, R.drawable.lauchbox, "光華飯包"));
        memberList.add(new Member(2, R.drawable.landi, "藍迪義大利麵館"));
        memberList.add(new Member(3, R.drawable.trashnoddle, "光華垃圾面"));
        memberList.add(new Member(4, R.drawable.beefnoddle579, "伍柒玖牛肉麵"));
        memberList.add(new Member(5, R.drawable.sakadon, "佐賀丼飯"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
// MemberAdapter 會在步驟7建立
        recyclerView.setAdapter(new MemberAdapter(this, memberList));
    }
    public void foodmapclick (View view){
        foodmap = findViewById(R.id.foodstreetmap);
        Intent foodstreetmap = new Intent(foodstreet.this, com.example.me.finalproject.footstreetmap
                .class);
        foodstreet.this.startActivity(foodstreetmap);
    }

    private class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewHolder> {
        private Context context;
        private List<Member> memberList;

        MemberAdapter(Context context, List<Member> memberList) {
            this.context = context;
            this.memberList = memberList;
        }

        @Override
        public MemberAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MemberAdapter.ViewHolder holder, int position) {
            final Member member = memberList.get(position);
            holder.imageId.setImageResource(member.getImage());
            holder.textId.setText(String.valueOf(member.getId()));
            holder.textName.setText(member.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView imageView = new ImageView(context);
                    imageView.setImageResource(member.getImage());
                    Toast toast = new Toast(context);
                    toast.setView(imageView);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return memberList.size();
        }

        //Adapter 需要一個 ViewHolder，只要實作它的 constructor 就好，保存起來的view會放在itemView裡面
        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageId;
            TextView textId, textName;
            ViewHolder(View itemView) {
                super(itemView);
                imageId = (ImageView) itemView.findViewById(R.id.imageId);
                textId = (TextView) itemView.findViewById(R.id.textId);
                textName = (TextView) itemView.findViewById(R.id.textName);
            }
        }
    }
}



