package com.example.myapplication.fragmentAdapter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.myapplication.R;

public class Fragment_One extends Fragment {
    private View mView;
    public static Fragment_One newInstance(){
        Bundle args = new Bundle();
        Fragment_One fragment_one = new Fragment_One();
        fragment_one.setArguments(args);
        return fragment_one;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.flutter_fragment_container_1,null);
        // TODO: the UI of the Fragment (path: res/layout/flutter_fragment_container_1.xml) - nativeFragmentContainer
    }

}