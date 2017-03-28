package com.pmcc.base.util;

import org.apache.poi.hssf.record.formula.functions.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 用于返回结果
 * Created by Admin on 2016/1/25.
 */
public class ResultBean<T> {
    public static final String SUCCESS = "1";
    public static final String FAIL = "0";
    public static final String OUTLINE = "-1";
    private String resultCode; // 返回结果类型
    private String resultDesc; // 返回结果描述
    private String resultData; // 返回的数据
    private long total; // 总数量，加载grid用
    private int curPage; // 当前页数
    private List<T> extData; // 数据

    private int sCount = 0; // 成功条数
    private int fCount = 0; // 失败条数
    private boolean result = true;

    public ResultBean() {
        super();
    }

    public ResultBean(String resultCode, String resultDesc, String resultData) {
        super();
        this.resultCode = resultCode;
        this.resultDesc = resultDesc;
        this.resultData = resultData;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(String resultDesc) {
        this.resultDesc = resultDesc;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<T> getExtData() {
        return extData;
    }

    public void setExtData(List<T> extData) {
        this.extData = extData;
    }

    public int getsCount() {
        return sCount;
    }

    public void setsCount(int sCount) {
        this.sCount = sCount;
    }

    public int getfCount() {
        return fCount;
    }

    public void setfCount(int fCount) {
        this.fCount = fCount;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
