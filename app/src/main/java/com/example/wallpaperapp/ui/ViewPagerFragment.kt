package com.example.wallpaperapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.wallpaperapp.R
import com.example.wallpaperapp.ui.categoy.CategoriesFragment
import com.example.wallpaperapp.ui.favourite.FavouritesFragment
import com.example.wallpaperapp.ui.home.HomeFragment
import com.example.wallpaperapp.databinding.FragmentViewPagerBinding
import com.example.wallpaperapp.utilities.WallpaperPageAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewPagerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = DataBindingUtil.inflate<FragmentViewPagerBinding>(inflater,
                R.layout.fragment_view_pager,
            container,
            false)



        val fragmentList:ArrayList<Fragment> = arrayListOf(
            HomeFragment.newInstance(),
            CategoriesFragment.newInstance(),
            FavouritesFragment.newInstance())

        val viewPagerAdapter = WallpaperPageAdapter(fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)


        binding.viewPager.adapter = viewPagerAdapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab,position->

            when(position){
                0->{tab.setText("Home");tab.setIcon(R.drawable.ic_home)}
                1->{tab.setText("Categories");tab.setIcon(R.drawable.ic_categories)}
                2->{tab.setText("Favourites");tab.setIcon(R.drawable.ic_favourite)}
                else->{tab.setText("Home");tab.setIcon(R.drawable.ic_home)}
            }
        }.attach()

        return binding.root
    }


}