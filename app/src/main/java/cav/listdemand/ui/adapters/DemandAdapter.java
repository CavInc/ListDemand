package cav.listdemand.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cav.listdemand.R;

/**
 * Created by Kotov Alexandr 31.01.17.
 */
public class DemandAdapter extends  RecyclerView.Adapter<DemandAdapter.DemandViewHolder>{
    private Context mContext;

    public DemandAdapter(){

    }

    @Override
    public DemandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        return null;
    }

    @Override
    public void onBindViewHolder(DemandViewHolder holder, int position) {

    }



    @Override
    public int getItemCount() {
        return 0;
    }

    public static class DemandViewHolder extends RecyclerView.ViewHolder  {
        protected TextView mTitle,mContent;

        public DemandViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title_demand);
            mContent = (TextView) itemView.findViewById(R.id.content_demand);
        }
    }

}
