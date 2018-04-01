package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import bases.datashare.service.DataPreferencesManager;

/**
 * Created by LFZ on 2018/3/11.
 * 打开时加载的数据，regist机型注册，然后统一init
 */

public class DataPrepareManager {

    private static DataPrepareManager dataPrepareManager;
    private List<DataPrepareWithInstall> dataPrepareWithInstallList;

    public static DataPrepareManager getInstance(){
        if(dataPrepareManager == null){
            synchronized (DataPrepareManager.class){
                if(dataPrepareManager == null){
                    dataPrepareManager = new DataPrepareManager();
                }
            }
        }
        return dataPrepareManager;
    }

    public DataPrepareManager(){
        dataPrepareWithInstallList = new ArrayList<>();
    }

    /**
     * 注册初始化的数据的类
     * @param dataPrepare
     */
    public void registDataPrepare(DataPrepare dataPrepare){
        registDataPrepare(dataPrepare, false);
    }

    /**
     * 注册初始化的数据
     * @param dataPrepare
     * @param firstInstallPrepare 第一次安装
     */
    public void registDataPrepare(DataPrepare dataPrepare, boolean firstInstallPrepare){
        dataPrepareWithInstallList.add(new DataPrepareWithInstall(dataPrepare, firstInstallPrepare));
    }

    /**
     * 初始化数据
     */
    public void initDataPrepare(){
        for(DataPrepareWithInstall dataPrepareWithInstall : dataPrepareWithInstallList){
            //第一次安装全部初始化
            if(!DataPreferencesManager.hasInstall()){
                dataPrepareWithInstall.getDataPrepare().init();
            }else{
                if(!dataPrepareWithInstall.isFirstInstallPrepare()){
                    dataPrepareWithInstall.getDataPrepare().init();
                }
            }
        }
    }

    /**
     * 数据准备类包含是否是第一次安装才初始化
     */
    class DataPrepareWithInstall{
        public DataPrepareWithInstall(DataPrepare dataPrepare, boolean firstInstallPrepare){
            this.dataPrepare = dataPrepare;
            this.firstInstallPrepare = firstInstallPrepare;
        }

        private DataPrepare dataPrepare;
        private boolean firstInstallPrepare;     //true表示只有第一次安装才初始化

        public void setDataPrepare(DataPrepare dataPrepare) {
            this.dataPrepare = dataPrepare;
        }

        public DataPrepare getDataPrepare() {
            return dataPrepare;
        }

        public void setFirstInstallPrepare(boolean firstInstallPrepare) {
            this.firstInstallPrepare = firstInstallPrepare;
        }

        public boolean isFirstInstallPrepare() {
            return firstInstallPrepare;
        }
    }
}
