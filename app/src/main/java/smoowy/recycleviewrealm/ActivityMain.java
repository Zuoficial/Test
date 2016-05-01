package smoowy.recycleviewrealm;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import smoowy.recycleviewrealm.Adapter.Adapter;
import smoowy.recycleviewrealm.Adapter.SimpleTouchCallback;

public class ActivityMain extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Adapter mAdapter;
    Realm mRealm;
    RealmResults<DataManager> results;
    FloatingActionButton FAB;
    RealmChangeListener mRealmListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            mAdapter.update(results);

        }
    };
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AddInfo();

        }
    };


    private void AddInfo() {
        AddInformation addinfo = new AddInformation();
        addinfo.show(getSupportFragmentManager(),"add");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
        mRealm = Realm.getDefaultInstance();
        results = mRealm.where(DataManager.class).findAllAsync();
        FAB = (FloatingActionButton) findViewById(R.id.FAB);
        FAB.setOnClickListener(mClickListener);
        mAdapter = new Adapter(this,results,mRealm);
        mRecyclerView.setAdapter(mAdapter);
        SimpleTouchCallback callback = new SimpleTouchCallback(mAdapter);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRealm.addChangeListener(mRealmListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mRealm.removeChangeListener(mRealmListener);

    }
}
