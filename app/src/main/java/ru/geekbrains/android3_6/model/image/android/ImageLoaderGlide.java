package ru.geekbrains.android3_6.model.image.android;

import android.graphics.Bitmap;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import dagger.Module;
import dagger.Provides;
import io.paperdb.Paper;
import ru.geekbrains.android3_6.NetworkStatus;
import ru.geekbrains.android3_6.model.cache.ImageCache;
import ru.geekbrains.android3_6.model.image.ImageLoader;
import timber.log.Timber;

/**
 * Created by stanislav on 3/12/2018.
 */


public class ImageLoaderGlide implements ImageLoader<ImageView>
{
    private static final String TAG = "ImageLoaderGlide";


    @Override
    public void loadInto(@Nullable String url, ImageView container)
    {
        if(NetworkStatus.isOnline())
        {
            GlideApp.with(container.getContext()).asBitmap().load(url).listener(new RequestListener<Bitmap>()
            {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource)
                {
                    Timber.e( "Image load failed", e);
                    return false;
                }

                @Override
                public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource)
                {
                    ImageCache.saveImage(url, resource);
                    return false;
                }
            }).into(container);
        }
        else
        {
            if(ImageCache.contains(url))
            {
                GlideApp.with(container.getContext())
                        .load(ImageCache.getFile(url))
                        .into(container);
            }
        }
    }



}
