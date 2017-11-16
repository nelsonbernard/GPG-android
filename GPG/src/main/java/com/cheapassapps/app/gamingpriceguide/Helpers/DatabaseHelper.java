package com.cheapassapps.app.gamingpriceguide.Helpers;

import android.os.AsyncTask;
import android.util.Log;
import com.cheapassapps.app.gamingpriceguide.Objects.Console;
import com.cheapassapps.app.gamingpriceguide.Objects.Game;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class DatabaseHelper {

    public static final String DATABASE_NAME = "u297075769_gmapp";
    public static final String CONSOLE_TABLE_NAME = "CONSOLE";
    public static final String GAME_TABLE_NAME = "GAMES";
    public static final String CONSOLE_HEADER_ID = "ID";
    public static final String CONSOLE_HEADER_NAME = "NAME";
    public static final String CONSOLE_HEADER_IMAGE = "IMAGE_NAME";
    public static final String CONSOLE_HEADER_GIANTBOMB_ID = "GIANTBOMBID";
    public static final String HEADER_ID = "ID";
    public static final String HEADER_NAME = "NAME";
    public static final String HEADER_IMAGE = "IMAGE_NAME";
    public static final String HEADER_LOOSE_PRICE = "LOOSE";
    public static final String HEADER_COMPLETE_PRICE = "CIB";
    public static final String HEADER_NEW_PRICE = "NEW";
    public static final String HEADER_GRADED = "GRADED";
    public static final String HEADER_GIANTBOMBID = "GIANTBOMBID";
    public static final String HEADER_GAME_URL = "GAME_URL";
    public static final String HEADER_CONSOLE = "CONSOLEID";
    public static final String gamesDBNet_API_BaseURL = " http://thegamesdb.net/api/";

    private ArrayList<Console> consoleList = new ArrayList<>();
    private ArrayList<Game> gamesList = new ArrayList<>();
    private Game game = new Game();

    public Game getCurrentGame(){ return game; }
    public ArrayList<Game> getGamesList() {
        return gamesList;
    }
    public ArrayList<Console> getConsoleList() { return consoleList; }

    OkHttpClient client = new OkHttpClient();

    public void getAllConsoles(){

        GetAllConsolesTask myTask = new GetAllConsolesTask();
        try {
            myTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void getGamesFromConsole(String consoleid) throws IOException, JSONException {

        GetGamesFromConsoleTask myTask = new GetGamesFromConsoleTask();
        try {
            myTask.execute(consoleid).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public void getGame(int id) throws ExecutionException, InterruptedException {

        GetGameTask myTask = new GetGameTask();
        try {
            myTask.execute(id).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private class GetGameTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(Integer... params) {

            String gamejson = "{\"id\":\"" + params[0].toString() + "\"}";
            String postResponse = null;
            String searchForGameID = "GetGames.php?name=";
            String getGame_GDBNet_URL = "GetGame.php?id=";
            Game currentGame = new Game();
            try {
                postResponse = doPostRequest("http://www.cheapassgames.xyz/gpgapp/phpscripts/gameinfo.php", gamejson);
                JSONObject jsonObject = new JSONObject(postResponse);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject currentObject = jsonArray.getJSONObject(i);

                    currentGame.setImageName(currentObject.getString("image"));
                    currentGame.setName(currentObject.getString("name"));
                    currentGame.setId(Integer.parseInt(currentObject.getString("id")));
                    currentGame.setLoosePrice(currentObject.getString("loose"));
                    currentGame.setCompletePrice(currentObject.getString("cib"));
                    currentGame.setNewPrice(currentObject.getString("new"));
                    currentGame.setConsoleID(currentObject.getString("consoleid"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            gamejson = "{\"query\":\"" + currentGame.getName() + "\"}";

            try {
                postResponse = doPostRequest("http://www.cheapassgames.xyz/gpgapp/phpscripts/giantbombquery.php", gamejson);
                JSONArray jsonArray = new JSONArray(postResponse);

                JSONObject currentObject = jsonArray.getJSONObject(0);
                currentGame.setDeck(currentObject.getString("deck"));
                currentGame.setDescription(currentObject.getString("description"));
                currentGame.setScreen_url(currentObject.getString("screen_url"));
                currentGame.setReleaseDate(currentObject.getString("release_date"));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String description = currentGame.getDescription();

            removeTags(description);
            currentGame.setDescription(description);
            game = currentGame;
            return null;
        }
    }

    private class GetGamesFromConsoleTask extends AsyncTask<String, Void, Void>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {

            String consolejson = "{\"consoleid\":\"" + params[0] + "\"}";
            String postResponse = null;
            try {
                postResponse = doPostRequest("http://www.cheapassgames.xyz/gpgapp/phpscripts/gamesbyconsole.php", consolejson);
                JSONObject jsonObject = new JSONObject(postResponse);
                JSONArray jsonArray = jsonObject.getJSONArray("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject currentObject = jsonArray.getJSONObject(i);
                    Game currentGame = new Game();
                    currentGame.setImageName(currentObject.getString("image"));
                    currentGame.setName(currentObject.getString("name"));
                    currentGame.setId(Integer.parseInt(currentObject.getString("id")));
                    currentGame.setLoosePrice(currentObject.getString("loose"));
                    currentGame.setCompletePrice(currentObject.getString("cib"));
                    currentGame.setNewPrice(currentObject.getString("new"));
                    currentGame.setConsoleID(params[0]);
                    gamesList.add(currentGame);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void avoid) {
            super.onPostExecute(avoid);
        }
    }

    private class GetAllConsolesTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected Void doInBackground(Void... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://www.cheapassgames.xyz/gpgapp/phpscripts/consolelist.php");
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json");
                connection.connect();

                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));
                String data = "";
                String line = "";

                while (line != null) {
                    line = reader.readLine();
                    data = data + line;
                    Log.d("Response: ", "> " + line);
                }

                JSONObject jsonObject = new JSONObject(data);
                JSONArray jsonArray = (JSONArray) jsonObject.get("result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    Console console = new Console();
                    console.setName(jsonObject.getString("name"));
                    console.setConsoleID(jsonObject.getString("consoleid"));
                    console.setId(Integer.parseInt(jsonObject.getString("id")));

                    String imageLocation = "http://www.cheapassgames.xyz/gpgapp/images/consoles/" + console.getConsoleID() + ".png";
                    console.setImageURL(imageLocation);
                    consoleList.add(console);
                    Log.i("Game:  ", console.getName());
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        protected void onPostExecute(Void avoid){
            super.onPostExecute(avoid);
        }
    }

    public String doPostRequest(String url, String json) throws IOException{
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    public String removeTags(String in)
    {
        int index=0;
        int index2=0;
        while(index!=-1)
        {
            index = in.indexOf("<");
            index2 = in.indexOf(">", index);
            if(index!=-1 && index2!=-1)
            {
                in = in.substring(0, index).concat(in.substring(index2+1, in.length()));
            }
        }
        return in;
    }
}
