package com.example.administrator.weathertest.view;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.weathertest.R;
import com.example.administrator.weathertest.db.City;
import com.example.administrator.weathertest.db.County;
import com.example.administrator.weathertest.db.Province;
import com.example.administrator.weathertest.db.RootCity;
import com.example.administrator.weathertest.db.RootCounty;
import com.example.administrator.weathertest.db.RootProvince;
import com.example.administrator.weathertest.utils.HttpUtil;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ChooseFragment extends Fragment {
    public ArrayAdapter<String> arrayAdapter;
    private List<String> dataList=new ArrayList<>();
    private List<Province> provinceList;
    private List<City> cityList;
    private List<County> countyList;
    private ListView listView;
    private TextView title;
    private Button backButton;
    private final  int PROVINCE_LIST_FLAG=1;
    private final int CITY_LIST_FLAG=2;
    private final int COUNTY_LIST_FLAG=3;
    private int listNow;
    private final String BASE_URL ="http://guolin.tech/";
    private Province provinceSelected;
    private City citySelected;
    private County countySelected;
    private  ProgressDialog progressDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.choose,container,false);
        title=view.findViewById(R.id.title);
        listView=view.findViewById(R.id.place_list);
        backButton=view.findViewById(R.id.back_button);
        arrayAdapter=new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(arrayAdapter);
        Log.d("呵呵哈哈哈","lllllll");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (listNow==PROVINCE_LIST_FLAG){
                    provinceSelected=provinceList.get(position);
                    queryCity();
                }
                else if(listNow==CITY_LIST_FLAG){
                    citySelected=cityList.get(position);
                    queryCounty();
                }
            }
        });
        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(listNow==COUNTY_LIST_FLAG){
                    queryCity();
                }
                else if(listNow==CITY_LIST_FLAG){
                    queryProvince();
                }
            }
        });
        Log.d("哦哦哦哦哦","jjjjjjjjj");
        queryProvince();
    }
    //查询全国所有省份
    public void queryProvince() {
        Log.d("遍历省份","数据库查询");
            title.setText("中国");
            backButton.setVisibility(View.GONE);
            provinceList= DataSupport.findAll(Province.class);
            if(provinceList.size()>0){
                dataList.clear();
                for(Province province:provinceList){
                    dataList.add(province.getProvinceName());
                }
                arrayAdapter.notifyDataSetChanged();
                listView.setSelection(0);
                listNow=PROVINCE_LIST_FLAG;
        }
        else {
            Log.d("遍历省份","调用queryProvinceFromServer();");
            queryProvinceFromServer(BASE_URL);
        }
    }


    //查询全国所有城市
    public void queryCity() {
        title.setText(provinceSelected.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList=DataSupport.where("provinceId=?",String.
                valueOf(provinceSelected.getID())).find(City.class);
        if(cityList.size()>0){
            dataList.clear();
            for (City city:cityList){
                dataList.add(city.getCityName());
            }
            arrayAdapter.notifyDataSetChanged();
            listView.setSelection(0);
            listNow=CITY_LIST_FLAG;
        }
        else {
            int provinceCode=provinceSelected.getProvinceCode();
            queryCityFromServer(BASE_URL,provinceCode);
        }
}
     //获取全国县份信息
    public void queryCounty() {
        title.setText(citySelected.getCityName());
        backButton.setVisibility(View.VISIBLE);
        countyList=DataSupport.where("cityid=?",String.
                valueOf(citySelected.getID())).find(County.class);
        if(countyList.size()>0){
            dataList.clear();
            for(County county:countyList){
                dataList.add(county.getCountyName());
            }
            arrayAdapter.notifyDataSetChanged();
            listView.setSelection(0);
            listNow=COUNTY_LIST_FLAG;
        }
        else {
            int provinceCode=provinceSelected.getProvinceCode();
            int cityCode=citySelected.getCityCode();
            queryCountyFromServer(BASE_URL,provinceCode,cityCode);
        }
    }

    /*从服务器获取省份信息*/
    private void queryProvinceFromServer( String baseUrl) {
        Log.d("遍历省份","斤斤计较");
        Observable<RootProvince> provinceObservable;
        provinceObservable = HttpUtil.sendHttpRequest(baseUrl).getProvinces();
        provinceObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RootProvince, ArrayList<Province>>() {
                    @Override
                    public ArrayList<Province> call(RootProvince rootProvince) {
                        return rootProvince.getProvinceList();
                    }
                })
                .subscribe(new Subscriber<ArrayList<Province>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError");
                    }

                    @Override
                    public void onNext(ArrayList<Province> provinces) {
                        for (Province province : provinces) {
                            province.save();
                            Log.d("省份名字",province.getProvinceName());
                        }
                        queryProvince();
                    }
                });
    }

    /*从服务器获取城市信息*/
    private void queryCityFromServer(String baseUrl,int provinceCode){
        Observable<RootCity> cityObservable;
        cityObservable=HttpUtil.sendHttpRequest(baseUrl).getCities(provinceCode);
        cityObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RootCity,ArrayList<City>>(){
                    @Override
                    public ArrayList<City> call(RootCity rootCity) {
                        return rootCity.getCityList();
                    }
                })
                .subscribe(new Subscriber<ArrayList<City>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TAG","onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG","onError");
                    }

                    @Override
                    public void onNext(ArrayList<City> cities) {
                       for(City city:cities) {
                           city.save();
                       }
                       queryCity();
                    }

                });
    }

    /*从服务器获取县信息*/
    private void queryCountyFromServer(String baseUrl,
                                                    int provinceCode,int cityCode) {
        Observable<RootCounty> countyObservable;
        countyObservable = HttpUtil.sendHttpRequest(baseUrl).getCounties(provinceCode, cityCode);
        countyObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Func1<RootCounty, ArrayList<County>>() {
                    @Override
                    public ArrayList<County> call(RootCounty rootCounty) {
                        return rootCounty.getCountyList();
                    }
                })
                .subscribe(new Subscriber<ArrayList<County>>() {
                    @Override
                    public void onCompleted() {
                        Log.d("TAG", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG", "onError");
                    }

                    @Override
                    public void onNext(ArrayList<County> counties) {
                        for (County county:counties) {
                            county.save();
                        }
                        queryCounty();
                    }
                });
    }


    private void showProgressDialog(){
        if(progressDialog==null){
            progressDialog=new ProgressDialog(getActivity());
            progressDialog.setMessage("请稍后...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
    private void closeProgressDialog(){
        if (progressDialog!=null)
            progressDialog.dismiss();
    }

}

