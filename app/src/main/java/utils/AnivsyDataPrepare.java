package utils;

import android.content.res.Resources;

import com.lfz.lfzmjy.R;

import java.util.ArrayList;
import java.util.List;

import app.MyApplication;
import bean.Anivsy;
import home.AnivsyDBManager;
import home.activity.AnivsryActivity;
import utils.DateUtils;

/**
 * Created by LFZ on 2017/12/4.
 * 数据预加载
 */

public class AnivsyDataPrepare  extends DataPrepare{
    public static final String MJYBIRTH = "mjybirth";
    public static final String LFZBIRTH = "lfzbirth";
    public static final String FIRSTKISS = "firstkiss";
    public static final String FIRSTMAKELOVE = "firstmakelove";
    public static final String FIRSTLOVE = "firstlove";
    public static final String FIRSTDATE = "firstdate";

    private static AnivsyDataPrepare anivsyDataPrepare;

    public static AnivsyDataPrepare getInstance(){
        if(anivsyDataPrepare == null){
            synchronized (AnivsyDataPrepare.class){
                if(anivsyDataPrepare == null){
                    anivsyDataPrepare = new AnivsyDataPrepare();
                }
            }
        }
        return anivsyDataPrepare;
    }

    public AnivsyDataPrepare(){
        init();
    }

    @Override
    public void init(){
        initAnivsies();
    }

    private void initAnivsies(){
        List<Anivsy> anivsyList = new ArrayList<>();
        Resources  resources = MyApplication.getContext().getResources();
        Anivsy mjyBirth = new Anivsy(MJYBIRTH, resources.getString(R.string.mjybirth), DateUtils.str2date("1994/09/30",DateUtils.FMT),0);
        Anivsy lfzbirth = new Anivsy(LFZBIRTH, resources.getString(R.string.lfzbirth), DateUtils.str2date("1995/02/14",DateUtils.FMT),0);
        Anivsy firstkiss = new Anivsy(FIRSTKISS, resources.getString(R.string.firstkiss), DateUtils.str2date("2012/12/1",DateUtils.FMT),0);
        Anivsy firstmakelove = new Anivsy(FIRSTMAKELOVE, resources.getString(R.string.firstmakelove), DateUtils.str2date("2014/04/06",DateUtils.FMT),0);
        Anivsy firstlove = new Anivsy(FIRSTLOVE, resources.getString(R.string.firstlove), DateUtils.str2date("2012/10/13",DateUtils.FMT),0);
        Anivsy firstdate = new Anivsy(FIRSTDATE, resources.getString(R.string.firstdate), DateUtils.str2date("2012/10/03",DateUtils.FMT),0);
        anivsyList.add(mjyBirth);
        anivsyList.add(lfzbirth);
        anivsyList.add(firstkiss);
        anivsyList.add(firstmakelove);
        anivsyList.add(firstlove);
        anivsyList.add(firstdate);
        AnivsyDBManager.insertAllAnivsy(anivsyList);
    }

}
