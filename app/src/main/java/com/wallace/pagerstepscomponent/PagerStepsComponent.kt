package com.wallace.pagerstepscomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.wallace.pagerstepscomponent.databinding.ComponentPagerStepsBinding

class PagerStepsComponent(
    context: Context,
    attrs: AttributeSet?
) : ConstraintLayout(context, attrs) {
    private var adapter: StepsAdapter? = null

    private var binding = ComponentPagerStepsBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PagerStepsComponent)
        binding.rcvSteps.rootView.setBackgroundColor(ContextCompat.getColor(context, android.R.color.holo_blue_light))
        attributes.recycle()
    }

    fun setupAdapter(anyList: ArrayList<AnyModel>) {
        adapter = StepsAdapter(context, anyList)
        with(binding.rcvSteps) {
            this.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.HORIZONTAL, false
            )
            this.adapter = adapter
        }
    }

    fun populateList(anyList: ArrayList<AnyModel>) {
        adapter?.updateList(anyList)
    }

    fun nextPage() {
        adapter?.nextPage()
    }
}