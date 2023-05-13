package com.maiconhellmann.github

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maiconhellmann.github_user.feature.list.UserListActivity

/**
 * Empty main activity. Normally that's the activity that starts the navigation framework and redirects to
 * the first screen of the app, that could be an Activity or a Fragment depending on how the engineers define.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, UserListActivity::class.java))
    }
}