    package com.example.myappli.common

    import android.app.Activity
    import android.content.ActivityNotFoundException
    import android.content.Context
    import android.content.Intent
    import android.net.Uri
    import android.widget.Toast
    import androidx.core.content.ContextCompat
    import com.example.myappli.R


    fun Context.startShareIntent(link: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, link)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    fun Activity.startFacebookIntent(link: String) {

        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, link)
        share.setPackage("com.facebook.katana") //Facebook App package
        startActivity(Intent.createChooser(share, "Title of the dialog the system will open"))


    }

    fun Activity.startWhatsAppIntent(link: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.whatsapp")
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        } else {
            Toast.makeText(this, getString(R.string.dont_wp), Toast.LENGTH_SHORT).show()
        }
    }

    fun Activity.startInstagramIntent(link: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.instagram.android")
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        } else {
            Toast.makeText(this, getString(R.string.dont_inst), Toast.LENGTH_SHORT).show()
        }
    }


    fun Activity.startTwitterIntent(link: String) {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, link)
        sendIntent.type = "text/plain"
        sendIntent.setPackage("com.twitter.android")
        if (sendIntent.resolveActivity(packageManager) != null) {
            startActivity(sendIntent)
        } else {
            Toast.makeText(this, getString(R.string.dont_twitter), Toast.LENGTH_SHORT).show()
        }
    }



    fun Context.openPrivacyActivity() {
        val uri = Uri.parse(AppConstants.privacy_policy_link)
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    fun Context.openPlayStoreActivity() {
        val uri = Uri.parse(getString(R.string.moreapps_link))
        startActivity(Intent(Intent.ACTION_VIEW, uri))
    }

    fun Context.openRateAppActivity() {
        try {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.rateapp_link) + packageName)
                )
            )
        } catch (e: ActivityNotFoundException) {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(String.format(getString(R.string.rateapp_link), packageName))
                )
            )
        }
    }

    fun Context.openFeedbackActivity() {
        val emailIntent = Intent(Intent.ACTION_SENDTO)
        emailIntent.data = Uri.parse("mailto:" + getString(R.string.feedback_email_link))
        startActivity(Intent.createChooser(emailIntent, getString(R.string.send_mail)))
    }
