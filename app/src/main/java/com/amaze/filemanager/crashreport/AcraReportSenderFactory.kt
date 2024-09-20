package com.amaze.filemanager.crashreport

import android.content.Context
import org.acra.config.CoreConfiguration
import org.acra.sender.ReportSender
import org.acra.sender.ReportSenderFactory

class AcraReportSenderFactory : ReportSenderFactory {
    override fun create(
        context: Context,
        config: CoreConfiguration,
    ): ReportSender {
        return AcraReportSender()
    }

    override fun enabled(config: CoreConfiguration): Boolean {
        return true
    }
}
