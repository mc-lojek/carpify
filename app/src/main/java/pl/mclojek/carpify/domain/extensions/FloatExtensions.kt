package pl.mclojek.carpify.domain.extensions

fun Float.toReadable(scale: Int) = "%.${scale}f".format(this)