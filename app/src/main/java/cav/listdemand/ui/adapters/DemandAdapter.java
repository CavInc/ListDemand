package cav.listdemand.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cav.listdemand.R;
import cav.listdemand.data.managers.DataManager;
import cav.listdemand.data.storage.models.DemandModel;

/**
 * Created by Kotov Alexandr 31.01.17.
 */
public class DemandAdapter extends  RecyclerView.Adapter<DemandAdapter.DemandViewHolder>{
    private Context mContext;
    private List<DemandModel> mData;

    public DemandAdapter(List<DemandModel> data){
        this.mData= data;

    }

    @Override
    public DemandViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View contentView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_item,parent,false);
        return new DemandViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(DemandViewHolder holder, int position) {
        DemandModel demand = mData.get(position);
        holder.mTitle.setText(demand.getTitle());
        holder.mContent.setText(demand.getBody());

    }



    @Override
    public int getItemCount() {
        if (mData!= null) {
            return mData.size();
        }
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
