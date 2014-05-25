package se.chalmers.tda367.bluejava.models;

import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a video for a movie.
 * Could be a trailer, teaser, short clip etc.
 */
public class Video implements Parcelable {

    private final String ID;
    private final String language;
    private final String key;
    private final String name;
    private final String site;
    private final int resolution;
    private final String type;

    public Video(JSONObject jsonObject) throws JSONException {
        ID = jsonObject.getString("id");
        language = jsonObject.getString("iso_639_1");
        key = jsonObject.getString("key");
        name = jsonObject.getString("name");
        site = jsonObject.getString("site");
        resolution = jsonObject.getInt("size");
        type = jsonObject.getString("type");
    }

    public String getID() {
        return ID;
    }

    public String getLanguage() {
        return language;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getSite() {
        return site;
    }

    public int getResolution() {
        return resolution;
    }

    public String getType() {
        return type;
    }

    public static List<Video> jsonToListOfVideos(JSONArray jsonArray) {

        try {
            List<Video> videos = new ArrayList<Video>();

            for (int i = 0; i < jsonArray.length(); ++i) {
                JSONObject jsonVideo = jsonArray.getJSONObject(i);
                Video video = new Video(jsonVideo);
                videos.add(video);
            }

            return videos;

        } catch (JSONException e) {
            throw new RuntimeException("Video.java jsonToListOfVideos error");
        }
    }

    protected Video(Parcel in) {
        ID = in.readString();
        language = in.readString();
        key = in.readString();
        name = in.readString();
        site = in.readString();
        resolution = in.readInt();
        type = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ID);
        dest.writeString(language);
        dest.writeString(key);
        dest.writeString(name);
        dest.writeString(site);
        dest.writeInt(resolution);
        dest.writeString(type);
    }

    public static final Parcelable.Creator<Video> CREATOR = new Parcelable.Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
