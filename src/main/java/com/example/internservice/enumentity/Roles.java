package com.example.internservice.enumentity;

public enum Roles {
    Admin, User;

    public static Roles fromInteger(int x) {
        switch (x) {
            case 0:
                return Admin;
            case 1:
                return User;
        }
        return null;
    }
}
