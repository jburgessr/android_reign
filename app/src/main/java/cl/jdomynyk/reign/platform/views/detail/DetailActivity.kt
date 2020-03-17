package cl.jdomynyk.reign.platform.views.detail

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.view.KeyEvent
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import cl.jdomynyk.reign.R
import kotlinx.android.synthetic.main.activity_detail.*
import java.io.File

class DetailActivity : AppCompatActivity() {
    companion object {
        private const val EXTRA_URL = "URL"
        private const val MAX_PROGRESS = 100
        fun start(activity: Activity, url: String) {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(EXTRA_URL, url)
            activity.startActivity(intent)
        }
    }

    private lateinit var pageUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        pageUrl = intent.getStringExtra(EXTRA_URL)
            ?: throw IllegalStateException("field $EXTRA_URL missing in Intent")

        createFolderCache()

        initWebView()

        setWebClient()

        loadUrl(pageUrl)
    }

    private fun createFolderCache() {
        val dir: File = cacheDir

        if (!dir.exists()) {
            dir.mkdirs()
        }

        webView.settings.setAppCachePath(dir.path)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.domStorageEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override
            fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }
        }
    }

    private fun setWebClient() {
        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(
                view: WebView,
                newProgress: Int
            ) {
                super.onProgressChanged(view, newProgress)
                progressBar.progress = newProgress
                if (newProgress < MAX_PROGRESS && progressBar.visibility == ProgressBar.GONE) {
                    progressBar.visibility = ProgressBar.VISIBLE
                }
                if (newProgress == MAX_PROGRESS) {
                    progressBar.visibility = ProgressBar.GONE
                }
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, exit the activity)
        return super.onKeyDown(keyCode, event)
    }

    private fun loadUrl(pageUrl: String) {
        webView.loadUrl(pageUrl)
    }

}
