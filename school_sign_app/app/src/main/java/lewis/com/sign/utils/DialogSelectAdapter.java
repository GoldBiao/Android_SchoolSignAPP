package lewis.com.sign.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lewis.com.sign.R;


public class DialogSelectAdapter extends BaseAdapter {

    private Activity mActivity;
    private List<String> mList=new ArrayList<>();

    public DialogSelectAdapter(Activity activity, List<String> list){
        this.mActivity=activity;
        this.mList=list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            convertView=mActivity.getLayoutInflater().inflate(R.layout.item_select_dialog,null);
            holder=new ViewHolder();
            holder.name=convertView.findViewById(R.id.name);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(mList.get(position));
        return convertView;
    }

    class ViewHolder{
        TextView name;
    }
}
