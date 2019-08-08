import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetBehavior


/**
 * Becomes invisible if true
 */
@BindingAdapter("invisible")
fun View.invisible(invisible: Boolean) {
	visibility = if (invisible) View.INVISIBLE else View.VISIBLE
}

@BindingAdapter("android:visibility")
fun View.visibility(visible: Boolean) {
	visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("android:visibility")
fun View.visibility(obj: Any?) {
	visibility = if (obj != null) View.VISIBLE else View.GONE
}

@BindingAdapter("longText")
fun EditText.longText(num: Long) {
	if (num == 0L) return
	if (num.toString() != text.toString()) {
		setText(num.toString())
	}
}

@BindingAdapter("execute")
fun View.execute(f: Any) {
}

@BindingAdapter("bottomSheetState")
fun View.bottomSheetState(state: Int) {
	val bottomSheet = BottomSheetBehavior.from(this@bottomSheetState)
	bottomSheet.state = state
}

@BindingAdapter("bottomSheetCallback")
fun View.bottomSheetCallback(callback: BottomSheetBehavior.BottomSheetCallback) {
	val bottomSheet = BottomSheetBehavior.from(this@bottomSheetCallback)
	bottomSheet.setBottomSheetCallback(callback)
}

@BindingAdapter("mFullScroll")
fun NestedScrollView.mFullScroll(direction: Int) {
	fullScroll(direction)
}

@BindingAdapter("vehicleImage")
fun ImageView.vehicleImage(vehicleName: String) {
	val formattedVehicleName = vehicleName.replace(" ", "")
	val inputStream = context.assets.open("$formattedVehicleName.png")
	val drawable = Drawable.createFromStream(inputStream, null)
	setImageDrawable(drawable)
}

@BindingAdapter("touchListener")
fun View.touchListener(value: Boolean) {
	setOnTouchListener { _, _ ->
		val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
		imm.hideSoftInputFromWindow(windowToken, 0)
		false
	}
}

@BindingAdapter("movementMethod")
fun movementMethod(textView: TextView,context: Context) {
	textView.movementMethod = LinkMovementMethod.getInstance()

}

@BindingAdapter("android:src")
fun setImageViewResource(imageView: ImageView, resource: Int) {
	imageView.setImageResource(resource)
}

//@BindingAdapter("loadDriverImg")
//fun ImageView.loadDriverImg(url: String?) {
//		Glide.with(context.applicationContext).load(url)
//				.diskCacheStrategy(DiskCacheStrategy.ALL)
//				.into(this)
//}
