package se.chalmers.tda367.bluejava.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import se.chalmers.tda367.bluejava.R;
import se.chalmers.tda367.bluejava.models.Video;

import java.util.List;

/**
 * Used to create a list item in the cast view.
 * <p/>
 * Created by marcus on 2014-05-10.
 */
public class VideosTabArrayAdapter extends BaseAdapter {

    private final Context context;
    private final List<Video> videos;
    private final Activity activity;

    public VideosTabArrayAdapter(Context context, Activity activity, List<Video> videos) {
        this.activity = activity;
        this.context = context;
        this.videos = videos;
    }

    @Override
    public int getCount() {
        return videos.size();
    }

    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Converts an old view in a parent view to a new one.
     *
     * @param position    The position of the view in the parent.
     * @param convertView The view which is to be converted.
     * @param parent      The parent view.
     * @return The view which is to be used.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Video video = (Video) getItem(position);

        if (convertView == null) {

            final LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = inflater.inflate(R.layout.cast_tab_list_item, null);

        }

        ImageView coverImageView = (ImageView) convertView.findViewById(R.id.image);
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView characterTextView = (TextView) convertView.findViewById(R.id.character);

        coverImageView.setBackgroundResource(R.drawable.youtube_cover);
        nameTextView.setText(video.getName());
        characterTextView.setText(video.getType());

        convertView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String youtubeUrl = "https://www.youtube.com/watch?v=" + video.getKey();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                activity.startActivity(intent);
            }
        });

        return convertView;
    }
}
