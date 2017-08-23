package br.com.ps.adctest.repository;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import br.com.ps.adctest.model.User;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.Types;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

public final class Repository {

    public static final String REGISTERED_USERS = "registeredUsers";

    private Repository() {

    }

    public static boolean registerUser(Context context, User u) {
        SharedPreferences.Editor edit = getPreferences(context).edit();
        JsonAdapter<Set<User>> adapter = getListUserAdapter();
        Set<User> users = getRegisteredUsers(context);

        if (!users.add(u)) return false;

        edit.putString(REGISTERED_USERS, adapter.toJson(users));
        return edit.commit();
    }

    public static boolean login(Context context, String login, String password) {
        Set<User> users = getRegisteredUsers(context);

        for (User u : users) {
            if (u.canPass(login, password)) return true;
        }
        return false;
    }

    public static boolean clearAllData(Context context) {
        return getPreferences(context).edit().clear().commit();
    }

    private static Set<User> getRegisteredUsers(Context context) {
        SharedPreferences prefs = getPreferences(context);
        JsonAdapter<Set<User>> adapter = getListUserAdapter();
        Set<User> users;
        try {
            users = adapter.fromJson(prefs.getString(REGISTERED_USERS, "[]"));
            if (users == null) {
                users = new HashSet<>();
            }
        } catch (IOException e) {
            users = new HashSet<>();
            Log.e("Repository", "Error", e);
        }
        return users;
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    private static JsonAdapter<Set<User>> getListUserAdapter() {
        Moshi moshi = new Moshi.Builder().build();
        ParameterizedType listUserType = Types.newParameterizedType(Set.class, User.class);
        return moshi.adapter(listUserType);
    }
}
