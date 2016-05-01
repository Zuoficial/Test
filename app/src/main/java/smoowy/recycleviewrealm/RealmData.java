package smoowy.recycleviewrealm;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmData extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration configuration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(configuration);
    }
}
