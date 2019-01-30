package com.tong.gao.walletuser.ui.fragments.exchangeCoin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.ExchangeBTCBean;
import com.tong.gao.walletuser.bean.request.RequestBtcExchangeApply;
import com.tong.gao.walletuser.bean.request.RequestExchangeApplyList;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeApply;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeRate;
import com.tong.gao.walletuser.bean.response.ResponseExchangeApplyList;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.CalculateUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentExchangeCoin extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.et_input_ab_number)
    EditText etInputAbNumber;
    @BindView(R.id.et_input_btc_number)
    EditText etInputBtcNumber;
    @BindView(R.id.et_input_btc_address)
    EditText etInputBtcAddress;
    @BindView(R.id.btn_submit_apply)
    Button btnSubmitApply;
    @BindView(R.id.rl_recycler_my_apply)
    RecyclerView rlRecyclerMyApply;
    Unbinder unbinder;



    private boolean flag = true;

    private ResponseBtcExchangeRate btcExchangeRate;

    private String abNumber,btcNumber,btcAddress;

    private List<ExchangeBTCBean> dataList;

    private ExChangeAdapter adapter;

    private String dealing = "1";
    private String hadRemit = "2";
    private String hadRejected = "3";
    private String hadRevocation = "4";
//    1-处理中2-已汇出3-已驳回 4-已撤销

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_coin, container, false);
        unbinder = ButterKnife.bind(this, view);
        requestData();
        initView();
        return view;
    }

    private void requestData() {

        NetWorks.queryBtcExchangeRate(new Observer<ResponseBtcExchangeRate>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("d");
            }

            @Override
            public void onNext(ResponseBtcExchangeRate responseBtcExchangeRate) {
                if(null != responseBtcExchangeRate && MyConstant.resultCodeIsOK .equals(responseBtcExchangeRate.getErrcode())){
                    btcExchangeRate = responseBtcExchangeRate;
                    updateUI();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }

    private void updateUI() {
        LogUtils.d("ExchangeCoin","btcExchangeRate.getExchangeRate():"+btcExchangeRate.getExchangeRate());
        tvRate.setText("汇率 "+btcExchangeRate.getExchangeRate());

        loadBtcExchangeApplyList();
    }

    private void initView() {
        tvTitleBarTitle2.setText("兑换比特币");
        flBack.setOnClickListener(this);
        btnSubmitApply.setOnClickListener(this);


        etInputAbNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                if (flag) {
                    flag = false;
                    if (TextUtils.isEmpty(text) || btcExchangeRate == null) {
                        etInputBtcNumber.setText("");
                        return;
                    }
                    String result = CalculateUtils.mul(text, btcExchangeRate.getExchangeRate(), 4);
                    etInputBtcNumber.setText(result);
                } else {
                    flag = true;
                }
            }
        });

        etInputBtcNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                if (flag) {
                    flag = false;
                    if (TextUtils.isEmpty(text) || btcExchangeRate == null || "0".equals(text)) {
                        etInputAbNumber.setText("");
                        return;
                    }
                    String result = CalculateUtils.div(text, btcExchangeRate.getExchangeRate(), 4);
                    etInputAbNumber.setText(result);
                } else {
                    flag = true;
                }
            }
        });


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlRecyclerMyApply.setLayoutManager(layoutManager);

        rlRecyclerMyApply.setAdapter(adapter = new ExChangeAdapter(R.layout.item_exchange_apply_list,dataList));


        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showNomalShortToast("兑换详情position"+position);

                Bundle bundle = new Bundle();
                bundle.putString(MyConstant.btcExchangeApplyDetailKey,dataList.get(position).getBtcApplyId());

                NavHostFragment.findNavController(FragmentExchangeCoin.this)
                        .navigate(R.id.action_fragmentExchangeCoin_to_fragmentExchangeCoinDetail,bundle);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                rlRecyclerMyApply.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            adapter.loadMoreEnd();
                        } else {
                            if (isLoadMoreSuccess) {
                                //成功获取更多数据
                                pageno++;
                                loadMore();

                            } else {
                                //获取更多数据失败
                                isLoadMoreSuccess = false;
                                Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                adapter.loadMoreFail();
                            }
                        }

                    }
                },500);

            }
        },rlRecyclerMyApply);

    }

    private void loadMore() {

        NetWorks.queryBtcExchangeApplyList(new RequestExchangeApplyList(type, "" + pageno, "" + pagesize), new Observer<ResponseExchangeApplyList>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseExchangeApplyList responseExchangeApplyList) {
                if(null != responseExchangeApplyList && MyConstant.resultCodeIsOK.equals(responseExchangeApplyList.getErrcode())){

                    TOTAL_COUNTER = Integer.parseInt(responseExchangeApplyList.getPage().getSum()) ;

                    dataList = responseExchangeApplyList.getExchangeBTC();

                    if(null !=dataList && dataList.size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapter.addData(dataList);
                                mCurrentCounter = adapter.getData().size();
                                adapter.loadMoreComplete();
                            }
                        });
                    }
                }
            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                getActivity().finish();

                break;

            case R.id.btn_submit_apply:             //提交申请

                abNumber = etInputAbNumber.getText().toString();
                btcNumber = etInputBtcNumber.getText().toString();
                btcAddress = etInputBtcAddress.getText().toString();

                if(StringUtils.isEmpty(abNumber) || StringUtils.isEmpty(btcNumber) || StringUtils.isEmpty(btcAddress)){
                    ToastUtils.showNomalShortToast("输入信息不能有空");
                }else{
                    if(null != btcExchangeRate){
                        submitApply();
                    }else{
                        ToastUtils.showNomalShortToast("稍后重试...");
                    }

                }

                break;

        }
    }

    private void submitApply() {

        NetWorks.btcExchangeApply(new RequestBtcExchangeApply(btcExchangeRate.getExchangeRate(),
                        abNumber,btcNumber,btcAddress),
                new Observer<ResponseBtcExchangeApply>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("开始订阅"+d);
            }

            @Override
            public void onNext(ResponseBtcExchangeApply responseBtcExchangeApply) {

                if(null != responseBtcExchangeApply && MyConstant.resultCodeIsOK .equals(responseBtcExchangeApply.getErrcode())){
                    //兑换成功
                    ToastUtils.showNomalShortToast("提交申请成功");
//                    loadBtcExchangeApplyList();
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete");
            }
        });

    }


    private int mCurrentCounter =0;
    private int TOTAL_COUNTER   =0;
    private String type = "0";//0-全部 1-处理中 2-已处理
    private int pageno = 1;
    private int pagesize = 6;
    private boolean isLoadMoreSuccess = true;

    private void loadBtcExchangeApplyList() {

        NetWorks.queryBtcExchangeApplyList(new RequestExchangeApplyList(type, "" + pageno, "" + pagesize), new Observer<ResponseExchangeApplyList>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseExchangeApplyList responseExchangeApplyList) {
                if(null != responseExchangeApplyList && MyConstant.resultCodeIsOK.equals(responseExchangeApplyList.getErrcode())){

                    TOTAL_COUNTER =Integer.parseInt(responseExchangeApplyList.getPage().getSum()) ;
                    dataList = responseExchangeApplyList.getExchangeBTC();

                    adapter.addData(dataList);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        });

    }


    public class ExChangeAdapter extends BaseQuickAdapter<ExchangeBTCBean, BaseViewHolder> {

        public ExChangeAdapter(int layoutResId,@Nullable List<ExchangeBTCBean> data) {
            super(layoutResId,data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ExchangeBTCBean item) {

            if (dealing.equals(item.getStatus())) {
                helper.setText(R.id.tv_exchange_status, "处理中");
            }else if (hadRemit.equals(item.getStatus())) {
                helper.setText(R.id.tv_exchange_status, "已汇出");
            }else if (hadRejected.equals(item.getStatus())) {
                helper.setText(R.id.tv_exchange_status, "已驳回");
            }else if (hadRevocation.equals(item.getStatus())) {
                helper.setText(R.id.tv_exchange_status, "已撤销");
            }
            helper.setText(R.id.tv_exchange_rate, CalculateUtils.div(item.getNumber(), "1", 2) + " AB = "+ CalculateUtils.div(item.getBtcNumber(), "1", 2) +" BTC");
        }

    }
}
