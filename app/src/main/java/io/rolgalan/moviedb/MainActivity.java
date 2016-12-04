package io.rolgalan.moviedb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.arlib.floatingsearchview.FloatingSearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.rolgalan.moviedb.data.DataProvider;
import io.rolgalan.moviedb.server.ApiManager;

public class MainActivity extends AppCompatActivity {
    public final static String TAG = "MovieDB";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataProvider.getConfiguration();
        setContentView(R.layout.activity_main);
    }
}
