package com.example.harrypottercharacters.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.harrypottercharacters.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val application = requireNotNull(activity).application
        val binding = FragmentDetailBinding.inflate(inflater)
        binding.lifecycleOwner = this
        val hpProperty = DetailFragmentArgs.fromBundle(requireArguments()).selectedProperty
        val viewModelFactory = DetailViewModelFactory(hpProperty, application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[DetailViewModel::class.java]
        binding.viewModel = viewModel
        val currentValue = viewModel.selectedProperty.value
        binding.student.text =
            if (currentValue!!.hogwartsStudent) {
            "Student of Hogwarts"
            }
            else{
            "Not a Student of Hogwarts"
            }
        binding.nameText.text = "Name : " + currentValue.character
        binding.nickName.text = "Nickname : " + currentValue.nickname
        binding.house.text = "Hogwarts House : " + currentValue.hogwartsHouse
        binding.actor.text = "Actor : " + currentValue.interpretedBy



        binding.shareButton.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,"*Name* : ${currentValue.character}" +
                        "\n*${binding.student.text}*" +
                        "\n*Nickname* : ${currentValue.nickname}" +
                        "\n*Hogwarts House* : ${currentValue.hogwartsHouse}" +
                        "\n*Actor* : ${currentValue.interpretedBy}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share To:"))
        }
        return binding.root
    }
}
