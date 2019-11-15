package com.example.gradientlayout

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import android.animation.AnimatorListenerAdapter
import androidx.core.content.ContextCompat


class GradientLayout : FrameLayout {

    private val ANIMATION_SPEED: Long = 500
    private val GRADIENT_RADIUS: Float = 1.75f
    private val MIN_ALPHA: Int = 200
    private val MAX_ALPHA: Int = 255
    private var leftPaint: Paint = Paint()

    private var rightPaint: Paint = Paint()
    private var bottomPaint: Paint = Paint()

    private lateinit var animatorSet: AnimatorSet
    private lateinit var leftGradientAnimator: ObjectAnimator
    private lateinit var rightGradientAnimator: ObjectAnimator
    private lateinit var bottomGradientAnimator: ObjectAnimator

    private lateinit var leftGradient: RadialGradient
    private lateinit var rightGradient: RadialGradient
    private lateinit var bottomGradient: RadialGradient

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?
    ) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    private fun init() {
        setBackgroundColor(Color.WHITE)
        if (measuredWidth != 0) {
            leftGradient = RadialGradient(
                0f,
                measuredHeight * 0.25f,
                measuredWidth.toFloat() * GRADIENT_RADIUS,
                ContextCompat.getColor(context, R.color.green_color),
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
            leftPaint.shader = leftGradient

            rightGradient = RadialGradient(
                measuredWidth.toFloat(),
                0f,
                measuredWidth.toFloat() * GRADIENT_RADIUS,
                ContextCompat.getColor(context, R.color.red_color),
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
            rightPaint.shader = rightGradient

            bottomGradient = RadialGradient(
                measuredWidth.toFloat(),
                measuredHeight.toFloat(),
                measuredWidth.toFloat() * GRADIENT_RADIUS,
                ContextCompat.getColor(context, R.color.blue_color),
                Color.TRANSPARENT,
                Shader.TileMode.CLAMP
            )
            bottomPaint.shader = bottomGradient
        }
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPaint(bottomPaint)
        canvas.drawPaint(leftPaint)
        canvas.drawPaint(rightPaint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec)
        init()
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    fun startAnimation() {

        initStartParams()

        leftGradientAnimator =
            ObjectAnimator.ofInt(this, "leftGradient", MAX_ALPHA, MIN_ALPHA)

        rightGradientAnimator =
            ObjectAnimator.ofInt(this, "rightGradient", MAX_ALPHA, MIN_ALPHA)

        bottomGradientAnimator =
            ObjectAnimator.ofInt(this, "bottomGradient", MAX_ALPHA, MIN_ALPHA)

        animatorSet = AnimatorSet()
        animatorSet.duration = ANIMATION_SPEED
        animatorSet.playSequentially(
            leftGradientAnimator,
            rightGradientAnimator,
            bottomGradientAnimator
        )
        animatorSet.addListener(object : AnimatorListenerAdapter() {

            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                animatorSet.start()
            }

        })
        animatorSet.start()

    }

    private fun initStartParams() {
        leftPaint.alpha = MAX_ALPHA
        rightPaint.alpha = MIN_ALPHA
        bottomPaint.alpha = MIN_ALPHA
    }

    fun setLeftGradient(alpha: Int) {
        leftPaint.alpha = alpha
        rightPaint.alpha = MIN_ALPHA + (MAX_ALPHA - alpha)
        bottomPaint.alpha = MIN_ALPHA
        invalidate()
    }

    fun setRightGradient(alpha: Int) {
        rightPaint.alpha = alpha
        bottomPaint.alpha = MIN_ALPHA + (MAX_ALPHA - alpha)
        leftPaint.alpha = MIN_ALPHA
        invalidate()
    }

    fun setBottomGradient(alpha: Int) {
        bottomPaint.alpha = alpha
        leftPaint.alpha = MIN_ALPHA + (MAX_ALPHA - alpha)
        rightPaint.alpha = MIN_ALPHA
        invalidate()
    }


    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        startAnimation()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        stopAnimation()
    }

    private fun stopAnimation() {
        animatorSet.removeAllListeners()
        animatorSet.end()
        animatorSet.cancel()
    }
}
