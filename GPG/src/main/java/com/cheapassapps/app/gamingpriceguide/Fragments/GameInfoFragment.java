package com.cheapassapps.app.gamingpriceguide.Fragments;


import android.graphics.BlurMaskFilter;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheapassapps.app.gamingpriceguide.Helpers.DatabaseHelper;
import com.cheapassapps.app.gamingpriceguide.Helpers.PricingDataHelper;
import com.cheapassapps.app.gamingpriceguide.Objects.Game;
import com.cheapassapps.app.gamingpriceguide.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameInfoFragment extends Fragment {

//    private static final PricingDataHelper pdHelper = new PricingDataHelper();
    private static Game currentGame = new Game();
    DatabaseHelper dbHelper;

    public static GameInfoFragment newInstance(int gameID){
        Bundle args = new Bundle();
        args.putInt("id", gameID);
        GameInfoFragment fragment = new GameInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public GameInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dbHelper = new DatabaseHelper();
        try {
            dbHelper.getGame(this.getArguments().getInt("id"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        currentGame = dbHelper.getCurrentGame();



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_game_info, container, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.gameImageView);
        TextView textView = (TextView) view.findViewById(R.id.gameTitleTextView);
        TextView looseText = (TextView) view.findViewById(R.id.looseText);
        TextView cibText = (TextView) view.findViewById(R.id.cibText);
        TextView newText = (TextView) view.findViewById(R.id.newText);
        TextView textConsole = (TextView) view.findViewById(R.id.textConsole);
        TextView description = (TextView) view.findViewById(R.id.description);
        ImageView bannerImageView = (ImageView) view.findViewById(R.id.bannerImageView);
        String imageurl = "http://www.cheapassgames.xyz/gpgapp/images/" + currentGame.getConsoleID() + "/" + currentGame.getImageName();
        Picasso.with(getContext()).load(imageurl).into(imageView);
        textView.setText(currentGame.getName());
        looseText.setText(currentGame.getLoosePrice());
        cibText.setText(currentGame.getCompletePrice());
        newText.setText(currentGame.getNewPrice());
        textConsole.setText(currentGame.getConsoleID());
        description.setText(currentGame.getDescription());
        Picasso.with(getContext()).load(currentGame.getScreen_url()).into(bannerImageView);

        return view;
    }
}
