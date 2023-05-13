package com.maiconhellmann.github

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maiconhellmann.github_user.feature.list.UserListActivity

/**
 * Activity principal vazia. Normalmente, esta é a Activity que inicia o framework de navegação e redireciona para
 * a primeira tela do aplicativo, que pode ser uma Activity ou um Fragment, dependendo de como os engenheiros definem.
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, UserListActivity::class.java))
        finish()
    }
}