package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapplicationfin.R;
import com.example.myapplicationfin.activity_patient;

public class fragment_myinfo extends Fragment {

    TextView txtid, txtaddress, txtbirthdate, txtcid, txtemail, txtpass, txtnames, txtphone, txtrole;
    String id="", address="", birthdate="", cid="", email="", pass = "", names="", surnames="", phone="", role = "";
    TextView txtPass;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_myinfo() {
        // Required empty public constructor
    }

    public static fragment_myinfo newInstance(String param1, String param2) {
        fragment_myinfo fragment = new fragment_myinfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo, container, false);
        txtaddress = (TextView) view.findViewById(R.id.address);
        txtbirthdate = (TextView) view.findViewById(R.id.birthdate);
        txtcid = (TextView) view.findViewById(R.id.cid);
        txtemail = (TextView) view.findViewById(R.id.email);
        txtpass = (TextView) view.findViewById(R.id.password);
        txtnames = (TextView) view.findViewById(R.id.names_surnames);
        txtphone = (TextView) view.findViewById(R.id.phone);
        txtrole = (TextView) view.findViewById(R.id.role);
        viewData();
        return view;
    }

    public void viewData(){
        address = this.getArguments().getString("address");
        birthdate = this.getArguments().getString("birthdate");
        cid = this.getArguments().getString("cid");
        email = this.getArguments().getString("email");
        pass = this.getArguments().getString("pass");
        names = this.getArguments().getString("names") + " " + this.getArguments().getString("surnames");
        phone = this.getArguments().getString("phone");
        role = this.getArguments().getString("role");

        txtaddress.setHint(address);
        txtbirthdate.setHint(birthdate);
        txtcid.setHint(cid);
        txtemail.setHint(email);
        txtpass.setHint(pass);
        txtnames.setHint(names);
        txtphone.setHint(phone);

        if (role.equals("A"))
            txtrole.setHint("Administrator");
        else if (role.equals("T"))
            txtrole.setHint("Therapist");
        else if (role.equals("P"))
            txtrole.setHint("Patient");

    }
}