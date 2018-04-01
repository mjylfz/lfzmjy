package home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfz.lfzmjy.R;

import java.util.List;

import bean.Anivsy;

/**
 * Created by LFZ on 2017/12/3.
 * 纪念日列表
 */

public class AnivsyAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Anivsy> anivsyList;

    public AnivsyAdapter(Context context, List<Anivsy> anivsyList){
        this.context = context;
        this.anivsyList = anivsyList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_anlist, parent ,false);
        AnivsyHolder holder = new AnivsyHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Anivsy anivsy = anivsyList.get(position);
        AnivsyHolder anivsyHolder = (AnivsyHolder)holder;
        anivsyHolder.tvYear.setText(anivsy.getDate().getDate() + "");
        anivsyHolder.tvMD.setText(anivsy.getDate().getYear() + 1900 + " " + (anivsy.getDate().getMonth() + 1));
        anivsyHolder.description.setText(anivsy.getDescription());
        anivsyHolder.tgDay.setText(anivsy.getGapDate() + "");
    }

    @Override
    public int getItemCount() {
        return anivsyList.size();
    }

    class AnivsyHolder extends RecyclerView.ViewHolder{
        public TextView tvYear;
        public TextView tvMD;
        public TextView description;
        public TextView tgDay;
        public AnivsyHolder(View itemView) {
            super(itemView);

            tvYear = (TextView)itemView.findViewById(R.id.tvYear);
            tvMD = (TextView)itemView.findViewById(R.id.tvMD);
            description = (TextView)itemView.findViewById(R.id.list_dp);
            tgDay = (TextView)itemView.findViewById(R.id.dayct);
        }

    }
}
