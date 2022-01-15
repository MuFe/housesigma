package com.housesigma.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.housesigma.databinding.FragmentMapBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.recyclerview.widget.LinearSnapHelper
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.housesigma.adapter.MapAdapter
import androidx.recyclerview.widget.RecyclerView


class MapFragment() : BaseFragment() {
    private lateinit var mBinding: FragmentMapBinding
    private val mVm: MapViewModel by viewModel()
    private var map: GoogleMap? = null
    private var mapAdapter: MapAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentMapBinding.inflate(inflater)
        mBinding.lifecycleOwner = this
        mBinding.vm = mVm
        MapsInitializer.initialize(requireContext())
        mBinding.map.onCreate(null)

        mBinding.map.getMapAsync(OnMapReadyCallback {
            // 这里返回创建好的map
            mVm.load()
            map = it
            it.setMinZoomPreference(13.0f)
            it.setMaxZoomPreference(20.0f)
            it.uiSettings.isZoomGesturesEnabled = true
            it.setOnMarkerClickListener {
                val index = it.title!!.toInt()
                mBinding.recycler.smoothScrollToPosition(index)
                mVm.isHide.value = false
                false
            }
            it.setOnMapClickListener {
                mVm.isHide.value = true
            }
        })
        mapAdapter = MapAdapter()
        mBinding.recycler.adapter = mapAdapter
        LinearSnapHelper().attachToRecyclerView(mBinding.recycler)
        mBinding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val childCount = recyclerView.getChildCount()
                for (index in 0..childCount) {
                    val child = recyclerView.getChildAt(index)
                    if (child != null) {
                        val left = child.getLeft()
                        val paddingStart = recyclerView.getPaddingStart();
                        // 遍历recyclerView子项，以中间项左侧偏移量为基准进行缩放
                        val bl =
                            Math.min(1f, Math.abs(left - paddingStart) * 1f / child.getWidth());
                        val scale = 1 - bl * (1 - 0.8);
                        child.setScaleY(scale.toFloat())
                    }

                }
            }

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState==RecyclerView.SCROLL_STATE_IDLE){
                   val lay= recyclerView.layoutManager as LinearLayoutManager
                    val index=lay.findFirstVisibleItemPosition()
                    val it =mapAdapter?.items!!.get(index)
                    val appointLoc = LatLng(it.location.lat, it.location.lon)
                    map?.moveCamera(CameraUpdateFactory.newLatLng(appointLoc))
                }
            }
        })

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mVm.listData.observe(viewLifecycleOwner, { listData ->
            for ((index, it) in listData.withIndex()) {
                val appointLoc = LatLng(it.location.lat, it.location.lon)
                map?.addMarker(MarkerOptions().position(appointLoc).title(index.toString()))
                if (index == 0) {
                    map?.moveCamera(CameraUpdateFactory.newLatLng(appointLoc))
                }
            }
            mapAdapter?.items?.clear()
            mapAdapter?.items?.addAll(listData)
            mapAdapter?.notifyDataSetChanged()
        })

    }

    override fun onResume() {
        super.onResume()
        mBinding.map.onResume()
    }

    override fun onStart() {
        super.onStart()
        mBinding.map.onStart()
    }

    override fun onStop() {
        super.onStop()
        mBinding.map.onStop()
    }


    override fun onPause() {
        mBinding.map.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mBinding.map.onDestroy()
        super.onDestroy()
    }


}