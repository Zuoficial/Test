package smoowy.recycleviewrealm.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;
import smoowy.recycleviewrealm.DataManager;
import smoowy.recycleviewrealm.R;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> implements SwipeListener {
    public LayoutInflater mInflater;
    RealmResults<DataManager> results;
    private Realm mRealm;

    public Adapter(Context Context, RealmResults<DataManager> results,Realm mRealm) {
       mInflater = LayoutInflater.from(Context);
        this.mRealm = mRealm;
        update(results);


    }

    public void update(RealmResults<DataManager> results) {
        this.results = results;
        notifyDataSetChanged();
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycleviewline,parent,false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.Nombre.setText(results.get(position).getNombre());
        holder.Apelldo.setText(results.get(position).getApellido());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    @Override
    public void onSwipe(int position) {

        if (position < results.size()) {
            mRealm.beginTransaction();
            results.get(position).deleteFromRealm();
            mRealm.commitTransaction();
            notifyItemRemoved(position);
        }
    }

    public class Holder extends RecyclerView.ViewHolder {
        TextView Nombre,Apelldo;
        CardView mCard;


        public Holder(View itemView) {
            super(itemView);
            Nombre = (TextView) itemView.findViewById(R.id.Nombre);
            Apelldo = (TextView) itemView.findViewById(R.id.Apellido);
            mCard = (CardView) itemView.findViewById(R.id.card_view);
        }
    }
}
