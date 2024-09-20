package com.amaze.filemanager.utils

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricPrompt
import androidx.biometric.BiometricPrompt.PromptInfo
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.amaze.filemanager.filesystem.files.EncryptDecryptUtils.DecryptButtonCallbackInterface
import com.amaze.filemanager.ui.activities.MainActivity

/** Created by vishal on 15/4/17.  */
@RequiresApi(api = Build.VERSION_CODES.M)
class FingerprintHandler(
    private val mainActivity: MainActivity,
    private val decryptIntent: Intent,
    private val promptInfo: PromptInfo,
    private val decryptButtonCallbackInterface: DecryptButtonCallbackInterface,
) : BiometricPrompt.AuthenticationCallback() {
    /**
     * Authenticate user to perform decryption.
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    fun authenticate(cryptoObject: BiometricPrompt.CryptoObject) {
        if (ActivityCompat.checkSelfPermission(mainActivity, Manifest.permission.USE_FINGERPRINT)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val prompt =
            BiometricPrompt(mainActivity, ContextCompat.getMainExecutor(mainActivity), this)
        prompt.authenticate(promptInfo, cryptoObject)
    }

    override fun onAuthenticationError(
        errMsgId: Int,
        errString: CharSequence,
    ) = Unit

    override fun onAuthenticationFailed() {
        decryptButtonCallbackInterface.failed()
    }

    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
        decryptButtonCallbackInterface.confirm(decryptIntent)
    }
}
