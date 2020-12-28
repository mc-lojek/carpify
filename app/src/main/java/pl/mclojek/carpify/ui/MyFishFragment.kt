package pl.mclojek.carpify.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pl.mclojek.carpify.R

class MyFishFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("foobar", "my fish fragment sie odpalil")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_fish, container, false)
    }
}