package com.tong.gao.walletuser.ui.adaper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.tong.gao.walletuser.R;

import java.util.List;


public class BannerPageAdapter extends PagerAdapter {

	Context ctx = null;
	List<Drawable> bannerData = null;

	public BannerPageAdapter(Context ct ,List<Drawable> data) {
		ctx = ct;
		bannerData = data;
	}

	/**
	 * Return the number of views available.
	 */
	@Override
	public int getCount() {
		return bannerData.size();
	}

	/**
	 * Create the page for the given position.  The adapter is responsible
	 * for adding the view to the container given here, although it only
	 * must ensure this is done by the time it returns from
	 * {@link #finishUpdate(ViewGroup)}.
	 *
	 * @param container The containing View in which the page will be shown.
	 * @param position  The page position to be instantiated.
	 * @return Returns an Object representing the new page.  This does not
	 * need to be a View, but can be some other container of the page.
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		View v = LayoutInflater.from(ctx).inflate(R.layout.item_ad_pager , null);
		ImageView img = (ImageView) v.findViewById(R.id.iv_coin_icon);

		img.setScaleType(ImageView.ScaleType.CENTER_CROP);
		img.setImageDrawable(bannerData.get(position));

		container.addView(v);
		return v;
	}

	/**
	 * Remove a page for the given position.  The adapter is responsible
	 * for removing the view from its container, although it only must ensure
	 * this is done by the time it returns from {@link #finishUpdate(ViewGroup)}.
	 *
	 * @param container The containing View from which the page will be removed.
	 * @param position  The page position to be removed.
	 * @param object    The same object that was returned by
	 *                  {@link #instantiateItem(View, int)}.
	 */
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
//		Log.i("PagerAdapter" , "method destroyItem be calling");
		container.removeView((View)object);
	}

	/**
	 * Determines whether a page View is associated with a specific key object
	 * as returned by {@link #instantiateItem(ViewGroup, int)}. This method is
	 * required for a PagerAdapter to function properly.
	 *
	 * @param view   Page View to check for association with <code>object</code>
	 * @param object Object to check for association with <code>view</code>
	 * @return true if <code>view</code> is associated with the key object <code>object</code>
	 */
	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}


}
