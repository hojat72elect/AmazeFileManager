

package com.amaze.filemanager.crashreport

import android.content.Context
import com.amaze.filemanager.R
import org.acra.data.CrashReportData
import org.acra.sender.ReportSender

class AcraReportSender : ReportSender {
    override fun send(
        context: Context,
        errorContent: CrashReportData,
    ) {
        ErrorActivity.reportError(
            context,
            errorContent,
            ErrorActivity.ErrorInfo.make(
                ErrorActivity.ERROR_UI_ERROR,
                "Application crash",
                R.string.app_ui_crash,
            ),
        )
    }

    override fun requiresForeground(): Boolean {
        return true
    }
}
