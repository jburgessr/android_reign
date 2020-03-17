package cl.jdomynyk.reign.platform.navigation

import android.app.Activity
import cl.jdomynyk.reign.platform.views.detail.DetailActivity

fun Activity.gotToDetail(url: String) {
    DetailActivity.start(this, url)
}