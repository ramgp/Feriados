package com.github.feriados.common;

import android.util.SparseArray;
import android.view.View;

/**
 * Generic View Holder Pattern implementation as suggested by Pierre-Yves Ricau
 * URL: http://www.piwai.info/android-adapter-good-practices/
 */
public class ViewHolder {
  @SuppressWarnings("unchecked")
  public static <T extends View> T getView(View view, int id) {
    SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
    if (viewHolder == null) {
      viewHolder = new SparseArray<View>();
      view.setTag(viewHolder);
    }

    View childView = viewHolder.get(id);
    if (childView == null) {
      childView = view.findViewById(id);
      viewHolder.put(id, childView);
    }

    return (T) childView;
  }
}
