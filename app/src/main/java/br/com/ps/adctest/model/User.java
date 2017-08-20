package br.com.ps.adctest.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override public User[] newArray(int size) {
            return new User[size];
        }
    };
    private String name;
    private String surname;
    private String login;
    private String password;

    public User() {
    }

    public User(String name, String surname, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
    }

    protected User(Parcel in) {
        name = in.readString();
        surname = in.readString();
        login = in.readString();
        password = in.readString();
    }

    @Override public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(login);
        dest.writeString(password);
    }

    @Override public int describeContents() {
        return 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return login != null ? login.equals(user.login) : user.login == null;
    }

    @Override public int hashCode() {
        return login != null ? login.hashCode() : 0;
    }

    public boolean canPass(String login, String password) {
        return this.login.equals(login) && this.password.equals(password);
    }
}
