package home.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lfz.lfzmjy.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bases.views.BaseActivity;
import bean.Anivsy;
import home.AnivsyDBManager;
import utils.AnivsyDataPrepare;
import home.adapter.AnivsyAdapter;
import utils.DateUtils;
import utils.ThemeUtils;

/**
 * Created by LFZ on 2017/12/3.
 * 纪念日的页面
 */

public class AnivsryActivity extends BaseActivity {

    private TextView title;
    private TextView dp;
    private TextView dCount;
    private RecyclerView daysView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Anivsy> anivsyList;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_anvsy;
    }

    @Override
    protected void initView() {
        ThemeUtils.setStatusBarColor(this, R.color.colorPrimaryDark);
        title = findById(R.id.tgDay);
        dp = findById(R.id.dp);
        dCount = findById(R.id.day);
        daysView = findById(R.id.listDays);

        initRecylerView();
    }

    @Override
    protected void setListeners() {

    }

    @Override
    protected void initData() {
        Date date = new Date();
        Anivsy mAnivsy = AnivsyDBManager.getAnivsy(AnivsyDataPrepare.MJYBIRTH);
        Date tgDay = DateUtils.getServeralYearsDate(mAnivsy.getDate(),date.getYear() + 1 - mAnivsy.getDate().getYear());
        dp.setText("目标日： " + DateUtils.date2str(tgDay, "yyyy-MM-dd") + " " + DateUtils.getDateOfWeek(tgDay.getDay() + 1));
        dCount.setText(DateUtils.getGapCount(date, tgDay)  + "");

        anivsyList = new ArrayList<>();
        anivsyList = AnivsyDBManager.getAllAnivsy();
        AnivsyAdapter adapter = new AnivsyAdapter(this, anivsyList);
        daysView.setAdapter(adapter);

        Calendar now = Calendar.getInstance();
    }

    @Override
    protected void requestData() {

    }

    @Override
    public void onClick(View v) {


    }


    //-----私有方法-----
    private  void initRecylerView(){
        layoutManager = new LinearLayoutManager(this);
        daysView.setLayoutManager(layoutManager);
        daysView.setNestedScrollingEnabled(false);
        daysView.setItemAnimator(new DefaultItemAnimator());
//        daysView.setAdapter(recyclerAdapter);
    }
}
