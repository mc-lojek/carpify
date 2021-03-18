package pl.mclojek.carpify.presentation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import pl.mclojek.carpify.databinding.ViewLakeListItemBinding
import pl.mclojek.carpify.domain.model.Lake
import timber.log.Timber

class LakeListItemView(context: Context, attrs: AttributeSet? = null) :
        FrameLayout(context, attrs), KodeinAware {
    private val binding =
            ViewLakeListItemBinding.inflate(LayoutInflater.from(context), this, true)
    override val kodein: Kodein by kodein()

    init {
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    fun bind(lake: Lake) {
        Timber.d("lake: ${lake.name}")
        binding.lake = lake
        //lake.getBounds().center
    }
}