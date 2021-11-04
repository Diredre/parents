package com.example.parents.Homework;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.example.parents.R;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class HomeworkAdapter extends RecyclerView.Adapter<HomeworkAdapter.ViewHolder> {

    private List<HomeworkBean> hwList;
    private Context context;

    DateFormat format= DateFormat.getDateTimeInstance();
    Calendar calendar= Calendar.getInstance(Locale.CHINA);

    public static class ViewHolder extends RecyclerView.ViewHolder{
        EditText et;
        ImageView iv_cal;
        TextView tv_del;
        TextView timetv;

        public ViewHolder(View v){
            super(v);
            this.et = v.findViewById(R.id.item_puthw_et);
            this.iv_cal = v.findViewById(R.id.item_puthw_iv_cal);
            this.tv_del = v.findViewById(R.id.item_puthw_tv_del);
            this.timetv = v.findViewById(R.id.item_puthw_tv);
        }
    }


    public HomeworkAdapter(Context context, List<HomeworkBean> hwList) {
        this.context = context;
        this.hwList = hwList;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_puthw, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HomeworkAdapter.ViewHolder holder, int position) {
        HomeworkBean homework = hwList.get(position);

        holder.et.setText(homework.getCon());

        Glide.with(context)
                .load("https://z3.ax1x.com/2021/10/31/IS4QUI.png")
                .into(holder.iv_cal);
        holder.iv_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(context,  4, holder.timetv, calendar);
                Glide.with(context)
                        .load("https://z3.ax1x.com/2021/10/30/5xnjr8.png")
                        .into(holder.iv_cal);
            }
        });

        /*Glide.with(context)
                .load("https://z3.ax1x.com/2021/10/30/5x8T3Q.png")
                .into(holder.iv_ach);*/
        holder.tv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItemData(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return hwList.size();
    }

    /**
     * 日期选择
     */
    public static void showDatePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(context, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText("您选择了：" + year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日");
            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     */
    public static void showTimePickerDialog(Context context, int themeResId, final TextView tv, Calendar calendar) {
        // Calendar c = Calendar.getInstance();
        // 创建一个TimePickerDialog实例，并把它显示出来
        new TimePickerDialog(context, themeResId,
                // 绑定监听器
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        tv.setText("您选择了：" + hourOfDay + "时" + minute + "分");
                    }
                }
                // 设置初始时间
                , calendar.get(Calendar.HOUR_OF_DAY)
                , calendar.get(Calendar.MINUTE)
                // true表示采用24小时制
                , true).show();
    }

    /**
     * 删除数据
     */
    public void removeItemData(int postition){
        hwList.remove(postition);
        notifyItemRemoved(postition);
        //notifyDataSetChanged();     //防止错位
    }

}
