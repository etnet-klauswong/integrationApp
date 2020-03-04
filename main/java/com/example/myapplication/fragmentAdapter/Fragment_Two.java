package com.example.myapplication.fragmentAdapter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
// Todo: Enable the R.Layout
import com.example.myapplication.R;
public class Fragment_Two extends Fragment {
    public static Fragment_Two newInstance() {
        Bundle args = new Bundle();
        Fragment_Two fragment_two = new Fragment_Two();
        fragment_two.setArguments( args );
        return fragment_two;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.flutter_fragment_container_2, null );
        // TODO: the UI of the Fragment (path: res/layout/flutter_fragment_container_1.xml) - nativeFragmentContainer
    }
}
