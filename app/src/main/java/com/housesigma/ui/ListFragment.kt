package com.housesigma.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.housesigma.MainActivityViewModel
import com.housesigma.adapter.ListAdapter
import com.housesigma.adapter.MapAdapter
import com.housesigma.databinding.FragmentListBinding
import com.housesigma.databinding.FragmentMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListFragment() : BaseFragment() {
    private lateinit var mBinding: FragmentListBinding
    private val mVm: ListViewModel by viewModel()
    private var listAdapter: ListAdapter? = null
    private var sIsScrolling:Boolean=false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentListBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        mBinding.vm = mVm
        listAdapter = ListAdapter()
        listAdapter?.isHavePhoto?.value=false
        mBinding.recycler.adapter=listAdapter

        //优化滚动
        mBinding.recycler.setHasFixedSize(true)
        mBinding.recycler.setItemViewCacheSize(20);
        mBinding.recycler.setDrawingCacheEnabled(true);
        mBinding.recycler.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        mBinding.smart.setOnRefreshListener {
            mVm.refresh()
        }
        mBinding.smart.setOnLoadmoreListener {
            mVm.loadMore()
        }
        mBinding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(mVm.isHavePhoto.value==true){
                    if (newState == RecyclerView.SCROLL_STATE_DRAGGING || newState == RecyclerView.SCROLL_STATE_SETTLING) {
                        sIsScrolling = true;
                        Glide.with(requireContext()).pauseRequests()
                    } else if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        if (sIsScrolling == true) {
                            Glide.with(requireContext()).resumeRequests()
                        }
                        sIsScrolling = false;
                    }
                }
            }
        })
        mVm.load()
        return mBinding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVm.listData.observe(viewLifecycleOwner, { listData ->
            if (mVm.index == 1) {
                listAdapter?.items?.clear()
            }
            listAdapter?.items?.addAll(listData)
            listAdapter?.notifyDataSetChanged()
        })
        mVm.event.observe(viewLifecycleOwner, { event ->
            when (event) {
                ListViewModel.ViewModelEvent.ShowPhotoEvent->{
                    listAdapter?.isHavePhoto?.value=mVm.isHavePhoto.value
                    listAdapter?.notifyDataSetChanged()
                }
                ListViewModel.ViewModelEvent.EndEvent->{
                    mBinding.smart.finishRefresh()
                    mBinding.smart.finishLoadmore()
                }
            }
        })
    }
}