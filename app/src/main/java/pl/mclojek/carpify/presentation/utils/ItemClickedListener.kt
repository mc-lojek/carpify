package pl.mclojek.carpify.presentation.utils

interface ItemClickedListener<T> {
    fun onItemClicked(item: T)
}