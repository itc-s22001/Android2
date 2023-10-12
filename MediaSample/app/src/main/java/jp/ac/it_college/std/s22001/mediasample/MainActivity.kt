package jp.ac.it_college.std.s22001.mediasample

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import jp.ac.it_college.std.s22001.mediasample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer()
        val mediaFileUri = Uri.parse(
            "android.resource://${packageName}/${R.raw.takibi}"
        )
        mediaPlayer?.apply {
            setDataSource(applicationContext, mediaFileUri)
            setOnPreparedListener(::mediaPlayerOnPrepared)
            setOnCompletionListener(::mediaPlayerOnCompletion)
            prepareAsync()
        }
        binding.btPlay.setOnClickListener(::onPlayButtonClick)
    }
    private fun mediaPlayerOnPrepared(mediaPlayer: MediaPlayer) {
        binding.btPlay.isEnabled = true
        binding.btBack.isEnabled = true
        binding.btForward.isEnabled = true
    }
    private fun mediaPlayerOnCompletion(mediaPlayer: MediaPlayer) {
        binding.btPlay.setText(R.string.bt_play_play)
    }
    private fun onPlayButtonClick(view: View) {
       mediaPlayer?.run {
           if(isPlaying) {
               pause()
               binding.btPlay.setText(R.string.bt_play_play)
           } else {
               start()
               binding.btPlay.setText(R.string.bt_play_pause)
           }
       }
    }

    override fun onStop() {
        mediaPlayer?.run {
            if(isPlaying) {
                stop()
            }
            release()
        }
        super.onStop()
    }
}







