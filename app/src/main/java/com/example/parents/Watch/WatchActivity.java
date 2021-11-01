package com.example.parents.Watch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import com.example.parents.R;
import com.example.parents.View.TitleLayout;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.parents.LoginRegister.LoginActivity.makeStatusBarTransparent;

public class WatchActivity extends AppCompatActivity {

    private SurfaceView watch_sv_camera;
    private Button watch_btn_preview, watch_btn_colse;
    private SurfaceHolder surfaceHolder;
    private TitleLayout watch_tit;
    private BarChart watch_bc_chart;
    private TextView watch_tv_time, watch_tv_usetime;
    private Camera camera;
    private boolean isPreview = false;
    private List<BarEntry> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();    //得到窗口
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);    //设置高亮
        setContentView(R.layout.activity_watch);
        makeStatusBarTransparent(WatchActivity.this);

        initView();
    }

    private void initView() {
        // 设置标题
        watch_tit = findViewById(R.id.watch_tit);
        watch_tit.setTitle("学习监测");

        watch_sv_camera = findViewById(R.id.watch_sv_camera);
        surfaceHolder = watch_sv_camera.getHolder();
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        watch_btn_preview = findViewById(R.id.watch_btn_preview);
        watch_btn_preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPreview) {
                    // 如果没打开->打开摄像头
                    camera = Camera.open();
                    isPreview = true;
                }
                try {
                    watch_btn_preview.setText("对焦");
                    camera.setPreviewDisplay(surfaceHolder);// 设置预览
                    Camera.Parameters parameters = camera.getParameters();// 获取摄像头参数

                    parameters.setPictureFormat(PixelFormat.JPEG);// 设置图片为jpg
                    parameters.set("jpeg-quality", 80);// 设置图片质量

                    camera.setParameters(parameters);   // 重新设置摄像头参数
                    camera.startPreview();              // 开始预览
                    camera.setDisplayOrientation(90);   // 不加的话，预览的图像就是横的
                    camera.autoFocus(null);         // 自动对焦

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        watch_btn_colse = findViewById(R.id.watch_btn_colse);
        watch_btn_colse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                camera.stopPreview();
                camera.release();
            }
        });

        watch_bc_chart = findViewById(R.id.watch_bc_chart);
        chart();

        watch_tv_time = findViewById(R.id.watch_tv_time);
        Date time = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日");
        watch_tv_time.setText(df.format(time));

        watch_tv_usetime = findViewById(R.id.watch_tv_usetime);
    }

    private void chart(){
        //添加数据
        list.add(new BarEntry(1,7));     //其中两个数字对应的分别是   X轴   Y轴
        list.add(new BarEntry(2,13));
        list.add(new BarEntry(3,12));
        list.add(new BarEntry(4,6));
        list.add(new BarEntry(5,3));

        BarDataSet barDataSet = new BarDataSet(list,"时间");   //list是你这条线的数据  "语文" 是你对这条线的描述
        BarData barData = new BarData(barDataSet);
        watch_bc_chart.setData(barData);

        //背景
        watch_bc_chart.setBackgroundColor(0x00FFFFFF);          //背景颜色
        watch_bc_chart.getXAxis().setDrawGridLines(false);      //是否绘制X轴上的网格线（背景里面的竖线）
        watch_bc_chart.getAxisLeft().setDrawGridLines(false);   //是否绘制Y轴上的网格线（背景里面的横线）

        //对于右下角一串字母的操作
        watch_bc_chart.getDescription().setEnabled(false);                //是否显示右下角描述
        watch_bc_chart.getDescription().setText("这是修改那串英文的方法");    //修改右下角字母的显示
        watch_bc_chart.getDescription().setTextSize(20);                  //字体大小
        watch_bc_chart.getDescription().setTextColor(Color.RED);          //字体颜色

        //图例
        Legend legend = watch_bc_chart.getLegend();
        legend.setEnabled(false);    //是否显示图例
        legend.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);    //图例的位置

        //X轴
        XAxis xAxis = watch_bc_chart.getXAxis();
        xAxis.setDrawGridLines(false);          //是否绘制X轴上的网格线（背景里面的竖线）
        xAxis.setAxisLineColor(0xFFD7D7D7);     //X轴颜色
        xAxis.setAxisLineWidth(1);              //X轴粗细
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);        //X轴所在位置   默认为上面
        xAxis.setValueFormatter(new IAxisValueFormatter() {   //X轴自定义坐标
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                if (v == 1){
                    return "故事";
                }
                if (v == 2){
                    return "视频";
                }
                if (v == 3){
                    return "学习";
                }
                if (v == 4){
                    return "作业";
                }
                if (v == 5){
                    return "社交";
                }
                return "";
            }
        });
        xAxis.setAxisMaximum(6);   //X轴最大数值
        xAxis.setAxisMinimum(0);   //X轴最小数值
        //X轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        xAxis.setLabelCount(6,false);

        //Y轴
        YAxis AxisLeft = watch_bc_chart.getAxisLeft();
        AxisLeft.setDrawGridLines(true);  //是否绘制Y轴上的网格线（背景里面的横线）
        AxisLeft.setAxisLineColor(0xFFD7D7D7);  //Y轴颜色
        AxisLeft.setAxisLineWidth(2);           //Y轴粗细
        /*AxisLeft.setValueFormatter(new IAxisValueFormatter() {  //Y轴自定义坐标
            @Override
            public String getFormattedValue(float v, AxisBase axisBase) {
                for (int a = 0 ;a < 16 ;a++){
                    if (a == v){
                        return "第"+a+"个";
                    }
                }
                return "";
            }
        });*/
        AxisLeft.setAxisMaximum(15);    //Y轴最大数值
        AxisLeft.setAxisMinimum(0);     //Y轴最小数值
        //Y轴坐标的个数    第二个参数一般填false     true表示强制设置标签数 可能会导致X轴坐标显示不全等问题
        AxisLeft.setLabelCount(15,false);

        //是否隐藏右边的Y轴（不设置的话有两条Y轴 同理可以隐藏左边的Y轴）
        watch_bc_chart.getAxisLeft().setEnabled(false);

        //柱子
        barDataSet.setColor(0xFF3DDC84);                //柱子的颜色
        //barDataSet.setColors(Color.BLACK,Color.BLUE);   //设置柱子多种颜色  循环使用
        barDataSet.setBarBorderColor(Color.WHITE);      //柱子边框颜色
        barDataSet.setBarBorderWidth(0);                //柱子边框厚度
        barDataSet.setBarShadowColor(Color.RED);
        barDataSet.setHighlightEnabled(true);          //选中柱子是否高亮显示  默认为true
        barDataSet.setStackLabels(new String[]{"aaa","bbb","ccc"});
        //定义柱子上的数据显示    可以实现加单位    以及显示整数（默认是显示小数）
        barDataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float v, Entry entry, int i, ViewPortHandler viewPortHandler) {
                if (entry.getY() == v){
                    return v + "min";
                }
                return "";
            }
        });

        //数据更新
        watch_bc_chart.notifyDataSetChanged();
        watch_bc_chart.invalidate();

        //动画（如果使用了动画可以则省去更新数据的那一步）
        watch_bc_chart.animateY(2000); //在Y轴的动画  参数是动画执行时间 毫秒为单位
        //watch_bc_chart.animateX(2000); //X轴动画
        //watch_bc_chart.animateXY(2000,2000);//XY两轴混合动画
    }

    protected void onPause() {
        super.onPause();
        if(camera != null){
            camera.stopPreview();
            camera.release();
        }
    }

}