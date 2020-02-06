package state;

import android.content.Context;
import android.content.SharedPreferences;

import model.User;

public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "UserCredentials";
    private static final String KEY_ID = "UserId";
    private static final String KEY_EMAIL = "UserEmail";
    private static final String KEY_NAME = "UserName";
    private static final String KEY_PHONE = "UserPhone";

    private static SharedPrefManager mInstance;
    private static Context context;

    private SharedPrefManager(Context context) {
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public void userLogIn(User user) {
        SharedPreferences.Editor editor = context
                .getSharedPreferences(mInstance.SHARED_PREF_NAME, context.MODE_PRIVATE)
                .edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_EMAIL,user.getEmail());
        editor.putString(KEY_NAME, user.getName());
        editor.apply();
    }

    public Boolean isLoggedIn() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_NAME, null)
        );
    }

    public void logOut() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
