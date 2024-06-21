package com.example.cicdtest

import android.content.Context
import android.widget.Toast

class TestFeature {
    fun test(context: Context) {
        Toast.makeText(context, "Hello CICDTest!!", Toast.LENGTH_SHORT).show()
    }
}