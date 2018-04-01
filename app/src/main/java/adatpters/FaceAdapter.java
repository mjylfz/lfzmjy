package adatpters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lfz.lfzmjy.R;

import java.util.ArrayList;
import java.util.List;

import face.FaceRegist;
import utils.ToastUtils;
import view.SwipeItemLayout;

/**
 * Created by LFZ on 2018/3/12.
 * 所有面部的展示
 * 关于LayoutInflater.from(context).inflate的参数，可以参考http://blog.csdn.net/fesdgasdgasdg/article/details/72870280
 *
 * 如果root不为空，attachToRoot为false，则按照xml的最外层的参数，比如高度等内容
 * 如果root为空，attachToRoot为true，则给xml外层再套一层root
 */

public class FaceAdapter extends RecyclerView.Adapter<FaceAdapter.FaceViewHolder> {

    private Context context;
    private List<FaceRegist> registList;
    private int currentTouchingPosition = -1;

    public FaceAdapter(Context context, List<FaceRegist> registList){
        this.context = context;
        this.registList = registList;
    }

    public void setRegistList(List<FaceRegist> registList) {
        this.registList = registList;
        notifyDataSetChanged();
    }

    @Override
    public FaceAdapter.FaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.face_item, null);

        return new FaceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final FaceAdapter.FaceViewHolder holder, final int position) {
        holder.tvFace.setTag(position);
        holder.tvFace.setText(registList.get(position).getName());


        holder.swipeItemLayout.setSwipeListener(new SwipeListener() {
            @Override
            public void onSwipe() {
                //当前滑动的页面
                currentTouchingPosition = position;
                notifyDataSetChanged();
            }

        });

        //防止多个侧滑的内容同时打开
        if(currentTouchingPosition != position && currentTouchingPosition != -1){
            holder.swipeItemLayout.close();
        }

    }

    @Override
    public int getItemCount() {
        return registList == null ? 0 : registList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public interface SwipeListener{
        void onSwipe();
    }

    class FaceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tvFace;
        public SwipeItemLayout swipeItemLayout; //显示的部分
        public LinearLayout hideItem;           //隐藏的部分
        public Button btnEdit;
        public Button btnRemove;

        public FaceViewHolder(View itemView) {
            super(itemView);
            tvFace = (TextView)itemView.findViewById(R.id.facename);
            swipeItemLayout = (SwipeItemLayout)itemView.findViewById(R.id.main_item);
//            hideItem = (LinearLayout)itemView.findViewById(R.id.hide_item);
            btnEdit = (Button)itemView.findViewById(R.id.btn_edit);
            btnRemove = (Button)itemView.findViewById(R.id.btn_remove);

            //getWidth是显示的大小，getMeasure是原始定义的大小
            //但在这里getWidth拿不到宽度，因为还没有执行onLayout方法，getMeasureWidth也是0
            swipeItemLayout.setButtonCount(2);

            btnEdit.setOnClickListener(this);
            btnRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_edit:
                    ToastUtils.shortToast("edit");
                    swipeItemLayout.close();
                    break;
                case R.id.btn_remove:
                    ToastUtils.shortToast("remove");
                    swipeItemLayout.close();
                    break;
            }
        }
    }
}
