/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android鍥捐〃鍩虹被搴�
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package com.demo.xclcharts.view;

import java.util.LinkedList;
import java.util.List;

import org.xclcharts.chart.CircleChart;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.renderer.XEnum;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

/**
 * @ClassName CircleChart01View
 * @Description  鍥惧舰鍥句緥瀛�(鍗婂渾)
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class CircleChart01View extends GraphicalView {

	private String TAG = "CircleChart01View";
	private CircleChart chart = new CircleChart();	
	
	private List<PieData> mlPieData = new LinkedList<PieData>();		
	private String mDataInfo = "";
	
	public CircleChart01View(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setPercentage(0);	
		chartRender();
	}
	
	public CircleChart01View(Context context, AttributeSet attrs){   
        super(context, attrs);   
        setPercentage(0);	
		chartRender();
	 }
	 
	 public CircleChart01View(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			setPercentage(0);	
			chartRender();
	 }
	 
	
	@Override  
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
        //鍥炬墍鍗犺寖鍥村ぇ灏�
        //xml涓殑璁剧疆: android:layout_height="200dip"  
        int chartHeight = DensityUtil.dip2px(getContext(), 200 / 2); //100dip
        chart.setChartRange(w ,h + chartHeight); 
    }  		
	
			
	public void chartRender()
	{
		try {									
			//信息
			chart.setAttributeInfo(mDataInfo); 	
			//外环背景颜色
			chart.getPaintBgCircle().setColor((int) Color.rgb(178, 212, 233));
			//内环背景颜色
			chart.getPaintFillCircle().setColor((int) Color.rgb(92, 153, 199));
			
			//标签字体大小
			chart.getLabelPaint().setTextSize(20);
			chart.getPaintDataInfo().setTextSize(20);
			//半圆
			chart.setCircleType(XEnum.CircleType.HALF);	
			
			//数据	
			chart.setDataSource(mlPieData);		
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//鐧惧垎姣�
	public void setPercentage(int per)
	{						
		if(per < 50)
		{
			mDataInfo = "不错";
			chart.getLabelPaint().setColor(Color.WHITE);
			
			chart.getPaintDataInfo().setColor(Color.WHITE);
			
		}else if(per < 70){
			mDataInfo = "很好";
			chart.getLabelPaint().setColor((int)Color.rgb(72, 201, 176));
			chart.getPaintDataInfo().setColor(Color.WHITE);
		}else{
			mDataInfo = "棒极了";
			chart.getLabelPaint().setColor(Color.RED);
			chart.getPaintDataInfo().setColor(Color.RED);
		}
		//PieData(鏍囩锛岀櫨鍒嗘瘮锛屽湪楗煎浘涓搴旂殑棰滆壊)
		mlPieData.clear();		
		mlPieData.add(new PieData(Integer.toString(per)+"℃",per,(int)Color.rgb(72, 201, 176)));		
	}

	@Override 
    public void render(Canvas canvas) {
        try{
            chart.render(canvas);
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }

	/*
	@Override
	public List<XChart> bindChart() {
		// TODO Auto-generated method stub		
		List<XChart> lst = new ArrayList<XChart>();
		lst.add(chart);		
		return lst;
	}
	*/
}
