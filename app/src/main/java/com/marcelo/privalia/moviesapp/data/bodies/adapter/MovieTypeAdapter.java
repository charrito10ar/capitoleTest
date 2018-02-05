package com.marcelo.privalia.moviesapp.data.bodies.adapter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.marcelo.privalia.moviesapp.data.bodies.MovieBody;

import java.io.IOException;

/**
 * This class allow build a MovieBody from a custom Json Response (To get imdb id)
 */
public class MovieTypeAdapter extends TypeAdapter<MovieBody> {
    @Override
    public void write(JsonWriter out, MovieBody value) throws IOException {
        out.beginObject();
        out.name("year").value(value.getYear());
        out.name("title").value(value.getTitle());
        out.name("overview").value(value.getOverview());
        out.name("released").value(value.getReleased());
        out.name("imdb").value(value.getImdb());
        out.endObject();
        out.close();
    }

    @Override
    public MovieBody read(JsonReader reader) throws IOException {
        MovieBody movieBody = new MovieBody();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("year") && reader.peek() != JsonToken.NULL) {
                movieBody.setYear(reader.nextInt());
            } else if (name.equals("title")) {
                movieBody.setTitle(reader.nextString());
            } else if (name.equals("overview") && reader.peek() != JsonToken.NULL) {
                movieBody.setOverview(reader.nextString());
            } else if (name.equals("released") && reader.peek() != JsonToken.NULL) {
                movieBody.setReleased(reader.nextString());
            } else if (name.equals("ids")) {
                reader.beginObject();
                while (reader.hasNext()){
                    String idName = reader.nextName();
                    if (idName.equals("imdb") && reader.peek() != JsonToken.NULL){
                        movieBody.setImdb(reader.nextString());
                    }else {
                        reader.skipValue();
                    }
                }
                reader.endObject();
            }else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return movieBody;
    }
}
