package util

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wind.collagePhotoMaker.share.R
import kotlinx.coroutines.suspendCancellableCoroutine
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.resume
import kotlin.math.max

//object Extension {
fun RecyclerView.init(
    orientation: Int = RecyclerView.VERTICAL,
    adapterCustom: RecyclerView.Adapter<out RecyclerView.ViewHolder>
) {
    layoutManager = LinearLayoutManager(context!!, orientation, false)
    adapter = adapterCustom
}

fun Fragment.getColorEx(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(requireContext(), colorRes)
}

fun Fragment.getDimen(dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

fun Context.getDimen(dimenRes: Int): Float {
    return resources.getDimension(dimenRes)
}

fun Context.getColorAttribute(
    @AttrRes attrColor: Int,
    typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    theme.resolveAttribute(attrColor, typedValue, resolveRefs)
    //    You need to check if the attribute got resolved to a resource or a color value.
    //            The default value of textColorPrimary is not a Color but a ColorStateList, which is a resource
    // resourceId is used if it's a ColorStateList, and data if it's a color reference or a hex color
    val colorRes: Int = if (typedValue.resourceId != 0) typedValue.resourceId else typedValue.data
    return ContextCompat.getColor(this, colorRes)
}

fun Context.getColorEx(colorId: Int): Int {
    return ContextCompat.getColor(this, colorId)
}

fun Context.getColorAttr(@AttrRes colorAttr: Int): Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(colorAttr, typedValue, true)
    return typedValue.data
}

fun Fragment.getColorAttr(@AttrRes colorAttr: Int): Int {
    val typedValue = TypedValue()
    requireActivity().theme.resolveAttribute(colorAttr, typedValue, true)
    return typedValue.data
}

fun Context.getDrawableEx(drawableId: Int): Drawable {
    return ContextCompat.getDrawable(this, drawableId)!!
}

fun View.getDrawableEx(drawableId: Int): Drawable {
    return ContextCompat.getDrawable(context, drawableId)!!
}

fun View.getColorEx(colorId: Int): Int {
    return ContextCompat.getColor(context!!, colorId)
}

fun View.gone() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.showCircularAnim() {
    visibility = View.VISIBLE
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        val cx = measuredWidth / 2
        val cy = measuredHeight / 2
        val finalRadius = max(measuredWidth, measuredHeight) / 2
        val anim = ViewAnimationUtils.createCircularReveal(this, cx, cy, 0f, finalRadius.toFloat())
        anim.duration = 150L
        anim.start()
    }
}

fun Context.dp() = resources.displayMetrics.density
fun View.dp() = resources.displayMetrics.density
fun View.dp(resId: Int) = resources.getDimension(resId)
fun View.sp() = resources.displayMetrics.scaledDensity
fun Context.screenHeight() = resources.displayMetrics.heightPixels
fun Context.screenWidth() = resources.displayMetrics.widthPixels

fun View.setMargins(l: Int = 0, t: Int = 0, r: Int = 0, b: Int = 0) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val p = layoutParams as ViewGroup.MarginLayoutParams
        p.setMargins(l, t, r, b)
        requestLayout()
    }
}

fun View.setHeight(h: Int) {
    val params: ViewGroup.LayoutParams = layoutParams
    params.height = h
    layoutParams = params
}

fun Context.toastDebug(mes: String) {
    val isDebuggable = 0 != applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
    if (isDebuggable) {
        Toast.makeText(this, mes, Toast.LENGTH_SHORT).show()
    }
}

inline fun <T> Any?.instanceOf(clazz: Class<T>, doSomething: (T) -> Unit) {
    if (clazz.isInstance(this)) {
        doSomething(this as T)
    }
}

fun ImageView.tint(color: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(color))
}

// return width and height measurespec
fun square(widthMeasureSpec: Int, heightMeasureSpec: Int): Pair<Int, Int> {
    val width = View.MeasureSpec.getSize(widthMeasureSpec)
    val height = View.MeasureSpec.getSize(heightMeasureSpec)
    val widthDesc = View.MeasureSpec.getMode(widthMeasureSpec)
    val heightDesc = View.MeasureSpec.getMode(heightMeasureSpec)
    var size = 0
    size = if (widthDesc == View.MeasureSpec.UNSPECIFIED && heightDesc == View.MeasureSpec.UNSPECIFIED) {
        100 // Use your own default size, in our case
    } else if ((widthDesc == View.MeasureSpec.UNSPECIFIED || heightDesc == View.MeasureSpec.UNSPECIFIED) && !
        (widthDesc == View.MeasureSpec.UNSPECIFIED && heightDesc == View.MeasureSpec.UNSPECIFIED)
    ) {
        //Only one of the dimensions has been specified so we choose the dimension that has a value (in the case of unspecified, the value assigned is 0)
        if (width > height) width else height
    } else {
        if (width > height) height else width
    }
    return Pair(
        View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY),
        View.MeasureSpec.makeMeasureSpec(size, View.MeasureSpec.EXACTLY)
    )
}

suspend fun View.awaitNextLayout() = suspendCancellableCoroutine<Unit> { cont ->
    // This lambda is invoked immediately, allowing us to create
    // a callback/listener

    val listener = object : View.OnLayoutChangeListener {
        override fun onLayoutChange(
            v: View,
            left: Int,
            top: Int,
            right: Int,
            bottom: Int,
            oldLeft: Int,
            oldTop: Int,
            oldRight: Int,
            oldBottom: Int
        ) {
            // The next layout has happened!
            // First remove the listener to not leak the coroutine
            v.removeOnLayoutChangeListener(this)
            // Finally resume the continuation, and
            // wake the coroutine up
            cont.resume(Unit)
        }
    }
    // If the coroutine is cancelled, remove the listener
    cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }
    // And finally add the listener to view
    addOnLayoutChangeListener(listener)

    // The coroutine will now be suspended. It will only be resumed
    // when calling cont.resume() in the listener above
}


suspend fun View.awaitLayout() = suspendCancellableCoroutine<Unit> { cont ->
    if (measuredWidth > 0 && measuredHeight > 0) {
        cont.resume(Unit)
    } else {
        val listener = object : View.OnLayoutChangeListener {
            override fun onLayoutChange(
                v: View,
                left: Int,
                top: Int,
                right: Int,
                bottom: Int,
                oldLeft: Int,
                oldTop: Int,
                oldRight: Int,
                oldBottom: Int
            ) {
                v.removeOnLayoutChangeListener(this)
                cont.resume(Unit)
            }
        }
        cont.invokeOnCancellation { removeOnLayoutChangeListener(listener) }
        addOnLayoutChangeListener(listener)
    }
}

suspend fun Animator.awaitEnd() = suspendCancellableCoroutine<Unit> { cont ->
    // Add an invokeOnCancellation listener. If the coroutine is
    // cancelled, cancel the animation too that will notify
    // listener's onAnimationCancel() function
    cont.invokeOnCancellation { cancel() }

    addListener(object : AnimatorListenerAdapter() {
        private var endedSuccessfully = true

        override fun onAnimationCancel(animation: Animator) {
            // Animator has been cancelled, so flip the success flag
            endedSuccessfully = false
        }

        override fun onAnimationEnd(animation: Animator) {
            // Make sure we remove the listener so we don't keep
            // leak the coroutine continuation
            animation.removeListener(this)

            if (cont.isActive) {
                // If the coroutine is still active...
                if (endedSuccessfully) {
                    // ...and the Animator ended successfully, resume the coroutine
                    cont.resume(Unit)
                } else {
                    // ...and the Animator was cancelled, cancel the coroutine too
                    cont.cancel()
                }
            }
        }
    })
}

inline fun RecyclerView.Adapter<out RecyclerView.ViewHolder>.doAtPos(pos: Int, func: () -> Unit) {
    if (pos in 0 until itemCount) {
        func()
    }
}

fun FragmentActivity.getWidth(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager
        .defaultDisplay
        .getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun FragmentActivity.getHeight(): Int {
    val displayMetrics = DisplayMetrics()
    windowManager
        .defaultDisplay
        .getMetrics(displayMetrics)
    return displayMetrics.heightPixels
}


/**
 * https://medium.com/thoughts-overflow/how-to-add-a-fragment-in-kotlin-way-73203c5a450b
 */
inline fun FragmentManager.inTransaction(
    useAnim: Boolean = false,
    func: FragmentTransaction.() -> Unit
) {
    beginTransaction().apply {
        if (useAnim) {
            useAnim()
        }
        func()
        commitAllowingStateLoss()
    }
}

fun FragmentTransaction.useAnim() {
    setCustomAnimations(
        R.anim.slide_in,
        R.anim.fade_out,
        R.anim.fade_in,
        R.anim.slide_out
    )
}

fun FragmentActivity.addFragment(id: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.commit(true) {
        add(id, fragment, tag)
        setReorderingAllowed(true)
    }
}

fun FragmentActivity.replaceFragment(
    frameId: Int, fragment: Fragment, tag: String? = null,
    isAddBackStack: Boolean = true, useAnim: Boolean = true
) {
    supportFragmentManager.commit(true) {
        if (useAnim) {
            useAnim()
        }
        replace(frameId, fragment, tag)
        if (isAddBackStack) {
            addToBackStack(tag)
        }
        setReorderingAllowed(true)
    }
}

fun FragmentActivity.popFragment(name: String, flag: Int = 0) {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStack(name, flag)
    }
}

fun FragmentActivity.popFragment() {
    if (supportFragmentManager.backStackEntryCount > 0) {
        supportFragmentManager.popBackStack()
    }
}

fun FragmentActivity.findFragmentByTag(tag: String?): Fragment? {
    return supportFragmentManager.findFragmentByTag(tag)
}

fun FragmentActivity.findFragmentById(id: Int): Fragment? {
    return supportFragmentManager.findFragmentById(id)
}


fun Fragment.addFragment(id: Int, fragment: Fragment, tag: String? = null) {
    if (findFragmentById(id) == null) {
        childFragmentManager.commit(true) {
            add(id, fragment, tag)
        }
    }
}

fun Fragment.replaceFragment(
    frameId: Int,
    fragment: Fragment,
    tag: String? = null,
    isAddBackStack: Boolean = true,
    useAnim: Boolean = true
) {
    childFragmentManager.commit(true) {
        if (useAnim) {
            useAnim()
        }
        replace(frameId, fragment, tag)
        if (isAddBackStack) {
            addToBackStack(null)
        }
    }
}

fun Fragment.removeFragment(tag: String) {
    val fragment = childFragmentManager.findFragmentByTag(tag)
    if (fragment != null) childFragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
}

fun Fragment.popFragment() {
    if (childFragmentManager.backStackEntryCount > 0) {
        childFragmentManager.popBackStack()
    }
}

fun Fragment.popFragment(name: String, flag: Int = 0) {
    if (childFragmentManager.backStackEntryCount > 0) {
        childFragmentManager.popBackStack(name, flag)
    }
}

fun Fragment.findFragmentById(id: Int): Fragment? {
    return childFragmentManager.findFragmentById(id)
}

fun Fragment.findFragmentByTag(tag: String?): Fragment? {
    return childFragmentManager.findFragmentByTag(tag)
}

fun Fragment.setUpToolbar(toolbar: Toolbar, title: String? = null, showUpIcon: Boolean = true, toolbarColorInt: Int? = null) {
    if (activity is AppCompatActivity) {
        val appActivity = activity as AppCompatActivity
        appActivity.setSupportActionBar(toolbar)

        val actionbar = appActivity.supportActionBar
        actionbar?.apply {
            this.title = title
            if (showUpIcon) {
                setDisplayHomeAsUpEnabled(true)
            }
        }
        if (showUpIcon) {
            toolbarColorInt?.let {
                toolbar.navigationIcon?.tint(it)
            }
            toolbar.setNavigationOnClickListener {
                appActivity.onBackPressed()
            }
        }
    }
}


fun Drawable.tint(color: Int) {
    setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
}

fun Fragment.getToolbar(): ActionBar? {
    if (activity is AppCompatActivity) {
        val appActivity = activity as AppCompatActivity
        return appActivity.supportActionBar
    }
    return null
}

/// ANIMATION EXTENSION
fun View.runAnimator(resId: Int, func: (Animator) -> Unit) {
    AnimatorInflater.loadAnimator(context, resId).apply {
        setTarget(this@runAnimator)
        func(this)
        start()
    }
}

fun View.runAnimation(resId: Int, func: (Animation) -> Unit) {
    AnimationUtils.loadAnimation(context, resId).let {
        func(it)
        startAnimation(it)
    }
}

open class SingletonHolder<out T : Any, in A>(creator: (A) -> T) {
    private var creator: ((A) -> T)? = creator
    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        val i = instance
        if (i != null) {
            return i
        }

        return synchronized(this) {
            val i2 = instance
            if (i2 != null) {
                i2
            } else {
                val created = creator!!(arg)
                instance = created
                creator = null
                created
            }
        }
    }
}

fun Bitmap.saveFile(context: Context) {
    try {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
        val currentDateAndTime = sdf.format(Date())
        val imageFileName = "IMG_$currentDateAndTime.jpg"
        val resolver = context.contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, imageFileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
        }
        resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)?.let { imageUri ->
            val fOut = resolver.openOutputStream(imageUri)
            compress(Bitmap.CompressFormat.JPEG, 100, fOut)
            fOut?.close()
        }
    } catch (ignored: Exception) {
    }
}

val Context.inflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Activity.fullScreen() {
    window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
}

/**
 * https://stackoverflow.com/questions/37672833/android-m-light-and-dark-status-bar-programmatically-how-to-make-it-dark-again
 */
fun Activity.lightStatusBar(lightStatusBar: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val oldFlags = window.decorView.systemUiVisibility
        var flags = oldFlags
        flags = if (lightStatusBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}

fun Fragment.lightStatusBar(lightStatusBar: Boolean) {
    requireActivity().lightStatusBar(lightStatusBar)
}

fun Activity.statusBarColor(): Int {
    return window.statusBarColor
}

fun Activity.statusBarColor(@ColorInt color: Int) {
    window.statusBarColor = color
}

fun Activity.lightNavigationBar(lightStatusBar: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val oldFlags = window.decorView.systemUiVisibility
        var flags = oldFlags
        flags = if (lightStatusBar) {
            flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        } else {
            flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
        }
        window.decorView.systemUiVisibility = flags
    }
}

// window inset
fun View.doOnApplyWindowInsets(f: (View, WindowInsets, InitialPadding) -> Unit) {
    // Create a snapshot of the view's padding state
    val initialPadding = recordInitialPaddingForView(this)
    // Set an actual OnApplyWindowInsetsListener which proxies to the given
    // lambda, also passing in the original padding state
    setOnApplyWindowInsetsListener { v, insets ->
        f(v, insets, initialPadding)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets
    requestApplyInsetsWhenAttached()
}

data class InitialPadding(
    val left: Int, val top: Int,
    val right: Int, val bottom: Int
)

private fun recordInitialPaddingForView(view: View) = InitialPadding(
    view.paddingLeft, view.paddingTop, view.paddingRight, view.paddingBottom
)

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        // We're already attached, just request as normal
        requestApplyInsets()
    } else {
        // We're not attached to the hierarchy, add a listener to
        // request when we are
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}