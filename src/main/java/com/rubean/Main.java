/**
 * Demonstration for Serialization und Deserialization with Gson
 *
 * UserSimple:
 * ===========
 * - UserSimple.java
 * - Example of UserSimple => UserSimple.json
 *
 * UserNested:
 * ===========
 * - UserNested.java
 * - UserAdress.java
 * - Example of UserNested => UserNested.json
 *
 * @author  Markus Meier
 * @version 1.0
 * @since   2021-04-12
 */

package com.rubean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;

public class Main {

    public static void main(String[] args) {
        serializeUserSimple();
        deserializeUserSimple();
        serializeNested();
        deserializeNested();
    }

    private static void serializeUserSimple() {
        UserSimple user = new UserSimple(
                "Markus",
                "Markus.Meier68@gmx.de",
                52,
                true);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        printJsonPretty(json);
    }

    private static void deserializeUserSimple() {
        String userJson = "{'name':'Markus','email':'Markus.Meier68@gmx.de','age':52,'isDeveloper':true}";
        Gson gson = new Gson();
        UserSimple userSimple = gson.fromJson(userJson, UserSimple.class);
    }

    private static void serializeNested() {
        UserAddress userAddress = new UserAddress(
                "Pütrichstrasse",
                "11",
                "Gauting",
                "Germany");
        UserAddress[] userAddressesArray = new UserAddress[1];

        userAddressesArray[0] = userAddress;

        UserNested userNested = new UserNested(
                "Markus",
                "Markus.Meier68@gmail.com",
                52,
                true,
                userAddressesArray);
        Gson gson = new Gson();
        String json = gson.toJson(userNested);
        printJsonPretty(json);
    }

    private static void deserializeNested() {
        String userNestedJson = "{'name':'Markus','email':'Markus.Meier68@gmail.com','age':52,'isDeveloper':true,'userAddress':[{'street':'Pütrichstrasse','houseNumber':'11','city':'Gauting','country':'Germany'}]}";
        UserNested userNested = new Gson().fromJson(userNestedJson, UserNested.class);
    }

    private static void printJsonPretty(String json){
        Gson gsonBuilder = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(json);
        System.out.println(gsonBuilder.toJson(je));
    }


}