package com.example.mamajohnspizzaapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.mamajohnspizzaapp.databinding.FragmentOrderBinding


class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.orderPizza.setOnClickListener {
            // Error Message Text
            var errorText = ""
            // Pizza Size
            var pizzaSizeSelected = binding.pizzaSizes.getSelectedItem().toString()
            // Crust Types
            var crustTypeSelected = binding.crustTypes.getSelectedItem().toString()
            // Topping
            var toppingsSelected = binding.toppings.getSelectedItem().toString()

            val pizzaShape = binding.pizzaShapesGroup.checkedRadioButtonId
            if (pizzaShape == -1){
                errorText = "You need to choose pizza size"
                Toast.makeText(activity, errorText, Toast.LENGTH_LONG).show()
            } else {
                var pizzaOrderText = "Pizza Shape: " + (when (pizzaShape) {
                    R.id.radio_circleShape -> "Circle"
                    else -> "Square"
                })
                pizzaOrderText += ", Pizza Size: $pizzaSizeSelected, Crust: $crustTypeSelected, Topping: $toppingsSelected"
                val action = OrderFragmentDirections
                    .actionOrderFragmentToCheckoutFragment(pizzaOrderText)
                view.findNavController().navigate(action)
            }
        }


        return view
    }
}