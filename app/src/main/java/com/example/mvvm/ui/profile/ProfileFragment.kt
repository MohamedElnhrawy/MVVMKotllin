package com.example.mvvm.ui.profile

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.mvvm.R
import com.example.mvvm.databinding.ProfileFragmentBinding
import org.kodein.di.android.x.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class  ProfileFragment : Fragment() ,KodeinAware{
    val factory : ProfileViewModelFactory by instance()
    override val kodein by kodein()


    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding : ProfileFragmentBinding = DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)
        viewModel = ViewModelProviders.of(this,factory).get(ProfileViewModel::class.java)
        binding.profileViewModel = viewModel
        // as we bind live data obj to our xml , we need to add lifecycleowner
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}
