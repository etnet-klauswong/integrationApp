package com.example.myapplication.fragmentAdapter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R; // Todo: Enable the R.Layout
public class Fragment_Three extends Fragment {
    public static Fragment_Three newInstance(){
        Bundle args = new Bundle();
        Fragment_Three fragment_three = new Fragment_Three();
        fragment_three.setArguments(args);
        return fragment_three;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flutter_fragment_container_3,null);
        // TODO: the UI of the Fragment (path: res/layout/flutter_fragment_container_1.xml) - nativeFragmentContainer
    }
}