package com.example.practicafragmento

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    private val KEY = "STATE_KEY"
    private var addWelcomeFragment: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addWelcomeFragment = savedInstanceState?.getBoolean(KEY, true) ?: true
        if (addWelcomeFragment)
            supportFragmentManager.beginTransaction().add(R.id.container, WelcomeFragment())
                .commit()

        findViewById<Button>(R.id.btnRedFragment).setOnClickListener {
            replaceFragment(RedFragment().apply {
                arguments = Bundle().apply {
                    putString("key", "This is red fragment")

                    playSound(R.raw.sound1)
                }
            })
        }

        findViewById<Button>(R.id.btnBlueFragment).setOnClickListener {
            replaceFragment(BlueFragment().apply {
                arguments = Bundle().apply {
                    putString("key", "This is blue fragment")
                    playSound(R.raw.sound2)
                }
            })
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putBoolean(KEY, false)
        }

        super.onSaveInstanceState(outState)
    }

    private fun playSound(sound: Int) = MediaPlayer.create(this, sound).start()

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {

            setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left)

            replace(R.id.container, fragment)
            addToBackStack(fragment.tag)
            commit()
        }
    }

}