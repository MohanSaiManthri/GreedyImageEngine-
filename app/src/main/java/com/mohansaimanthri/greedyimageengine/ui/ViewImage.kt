package com.mohansaimanthri.greedyimageloader.ui



import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.mohansaimanthri.greedyimageengine.R
import com.mohansaimanthri.greedyimageloader.utils.ImageLoader
import com.mohansaimanthri.greedyimageloader.utils.constants.Constants
import com.mohansaimanthri.greedyimageloader.utils.extensions.hideSystemUI
import kotlinx.android.synthetic.main.activity_view_image.*
import kotlinx.android.synthetic.main.layout_bottom_sheet.*
import java.text.SimpleDateFormat
import java.util.*


class ViewImage : AppCompatActivity() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
    }

    private val pattern = "yyyy-MM-dd'T'HH:mm:ss"
    private val targetPattern = "EEE, d MMM yyyy HH:mm"
    private val sdf = SimpleDateFormat(pattern, Locale.US)
    private val sdf1 = SimpleDateFormat(targetPattern, Locale.US)

    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_image)
        context = this
        hideSystemUI(this.window)
        if (intent.hasExtra(Constants.IntentViewImage.uri)) {
            val color = intent.getStringExtra(Constants.IntentViewImage.color)
            val name = intent.getStringExtra(Constants.IntentViewImage.title)

            //Changing the font and assigning the value.
            val typeface = ResourcesCompat.getFont(this@ViewImage, R.font.dm_serif_regular)
            photo_name.typeface = typeface
            photo_name.text = name

            //Loading image into the touch image view and below we configure it.
            /* val options = RequestOptions()
                 .diskCacheStrategy(DiskCacheStrategy.ALL)
                 .dontAnimate()
                 .override(800, 800)
                 .skipMemoryCache(true).autoClone()*/

            //Load image using glide with request options.
            ImageLoader.with(viewImage.context)
                .load(
                    intent.getStringExtra(Constants.IntentViewImage.uri)!!,/*placeholder*/
                    R.drawable.download,
                    viewImage
                )
            ImageLoader.with(viewImage.context)
                .load(
                    "https://images.unsplash.com/profile-1446404465118-3a53b909cc82?ixlib=rb-0.3.5&q=80&fm=jpg&crop=faces&cs=tinysrgb&fit=crop&h=64&w=64&s=3ef46b07bb19f68322d027cb8f9ac99f",/*placeholder*/
                    R.drawable.person,
                    profile_picture
                )

            /*ImageLoader.with(this@ViewImage).load(ColorDrawable(Color.parseColor("#${it.color.replace("#", "")}")))
                .apply(RequestOptions.circleCropTransform()).into(colorViewer)*/
            //Initializing view model factory
            /* model = ViewModelProviders.of(this@ViewImage, PhotoDetailViewModelFactory(application))
                 .get(PhotoDetailsViewModel::class.java)
             //Hit API from repository
             model.getPhotoDetails(id)*/

            dateField.text = sdf1.format(sdf.parse("2017-05-30T17:30:44-04:00"))
            nameField.text = "lukeskywalker"
            likesField.text = "355"
            downloadField.text = "6344"
            colorField.text = "#283c46"
            dimensionField.text = String.format("%s x %s", 3456, 2304)
            showFields()
            manufacturerField.text = "Panasonic"
            modelField.text = "DC-GH5"
            exposureField.text = "1/4000"
            apeturelField.text = "7.1"
            isoField.text = 1000.toString()
            focalField.text = "66"
            description.text =
                "I'm a hermit on a water planet. I don't need people. Please don't leave me. Gifted in the ways of the #Force.\","
            description.visibility = View.VISIBLE
            /* model.photoDetailLiveData.observe(this@ViewImage, Observer {
                 dateField.text = sdf1.format(sdf.parse(it.createdAt))
                 nameField.text = it.user.username
                 likesField.text = it.likes.toString()
                 downloadField.text = it.downloads.toString()
                 colorField.text = it.color
                 dimensionField.text = String.format("%s x %s", it.width, it.height)
                 when {
                     it.exif != null && it.exif.make!=null -> {
                         showFields()
                         manufacturerField.text = it.exif.make.toString()
                         modelField.text = it.exif.model.toString()
                         exposureField.text = it.exif.exposureTime.toString()
                         apeturelField.text = it.exif.aperture.toString()
                         isoField.text = it.exif.iso.toString()
                         focalField.text = it.exif.focalLength.toString()
                     }
                     else -> {
                         hide()
                     }
                 }
                 when {
                     it.altDescription != null -> {
                         description.text = it.altDescription
                         description.visibility = View.VISIBLE
                     }
                     else -> description.visibility = View.GONE
                 }
                 Glide.with(this@ViewImage).load(ColorDrawable(Color.parseColor("#${it.color.replace("#", "")}")))
                     .apply(RequestOptions.circleCropTransform()).into(colorViewer)
                 Glide.with(this@ViewImage).load(Uri.parse(it.user.profileImage.large))
                     .placeholder(R.drawable.person)
                     .apply(RequestOptions.circleCropTransform()).into(profile_picture)
             })*/
        }
        viewImageBack.setOnClickListener {
            onBackPressed()
        }

        /*Registering the Broadcast receiver */
        //registerReceiver()
    }

    private fun showFields() {
        container_6.visibility = View.VISIBLE
        container_7.visibility = View.VISIBLE
        container_8.visibility = View.VISIBLE
        container_9.visibility = View.VISIBLE
        container_10.visibility = View.VISIBLE
        container_11.visibility = View.VISIBLE
    }

    private fun hide() {
        container_6.visibility = View.GONE
        container_7.visibility = View.GONE
        container_8.visibility = View.GONE
        container_9.visibility = View.GONE
        container_10.visibility = View.GONE
        container_11.visibility = View.GONE
    }

}