package com.example.lottie

import com.example.lottie.databinding.FragmentOnBoardingBinding



import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.fragment.findNavController



class OnBoardingFragment : Fragment(), OnItemClicker {

    private lateinit var binding: FragmentOnBoardingBinding
    private val list = arrayListOf<OnBoardingModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnBoardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list.add(OnBoardingModel("anim/1.json", "Экономь время", "Дальше"))
        list.add(OnBoardingModel("anim/2.json", "Достигай целей", "Дальше"))
        list.add(OnBoardingModel("anim/3.json", "Развивайся", "НЕТ"))
        binding.viewPager.adapter = OnBoardingAdapter(list, this)
        binding.dotsIndicator.attachTo(binding.viewPager)


    }



    override fun onClick() {
        findNavController().navigate(R.id.clearBackStack)


        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("board_preference", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isShow", true).apply()



    }

    override fun onClickNext() {
        onNext()
    }

    private fun onNext() {
        val adapter = binding.viewPager.adapter
        val currentPosition = binding.viewPager.currentItem
        val nextPosition = currentPosition + 1
        if (nextPosition < adapter?.itemCount!!) {
            binding.viewPager.currentItem = nextPosition
        } else
            binding.viewPager.currentItem = 0
    }
}

