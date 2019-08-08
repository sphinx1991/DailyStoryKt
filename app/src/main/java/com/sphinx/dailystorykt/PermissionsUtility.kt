package com.instavans.instavansshippers.utils

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.jetbrains.anko.ctx

fun Activity.requestPermissionsIfNotAvailed(requestCode: Int, vararg permissions: String, allAllowed: () -> Unit = {}) {
	val allPermissionsAllowed = permissions.all { permission ->
		permissionGranted(permission)
	}
	if (allPermissionsAllowed) allAllowed() // ends
	else {
		ActivityCompat.requestPermissions(this, permissions, requestCode)
	}
}

fun Context.permissionGranted(permission: String) =
		ContextCompat.checkSelfPermission(ctx, permission) == PackageManager.PERMISSION_GRANTED
