package smoowy.recycleviewrealm;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import io.realm.Sort;
import smoowy.recycleviewrealm.Adapter.Adapter;
import smoowy.recycleviewrealm.Adapter.SimpleTouchCallback;

public class ActivityMain extends AppCompatActivity {

    RecyclerView mRecyclerView;
    Adapter mAdapter;
    static Realm mRealm;
    RealmResults<DataManager> mResults;
    FloatingActionButton FAB,FAB2;
    RealmChangeListener mRealmListener = new RealmChangeListener() {
        @Override
        public void onChange() {
            mAdapter.update(mResults);

        }
    };
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();

            switch(id) {

                case R.id.FAB: {
                    AddInfo();
                    break;
                }
                case R.id.FAB2: {
                    Agregar20();
                    break;
                }
            }

        }
    };

    private void Agregar20() {
        DataManager datamanger = new DataManager();

        for(char alphabet = 'A'; alphabet <= 'Z';alphabet++) {
            datamanger.setNombre("Nombre");
            datamanger.setApellido(String.valueOf(alphabet));
            mRealm.beginTransaction();
            mRealm.copyToRealm(datamanger);
            mRealm.commitTransaction();
        }

    }


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
        mResults = mRealm.where(DataManager.class).findAllSortedAsync("Apellido", Sort.ASCENDING);
        FAB = (FloatingActionButton) findViewById(R.id.FAB);
        FAB2 = (FloatingActionButton) findViewById(R.id.FAB2);
        FAB.setOnClickListener(mClickListener);
        FAB2.setOnClickListener(mClickListener);
        mAdapter = new Adapter(this, mResults,mRealm);
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
