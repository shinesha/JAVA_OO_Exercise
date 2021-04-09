package com.httpAuthors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.google.gson.*;


public class GetArticles {

    public static List<String> getArticleTitles(String author) {
        {


            String response;
            int startPage = 1;
            int totalPages = Integer.MAX_VALUE;
            List<String> titles = new ArrayList<>();
            while (startPage <= totalPages) {
                try {
                    URL obj = new URL("https://jsonmock.hackerrank.com/api/articles?author=" + author + "&page=" + startPage);
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con.setRequestMethod("GET");

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    while ((response = in.readLine()) != null) {

                        JsonObject convertedObject = new Gson().fromJson(response, JsonObject.class);
                        totalPages = convertedObject.get("total_pages").getAsInt();
                        JsonArray data = convertedObject.getAsJsonArray("data");
                        for (int i = 0; i < data.size(); i++) {
                            String title = data.get(i).getAsJsonObject().get("title").toString();
                            String story_title = data.get(i).getAsJsonObject().get("story_title").toString();
                            if (title != null) {
                                titles.add(title);
                            } else if (title == null && story_title != null) {

                                titles.add(story_title);
                            }
                        }
                    }
                    in.close();
                    startPage++;
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            return titles;
        }

    }

        public static void main (String[]args) throws IOException {

            List<String> result = GetArticles.getArticleTitles("epaga");
            result.forEach(System.out::println);


        }
    }


