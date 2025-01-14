package com.example.asynctask

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.material.snackbar.Snackbar
import android.util.Log

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    private lateinit var button: Button
    private lateinit var progressIndicator: CircularProgressIndicator
    private lateinit var rootLayout: ConstraintLayout

    private val TAG = "MainActivity"

    // Buat executor service untuk tugas latar belakang
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    // Handler untuk memperbarui UI di thread utama
    private val mainHandler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi views
        rootLayout = findViewById(R.id.root_layout)
        button = findViewById(R.id.download_button)
        imageView = findViewById(R.id.image_view)
        progressIndicator = findViewById(R.id.progress_indicator)

        button.setOnClickListener {
            loadImage("https://stis.ac.id/media/source/up.png")
        }
    }

    private fun loadImage(imageUrl: String) {
        // Tampilkan indikator progres
        progressIndicator.visibility = View.VISIBLE
        button.isEnabled = false

        // Kirim tugas ke executor service
        executorService.execute {
            val bitmap = downloadImage(imageUrl)
            Log.d(TAG, "Bitmap diunduh: $bitmap")

            // Perbarui UI di thread utama
            mainHandler.post {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap)
                    showSuccessMessage()
                } else {
                    showErrorMessage()
                }
                progressIndicator.visibility = View.GONE
                button.isEnabled = true
            }
        }
    }

    private fun downloadImage(imageUrl: String): Bitmap? {
        var connection: HttpURLConnection? = null
        var inputStream: InputStream? = null

        return try {
            val url = URL(imageUrl)
            connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            Log.d(TAG, "Mengunduh gambar dari URL: $imageUrl")

            inputStream = connection.inputStream
            val options = BitmapFactory.Options().apply {
                inPreferredConfig = Bitmap.Config.RGB_565
            }
            BitmapFactory.decodeStream(inputStream, null, options)
        } catch (e: IOException) {
            e.printStackTrace()
            Log.e(TAG, "IOException saat mengunduh gambar: ${e.message}")
            null
        } finally {
            // Tutup sumber daya dengan benar
            inputStream?.close()
            connection?.disconnect()
        }
    }

    private fun showSuccessMessage() {
        Snackbar.make(rootLayout, "Image downloaded successfully", Snackbar.LENGTH_SHORT).show()
    }

    private fun showErrorMessage() {
        Snackbar.make(rootLayout, "Failed to download image", Snackbar.LENGTH_SHORT)
            .setAction("Retry") {
                loadImage("https://stis.ac.id/media/source/up.png")
            }
            .show()
    }

    override fun onDestroy() {
        super.onDestroy()
        executorService.shutdown()
    }
}
