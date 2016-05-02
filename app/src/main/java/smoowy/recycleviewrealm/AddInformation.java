package smoowy.recycleviewrealm;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddInformation extends DialogFragment {

    EditText Nombre,Apellido;
    String  NombreS,ApellidoS;
    Button Add;
    Realm mRealm;
    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            NombreS = Nombre.getText().toString();
            ApellidoS = Apellido.getText().toString();
            DataManager dataManager = new DataManager(NombreS,ApellidoS);
            mRealm.beginTransaction();
            mRealm.copyToRealm(dataManager);
            mRealm.commitTransaction();
            Nombre.setText("");
            Apellido.setText("");


        }
    };


    public AddInformation() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL,R.style.DialogTheme);
        mRealm = Realm.getDefaultInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_information, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Nombre = (EditText) view.findViewById(R.id.Nombre);
        Apellido = (EditText) view.findViewById(R.id.Apellido);
        Add = (Button) view.findViewById(R.id.boton);
        Add.setOnClickListener(mClickListener);

    }
}
