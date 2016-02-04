package app.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class AudioUtil {
    public static int m6967a(Context context, String str) {
        MediaPlayer create = MediaPlayer.create(context, Uri.parse(str));
        if (create == null) {
            return 0;
        }
        int duration = create.getDuration() / 1000;
        create.reset();
        create.release();
        return duration;
    }
}
