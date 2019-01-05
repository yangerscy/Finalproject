package com.example.me.finalproject;

import android.content.ClipData;
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
import java.util.Random;

public class foodstreet extends AppCompatActivity
{
    Button foodmap;
    Button radon;
    TextView radontxv;
    Random randomGenerator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodstreet);


        List<Member> memberList = new ArrayList<>();
        memberList.add(new Member(1, R.drawable.lauchbox, "光華飯包"));
        memberList.add(new Member(2, R.drawable.landi, "藍迪義大利麵"));
        memberList.add(new Member(3, R.drawable.trashnoddle, "光華垃圾面"));
        memberList.add(new Member(4, R.drawable.beefnoddle579, "伍柒玖牛肉麵"));
        memberList.add(new Member(5, R.drawable.sakadon, "佐賀丼飯"));
        memberList.add(new Member(6, R.drawable.beefnoodle95, "玖伍牛肉麵"));
        memberList.add(new Member(7, R.drawable.satoseiniku, "佐藤精肉店"));
        memberList.add(new Member(8, R.drawable.yangdumplin, "楊記大餛飩"));
        memberList.add(new Member(9, R.drawable.noodlericefull, "麵足飯飽便當"));
        memberList.add(new Member(10, R.drawable.seafoodnoodle, "鼎吉粥棧"));
        memberList.add(new Member(11, R.drawable.oldnoodle, "老德記牛肉麵"));
        memberList.add(new Member(12, R.drawable.impasta, "I'm PASTA"));
        memberList.add(new Member(13, R.drawable.matsunoodle, "馬祖麵館"));
        memberList.add(new Member(14, R.drawable.karirice, "咖食堂"));
        memberList.add(new Member(15, R.drawable.slivenoodle, "銀記牛肉麵"));
        memberList.add(new Member(16, R.drawable.indiacari, "印度風咖哩"));
        memberList.add(new Member(17, R.drawable.friderice, "喬喜蛋炒飯"));
        memberList.add(new Member(18, R.drawable.vietname, "好滋味越南美食"));


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
    public  void foodstreetradon (View view){
        int getlist=0;
        String randomlist []={"光華飯包","藍迪義大利麵","光華垃圾面","伍柒玖牛肉麵","佐賀丼飯","玖伍牛肉麵","佐藤精肉店","楊記大餛飩","麵足飯飽便當"
                ,"鼎吉粥棧","老德記牛肉麵","I'm PASTA","馬祖麵館","咖食堂","銀記牛肉麵","印度風咖哩","喬喜蛋炒飯","好滋味越南美食"};
        radon = findViewById(R.id.ranfonfoodstreet);
        radontxv = findViewById(R.id.foodstradon);

        Random x = new Random();
        getlist = x.nextInt(18);

        radontxv.setText(String.valueOf("等等吃~"+randomlist[getlist]));

    }
}



