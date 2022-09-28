package uz.gita.olimpiadasavollari.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharedPreference {

    private static MySharedPreference mySharedPreference;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static MySharedPreference getInstance(Context context) {
        if (mySharedPreference == null) {
            mySharedPreference = new MySharedPreference(context);
        }
        return mySharedPreference;
    }

    private MySharedPreference(Context context) {
        sharedPreferences = context.getSharedPreferences("app_name", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setLevel(String level) {
        editor.putString("level", level);
        editor.apply();
    }

    public String getLevel() {
        return sharedPreferences.getString("level", "EASY");
    }

    public void setStart(Boolean easy) {
        editor.putBoolean("easy", easy);
        editor.apply();
    }

    public Boolean getStart() {
        return sharedPreferences.getBoolean("easy", false);
    }


    public void setResults(String listOfResult) {
        editor.putString("result", listOfResult);
        editor.apply();
    }

    public String getResults() {
        return sharedPreferences.getString("result", "");
    }

    public void setVisibleContinue(Boolean visible){
        editor.putBoolean("visible", visible).apply();
    }
    public Boolean getVisibleContinue(){
        return sharedPreferences.getBoolean("visible", false);
    }
}
