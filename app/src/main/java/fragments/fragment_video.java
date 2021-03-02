package fragments;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.myapplicationfin.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_video#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_video extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    VideoView videoView;
    Button next;
    int cont = 0;
    String path="https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/videos%2FTorre.mp4?alt=media&token=bc28f3dd-e9a9-4f8f-a980-b4fec48b6038";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_video() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_video.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_video newInstance(String param1, String param2) {
        fragment_video fragment = new fragment_video();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video, container, false);

        videoView = (VideoView) view.findViewById(R.id.videoView);
        next=(Button)view.findViewById(R.id.btnSiguiente);

        videoView.setVideoPath(path);
        MediaController mc = new MediaController(getContext());
        videoView.setMediaController(mc);
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                    startOtherVideo();
            }
            });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startOtherVideo();
            }
        });
        return view;
    }



    private void startOtherVideo(){
        cont++;
        videoView.stopPlayback();
        if(cont == 1)
            path = "https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/videos%2FPelota.mp4?alt=media&token=dfbee4df-32ed-4918-b042-9071bca72dcc";
        else if (cont == 2){
            path = "https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/videos%2FBotella.mp4?alt=media&token=4aa3f1e3-b31d-43ad-b34e-ce71c2c732f9";
        }
        else if (cont == 3){
            path = "https://firebasestorage.googleapis.com/v0/b/rehabilitacioniot-a9f5a.appspot.com/o/videos%2FTorre.mp4?alt=media&token=bc28f3dd-e9a9-4f8f-a980-b4fec48b6038";

        }
        videoView.setVideoPath(path);
        MediaController mc = new MediaController(getContext());
        videoView.setMediaController(mc);
        videoView.start();
        if(cont >= 3)
            cont=0;
    }
}