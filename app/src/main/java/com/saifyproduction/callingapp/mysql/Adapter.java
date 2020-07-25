package com.saifyproduction.callingapp.mysql;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.saifyproduction.callingapp.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable {
    List<Donnees> _pets, _petsFilter;
    private Context _context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public Adapter(List<Donnees> pets, Context context, RecyclerViewClickListener listener) {
        this._pets = pets;
        this._context = context;
        this._petsFilter = _petsFilter;
        this.mListener = listener;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, mListener);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.mNameEnt.setText(_pets.get(position).getNom_entreprise());
        holder.mRespo.setText(_pets.get(position).getResponsable());
        holder.mPhone.setText(_pets.get(position).getPhone());
        holder.mMail.setText(_pets.get(position).getEmail());
        holder.mType.setText(_pets.get(position).getDomicile());
        holder.mLieu.setText(_pets.get(position).getDomicile());
        holder.mLat.setText((int) _pets.get(position).getP_latitude());
        holder.mLongi.setText((int) _pets.get(position).getP_longitude());
        holder.mQuartier.setText(_pets.get(position).getQuartier());
        holder.mCommun.setText(_pets.get(position).getCommune());
        holder.mAvenu.setText(_pets.get(position).getAvenue());
        holder.mNumParc.setText(_pets.get(position).getNumero_home());
        holder.mDate.setText(_pets.get(position).getDate_save());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.ic_likeon);
        requestOptions.error(R.drawable.ic_likeon);

        Glide.with(_context)
                .load(_pets.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

        final Boolean love = _pets.get(position).getLove();

        if (love){
            holder.mLove.setImageResource(R.drawable.ic_likeon);
        } else {
            holder.mLove.setImageResource(R.drawable.like_of);
        }
    }
    @Override
    public int getItemCount() {
        return _pets.size();
    }

    @Override
    public Filter getFilter() {
        if (filter==null) {
            filter=new CustomFilter((ArrayList<Donnees>) _petsFilter,this);
        }
        return filter;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView mLove;
        private TextView mNameEnt,mRespo,mPhone, mMail,mType,mLieu,mLat,mLongi,mQuartier,mCommun,mAvenu,mNumParc,mDate;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, RecyclerViewClickListener listener) {
            super(itemView);
            mNameEnt = itemView.findViewById(R.id.txt_entreprise1);
            mRespo = itemView.findViewById(R.id.txt_respon1);
            mPhone = itemView.findViewById(R.id.txt_phone1);
            mMail = itemView.findViewById(R.id.txt_email1);
            mMail = itemView.findViewById(R.id.txt_email1);
            mType = itemView.findViewById(R.id.txt_type1);
            mLieu = itemView.findViewById(R.id.txt_domicile1);
            mLat = itemView.findViewById(R.id.txt_lat1);
            mLongi = itemView.findViewById(R.id.txt_longi1);
            mQuartier = itemView.findViewById(R.id.txt_quartier1);
            mCommun = itemView.findViewById(R.id.txt_commune1);
            mAvenu = itemView.findViewById(R.id.txt_avenu1);
            mNumParc = itemView.findViewById(R.id.txt_numero_parcel1);
            mPicture = itemView.findViewById(R.id.picture);
            mLove = itemView.findViewById(R.id.love);
            mDate = itemView.findViewById(R.id.date);
            mRowContainer = itemView.findViewById(R.id.row_container);
            mListener = listener;

            mRowContainer.setOnClickListener(this);
            mLove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.row_container:
                    mListener.onRowClick(mRowContainer, getAdapterPosition());
                    break;
                case R.id.love:
                    mListener.onLoveClick(mLove, getAdapterPosition());
                    break;
                default:
                    break;
            }
        }
    }
    public interface RecyclerViewClickListener {
        void onRowClick(View view, int position);
        void onLoveClick(View view, int position);
    }
}
