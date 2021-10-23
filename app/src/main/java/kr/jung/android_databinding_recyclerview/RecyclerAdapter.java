package kr.jung.android_databinding_recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/*
 * ViewHolder : 각 뷰를 보관하는 Holder 객체 (미리 그려놓은 View가 저장되어 있음)
 * ListView와 RecyclerView는 inflate(xml로 쓰여있는 각 View를 실제 객체롤 만드는 것)를 최소화 하기 위해 View를 재활용 한다.
 * 따라서 각 View에 내용을 업데이트하기 위해서는 findViewById를 호출해야하므로 느려질 수 있다.
 * 때문에 item을 각각 미리 그려놓고 아래로 스크롤 할 때마다 그려놓은 item만 보여줘서 빠르게 엑세스 할 수 있다.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Memo> items = new ArrayList<>();
    private Context mContext;
    private MemoDatabase db;

    public RecyclerAdapter(MemoDatabase db) {
        this.db = db;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<Memo> getItems() {return items;}

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        //!< View하나 더 만듦(rv_item_button)(버튼만 있는 아이템) 그 후 if문으로 lastitem 구별해서 마지막만 button으로 넘김

        mContext = viewGroup.getContext();
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder viewHolder, int position) {

        viewHolder.onBind(items.get(position),position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvContents;
        private Button btnSave;
        private int index;

        /* 스크롤시 매번 findViewById를 호출 할 것이 아니라 View 홀더로
                        View를 감싼 뒤 한번에 호출 했다가 스크롤 시 호출된 View만 띄워줌 */
        public ViewHolder(View view) {
            super(view);

            tvTitle = view.findViewById(R.id.tvTitle);
            tvContents = view.findViewById(R.id.tvContents);
            btnSave = view.findViewById(R.id.btnSave);
            btnSave.setOnClickListener(v -> editData(tvContents.getText().toString()));

        }
        public void onBind(Memo memo, int position){
            index = position;
            tvTitle.setText(memo.getTitle());
            tvContents.setText(memo.getContents());
        }

        public void editData(String contents){
            //!< 데이터 베이스에 값을 추가 ∴ Thread
            new Thread(() -> {
                items.get(index).setContents(contents);
                db.memoDao().update(items.get(index));
            }).start();
            Toast.makeText(mContext,"저장완료", Toast.LENGTH_SHORT).show();
        }


    }

    public void setItem(List<Memo> data) {
        items = data;
        notifyDataSetChanged();
    }

}
