package com.example.mvvm.ui.quotes

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer

import com.example.mvvm.R
import com.example.mvvm.ui.profile.ProfileViewModelFactory
import com.example.mvvm.util.Coroutines
import com.example.mvvm.util.toast
import okhttp3.internal.Internal.instance
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment() ,KodeinAware {
    override val kodein by kodein()
    val factory : QuotesViewModelFactory by instance()

    companion object {
        fun newInstance() = QuotesFragment()
    }

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,factory).get(QuotesViewModel::class.java)
        // TODO: Use the ViewModel

        // quotes type is deferred of livedata of list of quotes so we should use await() , and we need to call await from acoroutines scope or another
        //suspend func but now we can't make onactivity created as asuspend func so use coroutines scope
        Coroutines.main {
          val quotes =   viewModel.quotes.await()
            quotes.observe(this, Observer {
                context?.toast("${it.size}")
            })

        }
    }

}
