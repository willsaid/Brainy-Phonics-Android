package com.hearatale.phonics.ui.adapter.simple_alphabet;

import android.media.Image;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hearatale.phonics.Application;
import com.hearatale.phonics.R;
import com.hearatale.phonics.data.model.phonics.SightWordModel;
import com.hearatale.phonics.data.model.phonics.letters.SimpleLetterModel;
import com.hearatale.phonics.data.model.typedef.DifficultyDef;
import com.hearatale.phonics.utils.Config;
import com.hearatale.phonics.utils.FontsHelper;
import com.hearatale.phonics.utils.Helper;
import com.hearatale.phonics.utils.glide.GlideApp;

import java.util.ArrayList;
import java.util.List;

public class SimpleAlphabetAdapter extends BaseQuickAdapter<SimpleLetterModel, BaseViewHolder> {

    int sizeImage;
    @DifficultyDef
    int mDifficulty;

    public SimpleAlphabetAdapter(@Nullable List<SimpleLetterModel> data, int sizeImage, @DifficultyDef int difficulty) {
        super(R.layout.item_alphabet, data);
        this.sizeImage = sizeImage;
        mDifficulty = difficulty;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    protected void convert(BaseViewHolder helper, SimpleLetterModel item) {
        String text;
        if (mDifficulty == DifficultyDef.EASY) {
            String letterUpperCase = item.getLetter().toUpperCase();
            String letterLowerCase = item.getLetter().toLowerCase();
            text = letterUpperCase + letterLowerCase;
        } else {
            @ColorRes int colorRes = R.color.black_alphabet;
            switch (item.getColorString()) {
                case "orange":
                    colorRes = R.color.orange_alphabet;
                    break;
                case "green":
                    colorRes = R.color.green_alphabet;
                    break;
                case "purple":
                    colorRes = R.color.purple_alphabet;
                    break;
            }
            helper.setTextColor(R.id.text_view_alphabet, mContext.getResources().getColor(colorRes));
            text = item.getLetter().toLowerCase();
        }

        helper.setText(R.id.text_view_alphabet, text);

        helper.setProgress(R.id.progress_bar, item.getProgressCompleted());
        int totalProgress = Config.PUZZLE_COLUMNS * Config.PUZZLE_ROWS;
        boolean isComplete = item.getProgressCompleted() == totalProgress;
        helper.setMax(R.id.progress_bar, totalProgress);
        helper.setVisible(R.id.image_done, isComplete);

        ImageView imageViewStars = helper.getView(R.id.image_stars);

        if (isComplete) {
            setImageDrawableResStar(imageViewStars, item.getStarConsecutive());
        } else {
            imageViewStars.setVisibility(View.INVISIBLE);
        }

        ImageView imageView = helper.getView(R.id.image_avatar);
        GlideApp.with(mContext)
                .load(Uri.parse(item.getPathImage()))
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(sizeImage)
                .into(imageView);
    }

    private void setImageDrawableResStar(ImageView imageView, int starConsecutive) {

        if(imageView.getVisibility() != View.VISIBLE){
            imageView.setVisibility(View.VISIBLE);
        }

        switch (starConsecutive) {

            case 0:
            case 1:
                imageView.setVisibility(View.INVISIBLE);
            case 2:
                GlideApp.with(mContext).load(R.mipmap.star_2).into(imageView);
                break;
            case 3:
                GlideApp.with(mContext).load(R.mipmap.star_3).into(imageView);
                break;
            case 4:
                GlideApp.with(mContext).load(R.mipmap.star_4).into(imageView);
                break;
            default:
                GlideApp.with(mContext).load(R.mipmap.star_5).into(imageView);
                break;
        }
    }
}
