package lewis.com.sign.utils;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import lewis.com.sign.R;


public class TypeDialog extends Dialog implements View.OnClickListener {
    private View view;
    private Activity context;
    private onSelectListener listener;
    private TextView title;
    private ImageView close;
    private ListView listView;

    public TypeDialog(@NonNull Activity context) {
        super(context, R.style.DialogSty);
        this.context = context;
        view = View.inflate(context, R.layout.dialog_type,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view);//这行一定要写在前面
        setCancelable(true);//点击外部不可dismiss
        setCanceledOnTouchOutside(true);
        Window window = this.getWindow();
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = (int) (window.getWindowManager().getDefaultDisplay().getHeight()*0.5);
        window.setAttributes(params);
        getWindow().setWindowAnimations(R.style.dialog_ani);
    }

    public void setContent(String s1, final List<String> list) {
        title=view.findViewById(R.id.title);
        close=view.findViewById(R.id.close);
        title.setText(s1);
        close.setOnClickListener(this);
        listView=view.findViewById(R.id.listView);
        listView.setAdapter(new DialogSelectAdapter(context,list));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                listener.onSelect(list.get(position));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.close:
                dismiss();
                break;
            default:
        }
    }

    public void setListener(onSelectListener listener){
        this.listener=listener;
    }

    public interface onSelectListener {
        public void onSelect(String model);
    }
}
