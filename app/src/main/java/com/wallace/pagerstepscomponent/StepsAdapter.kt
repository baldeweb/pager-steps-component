package com.wallace.pagerstepscomponent

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.wallace.pagerstepscomponent.databinding.ItemStepBinding

class StepsAdapter(
    private val context: Context,
    private val anyList: ArrayList<AnyModel>
) : RecyclerView.Adapter<StepsAdapter.StepsHolder>() {
    private lateinit var binding: ItemStepBinding
    private var currentPosition: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StepsHolder {
        binding = ItemStepBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return StepsHolder(binding)
    }

    override fun onBindViewHolder(holder: StepsHolder, position: Int) {
        currentPosition = (holder.adapterPosition + 1)
        defineDefaultStyle()
    }

    private fun defineDefaultStyle() {
        when {
            anyList.lastOrNull() != null -> {
                binding?.ctlItemStep?.visibility = GONE
                binding?.ctlItemStepLast?.background = ContextCompat.getDrawable(context, R.drawable.circle_disabled_filled)
                binding?.ctlItemStepLast?.visibility = VISIBLE
                binding?.ctlItemCurrentStep?.visibility = GONE
            }
            else -> {
                binding?.ctlItemStep?.background = ContextCompat.getDrawable(context, R.drawable.circle_disabled_filled)
                binding?.ctlItemStep?.visibility = VISIBLE
                binding?.ctlItemCurrentStep?.visibility = GONE
                binding?.ctlItemStepLast?.visibility = GONE
            }
        }
    }

    private fun defineStyle(position: Int) {
        when {
            anyList.lastOrNull() != null -> {
                binding?.ctlItemStepLast?.visibility = VISIBLE
                binding?.ctlItemStep?.visibility = GONE
                binding?.ctlItemCurrentStep?.visibility = GONE
            }
            currentPosition == anyList[position].currentPage -> {
                binding?.ctlItemStepLast?.visibility = GONE
                binding?.ctlItemStep?.visibility = GONE
                binding?.ctlItemCurrentStep?.visibility = VISIBLE
            }
            else -> {
                binding?.ctlItemStepLast?.visibility = GONE
                binding?.ctlItemStep?.visibility = VISIBLE
                binding?.ctlItemCurrentStep?.visibility = GONE
            }
        }
    }

    override fun getItemCount(): Int = anyList.size

    inner class StepsHolder(binding: ItemStepBinding) : RecyclerView.ViewHolder(binding.root)

    fun nextPage() {
        if (currentPosition == anyList.size)
            return

        if (currentPosition <= anyList.size) {
            currentPosition++
            defineStyle(currentPosition)
        }

        Log.d("LOG", "Current Page: ${anyList[currentPosition].currentPage}")
        Log.d("LOG", "Current Position: $currentPosition")
    }

    fun updateList(anyList: ArrayList<AnyModel>) {
        this.anyList.clear()
        this.anyList.addAll(anyList)
    }
}