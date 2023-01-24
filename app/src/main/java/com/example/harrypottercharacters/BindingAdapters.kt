package com.example.harrypottercharacters

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.harrypottercharacters.network.HpProperty
import com.example.harrypottercharacters.overview.HpApiStatus
import com.example.harrypottercharacters.overview.PhotoGridAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data: List<HpProperty>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}
@BindingAdapter("image")
fun bindImage(imgView:ImageView,image:String?){
    image?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}

@BindingAdapter("hpApiStatus")
fun bindStatus(statusImageView: ImageView, status:HpApiStatus?){
    when(status){
        HpApiStatus.LOADING ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        HpApiStatus.ERROR ->{
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        else -> {statusImageView.visibility = View.GONE}
    }
}