package ru.vbnz.deltasofttest;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

public class ContentRow {
    public String Text;
    public Drawable Drawable;

    public ContentRow(String line, Context context) {
        String[] data = line.split(";");
        Resources resources = context.getResources();
        String resName = data[0].replace(".jpg","").replace("-", "_");
        int resourceId = resources.getIdentifier(resName, "drawable",
                context.getPackageName());
        Drawable = AppCompatResources.getDrawable(context, resourceId);
        Text = data[1] + "\n" + data[2];
    }
}
