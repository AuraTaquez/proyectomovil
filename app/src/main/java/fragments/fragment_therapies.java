package fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import com.example.myapplicationfin.R;
import com.example.myapplicationfin.WebService.Asynchtask;
import com.example.myapplicationfin.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapters.adpPrescription;
import clases.PrescriptionModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_therapies#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_therapies extends Fragment implements Asynchtask {

    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_therapies() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_therapies.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_therapies newInstance(String param1, String param2) {
        fragment_therapies fragment = new fragment_therapies();
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
        View view = inflater.inflate(R.layout.fragment_therapies, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, getActivity(), fragment_therapies.this);
        ws.execute("GET");

        return view;
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList<PrescriptionModel> lstPrescription = new ArrayList<PrescriptionModel> ();
        try {
            JSONObject JSONlista =  new JSONObject(result);
            JSONArray JSONlistaPrescription=  JSONlista.getJSONArray("prescription");
            lstPrescription = PrescriptionModel.JsonObjectsBuild(JSONlistaPrescription);
            adpPrescription adapPrescription = new adpPrescription(lstPrescription,getContext());
            int resId = R.anim.layout_animation_down_to_up;
            LayoutAnimationController animation = AnimationUtils.loadLayoutAnimation(getContext(),
                    resId);
            recyclerView.setLayoutAnimation(animation);
            recyclerView.setAdapter(adapPrescription);
        }catch (JSONException e)
        {
            Toast.makeText(this.getContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}