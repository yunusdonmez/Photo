package com.genesis.randomphoto.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.genesis.randomphoto.R
import com.genesis.randomphoto.adapter.SliderAdapter
import com.genesis.randomphoto.dto.PhotoDTO
import com.genesis.randomphoto.framework.slide.ItemConfig
import com.genesis.randomphoto.framework.slide.ItemTouchHelperCallback
import com.genesis.randomphoto.framework.slide.OnSlideListener
import com.genesis.randomphoto.framework.slide.SlideLayoutManager
import com.genesis.randomphoto.network.SendRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SliderFragment : Fragment(), Callback<ArrayList<PhotoDTO>> {
    override fun onFailure(call: Call<ArrayList<PhotoDTO>>, t: Throwable) {
        Toast.makeText(context, "Resimleri Görebilmek İçin İnternet Bağlantısı Gerekir.", Toast.LENGTH_LONG).show()
        mPhotoList.add(PhotoDTO(9999))
        initView()
        initListener()
    }

    override fun onResponse(call: Call<ArrayList<PhotoDTO>>, response: Response<ArrayList<PhotoDTO>>) {
        mPhotoList = response.body()!!
        mPhotoList.shuffle()
        initView()
        initListener()
    }


    private lateinit var rootView: View
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSlideLayoutManager: SlideLayoutManager
    private lateinit var mItemTouchHelper: ItemTouchHelper
    private lateinit var mItemTouchHelperCallback: ItemTouchHelperCallback<Int>
    private var mAdapter: SliderAdapter? = null
    private var mPhotoList = ArrayList<PhotoDTO>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_slider, container, false)
        //initView(rootView)
        //initListener()
        addData()
        return rootView
    }

    private fun initView() {
        Log.e("SliderFragment", "initView")
        mRecyclerView = rootView.findViewById(R.id.recycler_view)
        mAdapter = SliderAdapter(rootView.context, mPhotoList)
        mRecyclerView.adapter = mAdapter
        //addData()
        mItemTouchHelperCallback = ItemTouchHelperCallback<Int>(mRecyclerView.adapter!!, mPhotoList)
        mItemTouchHelper = ItemTouchHelper(mItemTouchHelperCallback)
        mSlideLayoutManager = SlideLayoutManager(mRecyclerView, mItemTouchHelper)
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)
        mRecyclerView.layoutManager = mSlideLayoutManager

    }

    private fun initListener() {
        mItemTouchHelperCallback.setOnSlideListener(object : OnSlideListener<PhotoDTO> {
            override fun onSlided(viewHolder: RecyclerView.ViewHolder, t: PhotoDTO, direction: Int) {
                Log.e("SliderFragment", "onSlided")
            }

            override fun onSliding(viewHolder: RecyclerView.ViewHolder, ratio: Float, direction: Int) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }

            }

            override fun onClear() {
                addData()
            }

        })
    }

    fun addData() {
        SendRequest.getPhotos().enqueue(this@SliderFragment)

        /*val bgs = ArrayList<Int>()
        bgs.add(R.drawable.img_slide_1)
        bgs.add(R.drawable.img_slide_2)
        bgs.add(R.drawable.img_slide_3)
        bgs.add(R.drawable.img_slide_4)
        bgs.add(R.drawable.img_slide_5)
        bgs.add(R.drawable.img_slide_6)

        for (i in 0..5) {
            mList.add(bgs[i])
        }*/
    }
}


